package com.lickhunter.spotbot.strategies;

import com.lickhunter.spotbot.controllers.ApplicationController;
import com.lickhunter.spotbot.entities.tables.records.SymbolRecord;
import com.lickhunter.spotbot.properties.ApplicationProperty;
import com.lickhunter.spotbot.properties.StrategyProperty;
import com.lickhunter.spotbot.repositories.AccountRepository;
import com.lickhunter.spotbot.repositories.SymbolRepository;
import com.lickhunter.spotbot.services.TradeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class LunarCrushStrategy {

    private final ApplicationProperty applicationProperty;
    private final StrategyProperty strategyProperty;
    private final SymbolRepository symbolRepository;
    private final AccountRepository accountRepository;
    private final TradeService tradeService;
    private final ApplicationController applicationController;

    @Scheduled(fixedRateString = "3600000")
    public void rebalance() {
        applicationController.saveSymbol();
        applicationController.getSentiments();
        applicationController.getAccountInformation();
        applicationController.getTickerPrice();
        List<SymbolRecord> tradingSymbols = symbolRepository.findTradingSymbols();
        List<SymbolRecord> symbolRecords = getTradeableSymbols();
        if(!symbolRecords.equals(tradingSymbols)) {
            //close all positions
            tradingSymbols.forEach(symbolRecord -> {
                try {
                    tradeService.closePosition(symbolRecord);
                    symbolRecord.setIsTrading(false);
                    symbolRepository.save(symbolRecord);
                } catch (Exception e) {
                    log.error(String.format("Failed closing position for symbol %s: %s", symbolRecord.getSymbol(), e.getMessage()));
                }
            });
            symbolRecords.forEach(symbolRecord -> {
                BigDecimal qty = getQty(symbolRecord, symbolRecords);
                if (qty.compareTo(BigDecimal.ZERO) > 0) {
                    try {
                        tradeService.newOrder(symbolRecord.getSymbol(), qty);
                        symbolRecord.setIsTrading(true);
                        symbolRepository.save(symbolRecord);
                    } catch (Exception e) {
                        log.error(String.format("Failed creating new order for symbol %s: %s", symbolRecord.getSymbol(), e.getMessage()));
                    }
                }
            });
        }
    }

    private BigDecimal getQty(SymbolRecord symbolRecord, List<SymbolRecord> symbolRecords) {
        BigDecimal balance = new BigDecimal(accountRepository.findOne(applicationProperty.getKey()).getUsdtBalance());
        int scale = BigDecimal.valueOf(symbolRecord.getStepSize()).stripTrailingZeros().scale();
        BigDecimal qty = balance
                .multiply(BigDecimal.valueOf(strategyProperty.getRebalanceRatio())
                        .divide(BigDecimal.valueOf(100D), 2, RoundingMode.HALF_DOWN))
                .divide(BigDecimal.valueOf(symbolRecords.size()), 2, RoundingMode.HALF_DOWN)
                .divide(BigDecimal.valueOf(symbolRecord.getPrice()), scale, RoundingMode.HALF_DOWN) //markPrice
                .setScale(scale, RoundingMode.HALF_DOWN);
        return qty.compareTo(BigDecimal.valueOf(symbolRecord.getStepSize())) > 0 ? qty : BigDecimal.ZERO;
    }

    private List<SymbolRecord> getTradeableSymbols() {
        List<SymbolRecord> symbolRecords = (List<SymbolRecord>) symbolRepository.findAll();
        return symbolRecords.stream()
                .filter(symbolRecord -> Objects.nonNull(symbolRecord.getAltRank())
                        && Objects.nonNull(symbolRecord.getGalaxyScore())
                        && Objects.nonNull(symbolRecord.getPrice()))
                .filter(symbolRecord -> symbolRecord.getAltRank() <= strategyProperty.getAltRank())
                .filter(symbolRecord -> symbolRecord.getGalaxyScore() >= strategyProperty.getGalaxyScore())
                .collect(Collectors.toList());
    }
}
