package com.lickhunter.spotbot.subscriptions;

import com.binance.api.client.BinanceApiClientFactory;
import com.binance.api.client.BinanceApiWebSocketClient;
import com.binance.api.client.exception.BinanceApiException;
import com.lickhunter.spotbot.properties.ApplicationProperty;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class BinanceSubscription {

    private final ApplicationProperty applicationProperty;

    public void subscribeMarketTickerEvents() {
        BinanceApiClientFactory factory = BinanceApiClientFactory.newInstance(applicationProperty.getKey(), applicationProperty.getSecret());
        BinanceApiWebSocketClient client = factory.newWebSocketClient();
        try {
//            client.onAllMarketTickersEvent()
        } catch (BinanceApiException e) {

        }
    }
}
