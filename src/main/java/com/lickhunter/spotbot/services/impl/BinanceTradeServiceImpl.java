package com.lickhunter.spotbot.services.impl;

import com.binance.api.client.BinanceApiClientFactory;
import com.binance.api.client.BinanceApiRestClient;
import com.binance.api.client.domain.OrderSide;
import com.binance.api.client.domain.OrderType;
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
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

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
        List<Order> orderList = client.getAllOrders(ordersRequest)
                .stream()
                .filter(order -> order.getType().equals(OrderType.MARKET) && order.getSide().equals(OrderSide.BUY))
                .sorted(Comparator.comparing(Order::getTime).reversed())
                .collect(Collectors.toList());
        new AtomicReference<>(BigDecimal.ONE);
        AtomicReference<BigDecimal> qty = new AtomicReference<>();
        qty.set(new BigDecimal(orderList.get(0)
                .getExecutedQty())
                .setScale(scale, RoundingMode.HALF_DOWN));
        while(true) {
            try {
                client.newOrder(NewOrder.marketSell(symbolRecord.getSymbol(), qty.toString()));
                break;
            } catch (Exception e) {
                qty.set(qty.get()
                        .multiply(BigDecimal.valueOf(0.99))
                        .setScale(scale, RoundingMode.HALF_DOWN));
                continue;
            }
        }
    }
}
