package com.lickhunter.spotbot.converters;

import com.lickhunter.spotbot.dto.AccountDto;
import com.lickhunter.spotbot.entities.tables.records.AccountRecord;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Objects;

@Component
public class AccountConverter extends Converter<AccountDto, AccountRecord> {

    public AccountConverter() {
        super(AccountConverter::convertToEntity, AccountConverter::convertToDto);
    }

    private static AccountDto convertToDto(AccountRecord symbolRecord) {
        return new AccountDto()
                .withId(symbolRecord.getId())
                .withUsdtBalance(new BigDecimal(symbolRecord.getUsdtBalance()));
    }

    private static AccountRecord convertToEntity(AccountDto accountDto) {
        AccountRecord accountRecord = new AccountRecord();
        accountRecord.setId(accountDto.getId());
        accountRecord.setUsdtBalance(Objects.nonNull(accountDto.getUsdtBalance()) ? accountDto.getUsdtBalance().toString() : null);
        return accountRecord;
    }
}
