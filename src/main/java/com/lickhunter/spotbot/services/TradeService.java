package com.lickhunter.spotbot.services;

import com.lickhunter.spotbot.entities.tables.records.SymbolRecord;

import java.math.BigDecimal;

public interface TradeService {
    void newOrder(String symbol, BigDecimal qty);
    void closePosition(SymbolRecord symbolRecord);

}
