package com.lickhunter.spotbot.services.impl;

import com.binance.api.client.BinanceApiClientFactory;
import com.binance.api.client.BinanceApiRestClient;
import com.binance.api.client.domain.account.NewOrder;
import com.binance.api.client.domain.account.NewOrderResponse;
import com.binance.api.client.domain.account.NewOrderResponseType;
import com.binance.api.client.domain.account.Order;
import com.binance.api.client.domain.account.request.AllOrdersRequest;
import com.lickhunter.spotbot.converters.SymbolConverter;
import com.lickhunter.spotbot.dto.SymbolDto;
import com.lickhunter.spotbot.entities.tables.records.SymbolRecord;
import com.lickhunter.spotbot.properties.ApplicationProperty;
import com.lickhunter.spotbot.repositories.SymbolRepository;
import com.lickhunter.spotbot.services.TradeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class BinanceTradeServiceImpl implements TradeService {

    private final ApplicationProperty applicationProperty;
    private final SymbolRepository symbolRepository;
    private final SymbolConverter symbolConverter;

    @Override
    public void newOrder(String symbol, BigDecimal qty) {
        BinanceApiClientFactory factory = BinanceApiClientFactory.newInstance(applicationProperty.getKey(), applicationProperty.getSecret());
        BinanceApiRestClient client = factory.newRestClient();
        SymbolRecord symbolRecord = symbolRepository.findOne(symbol);
        NewOrderResponse order = client.newOrder(NewOrder.marketBuy(symbol, qty.toString())
                                                    .newOrderRespType(NewOrderResponseType.FULL));
        symbolRepository.save(symbolConverter.convertFromDto(new SymbolDto().withSymbol(symbol).withOrderId(order.getOrderId())));
    }

    public void closePosition(SymbolRecord symbolRecord) {
        BinanceApiClientFactory factory = BinanceApiClientFactory.newInstance(applicationProperty.getKey(), applicationProperty.getSecret());
        BinanceApiRestClient client = factory.newRestClient();
        Long orderId = symbolRepository.findOne(symbolRecord.getSymbol()).getOrderId();
        AllOrdersRequest ordersRequest = new AllOrdersRequest(symbolRecord.getSymbol());
        ordersRequest.orderId(orderId);
        Integer scale = BigDecimal.valueOf(symbolRecord.getStepSize()).stripTrailingZeros().scale();
        List<Order> orderList = client.getAllOrders(ordersRequest);
        String qty = orderList.stream()
                .map(Order::getExecutedQty)
                .map(BigDecimal::new)
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .setScale(scale, RoundingMode.UNNECESSARY)
                .toString();
        client.newOrder(NewOrder.marketSell(symbolRecord.getSymbol(), qty));
    }
}