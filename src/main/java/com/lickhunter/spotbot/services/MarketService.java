package com.lickhunter.spotbot.services;

import java.util.List;

public interface MarketService {
    <EXCHANGEINFO> EXCHANGEINFO getExchangeInformation();
    <TICKERPRICE> List<TICKERPRICE> getTickerPrice();
}
