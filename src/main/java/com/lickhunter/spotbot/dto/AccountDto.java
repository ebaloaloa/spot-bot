package com.lickhunter.spotbot.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@With
@AllArgsConstructor
@NoArgsConstructor
public class AccountDto {
    private String id;
    private BigDecimal usdtBalance;
}
