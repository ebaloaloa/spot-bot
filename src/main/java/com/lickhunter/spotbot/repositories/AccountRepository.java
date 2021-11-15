package com.lickhunter.spotbot.repositories;

import com.lickhunter.spotbot.dto.AccountDto;
import com.lickhunter.spotbot.entities.tables.records.AccountRecord;
import lombok.extern.slf4j.Slf4j;
import org.jooq.DSLContext;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Objects;

import static com.lickhunter.spotbot.entities.tables.Account.ACCOUNT;

@Service
@Slf4j
public class AccountRepository extends AbstractCrudRepository<AccountDto, AccountRecord> {

    public AccountRepository(DSLContext dsl) {
        super(dsl);
        super.table = ACCOUNT;
    }

    public AccountRecord save(AccountRecord accountRecord) {
        AccountRecord account = this.findOne(accountRecord.getId());
        try {
            if(Objects.nonNull(account)) {
                dsl.update(ACCOUNT)
                        .set(ACCOUNT.USDT_BALANCE, Objects.nonNull(accountRecord.getUsdtBalance()) ? accountRecord.getUsdtBalance() : account.getUsdtBalance())
                        .where(ACCOUNT.ID.eq(accountRecord.getId()))
                        .execute();
            } else {
                dsl.insertInto(ACCOUNT)
                        .set(ACCOUNT.ID, accountRecord.getId())
                        .set(ACCOUNT.USDT_BALANCE, accountRecord.getUsdtBalance())

                        .execute();
            }
        } catch (Exception e) {
            log.error(String.format("Failed to save symbol: %s", accountRecord.getId()));
        }
        return accountRecord;
    }

    @Override
    public AccountRecord findOne(Serializable primaryKey) {
        Objects.requireNonNull(primaryKey);
        return dsl.selectFrom(ACCOUNT)
                .where(ACCOUNT.ID.equalIgnoreCase(primaryKey.toString()))
                .fetchOne();
    }
}
