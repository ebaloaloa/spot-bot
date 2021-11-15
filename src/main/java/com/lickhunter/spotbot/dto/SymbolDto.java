package com.lickhunter.spotbot.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@With
@AllArgsConstructor
@NoArgsConstructor
public class SymbolDto {
    private String symbol;
    private Integer galaxyScore;
    private Integer altRank;
    private BigDecimal stepSize;
    private Integer quotePrecision;
    private BigDecimal price;
    private Long orderId;
    private Boolean isTrading;
}
