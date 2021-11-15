package com.lickhunter.spotbot.services.impl;

import com.binance.api.client.BinanceApiClientFactory;
import com.binance.api.client.BinanceApiRestClient;
import com.binance.api.client.domain.general.ExchangeInfo;
import com.binance.api.client.domain.market.TickerPrice;
import com.lickhunter.spotbot.properties.ApplicationProperty;
import com.lickhunter.spotbot.services.MarketService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class BinanceMarketServiceImpl implements MarketService {

    private final ApplicationProperty applicationProperty;

    @Override
    public ExchangeInfo getExchangeInformation() {
        log.debug("Retrieving exchange information");
        ExchangeInfo result = null;
        BinanceApiClientFactory factory = BinanceApiClientFactory.newInstance(applicationProperty.getKey(), applicationProperty.getSecret());
        BinanceApiRestClient client = factory.newRestClient();
        try {
            result = client.getExchangeInfo();
        } catch(Exception e) {
            log.error("Failed to retrieve exchange information. " + e.getMessage());
        }
        return result;
    }

    @Override
    public List<TickerPrice> getTickerPrice() {
        log.debug("Retrieving All Market Prices");
        List<TickerPrice> result = null;
        BinanceApiClientFactory factory = BinanceApiClientFactory.newInstance(applicationProperty.getKey(), applicationProperty.getSecret());
        BinanceApiRestClient client = factory.newRestClient();
        try {
            result = client.getAllPrices();
        } catch(Exception e) {
            log.error("Failed to retrieve ticker prices");
        }
        return result;
    }
}
