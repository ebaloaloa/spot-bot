package com.lickhunter.spotbot.controllers;

import com.binance.api.client.domain.account.Account;
import com.binance.api.client.domain.general.ExchangeInfo;
import com.binance.api.client.domain.general.FilterType;
import com.binance.api.client.domain.general.SymbolInfo;
import com.binance.api.client.domain.market.TickerPrice;
import com.lickhunter.spotbot.converters.AccountConverter;
import com.lickhunter.spotbot.converters.SymbolConverter;
import com.lickhunter.spotbot.dto.AccountDto;
import com.lickhunter.spotbot.dto.SymbolDto;
import com.lickhunter.spotbot.models.sentiments.SentimentData;
import com.lickhunter.spotbot.properties.ApplicationProperty;
import com.lickhunter.spotbot.repositories.AccountRepository;
import com.lickhunter.spotbot.repositories.SymbolRepository;
import com.lickhunter.spotbot.services.AccountService;
import com.lickhunter.spotbot.services.MarketService;
import com.lickhunter.spotbot.services.SentimentsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RequestMapping("/api/application")
@RestController
@RequiredArgsConstructor
public class ApplicationController {

    private final SentimentsService sentimentsService;
    private final MarketService marketService;
    private final SymbolRepository symbolRepository;
    private final AccountRepository accountRepository;
    private final SymbolConverter symbolConverter;
    private final AccountConverter accountConverter;
    private final AccountService accountService;
    private final ApplicationProperty applicationProperty;

    @GetMapping("/sentiments")
    public ResponseEntity<?> getSentiments() {
        SentimentData sentimentData = sentimentsService.getSentiments();
        if(sentimentData.getData().size() > 0) {
            sentimentData.getData()
                    .forEach(datum -> symbolRepository.save(symbolConverter
                                .convertFromDto(new SymbolDto()
                                        .withSymbol(datum.getS().concat("USDT"))
                                        .withGalaxyScore(datum.getGs().intValue())
                                        .withAltRank(datum.getAcr().intValue())
                                )));
        }
        return ResponseEntity.ok(null);
    }

    @GetMapping("/exchange")
    public ResponseEntity<?> getExchangeInfo() {
        return ResponseEntity.ok(marketService.getExchangeInformation());
    }

    @GetMapping("/price")
    public ResponseEntity<?> getTickerPrice() {
        List<TickerPrice> tickerPrices = marketService.getTickerPrice();
        tickerPrices.forEach(tickerPrice -> {
            symbolRepository.save(symbolConverter.convertFromDto(new SymbolDto()
                    .withSymbol(tickerPrice.getSymbol())
                    .withPrice(new BigDecimal(tickerPrice.getPrice()))));
        });

        return ResponseEntity.ok(tickerPrices);
    }

    @GetMapping("/account")
    public ResponseEntity<?> getAccountInformation() {
        Account account = accountService.getAccountInformation();
        accountRepository.save(accountConverter.convertFromDto(
                new AccountDto()
                        .withId(applicationProperty.getKey())
                        .withUsdtBalance(new BigDecimal(account.getAssetBalance("USDT").getFree()))));
        return ResponseEntity.ok(account);
    }

    @GetMapping("/save")
    public ResponseEntity<?> saveSymbol() {
        ExchangeInfo exchangeInfo = (ExchangeInfo) marketService.getExchangeInformation();
        exchangeInfo.getSymbols()
                .stream()
                .filter(SymbolInfo::isSpotTradingAllowed)
                .filter(symbolInfo -> symbolInfo.getSymbol().matches("^.*USDT$"))
                .map(symbolInfo -> new SymbolDto()
                        .withSymbol(symbolInfo.getSymbol())
                        .withStepSize(symbolInfo.getFilters().stream()
                                .filter(symbolFilter -> symbolFilter.getFilterType().equals(FilterType.LOT_SIZE))
                                .map(symbolFilter -> new BigDecimal(symbolFilter.getStepSize()))
                                .findFirst().get())
                        .withQuotePrecision(symbolInfo.getQuotePrecision()))
                .forEach(symbolDto -> symbolRepository.save(symbolConverter.convertFromDto(symbolDto)));
        return ResponseEntity.ok(null);
    }
}
