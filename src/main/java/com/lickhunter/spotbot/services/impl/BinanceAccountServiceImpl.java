package com.lickhunter.spotbot.services.impl;

import com.binance.api.client.BinanceApiClientFactory;
import com.binance.api.client.BinanceApiRestClient;
import com.binance.api.client.domain.account.Account;
import com.lickhunter.spotbot.properties.ApplicationProperty;
import com.lickhunter.spotbot.services.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class BinanceAccountServiceImpl implements AccountService {

    private final ApplicationProperty applicationProperty;

    @Override
    public Account getAccountInformation() {
        BinanceApiClientFactory factory = BinanceApiClientFactory.newInstance(applicationProperty.getKey(), applicationProperty.getSecret());
        BinanceApiRestClient client = factory.newRestClient();
        return client.getAccount();
    }
}
