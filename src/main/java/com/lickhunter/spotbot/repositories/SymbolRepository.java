package com.lickhunter.spotbot.repositories;

import com.lickhunter.spotbot.dto.SymbolDto;
import com.lickhunter.spotbot.entities.tables.records.SymbolRecord;
import lombok.extern.slf4j.Slf4j;
import org.jooq.DSLContext;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import static com.lickhunter.spotbot.entities.tables.Symbol.SYMBOL;

@Component
@Slf4j
public class SymbolRepository
        extends AbstractCrudRepository<SymbolDto, SymbolRecord> {

    public SymbolRepository(DSLContext dsl) {
        super(dsl);
        super.table = SYMBOL;
    }

    public SymbolRecord save(SymbolRecord symbolRecord) {
        SymbolRecord symbol = this.findOne(symbolRecord.getSymbol());
        try {
            if(Objects.nonNull(symbol)) {
                dsl.update(SYMBOL)
                        .set(SYMBOL.GALAXY_SCORE, Objects.nonNull(symbolRecord.getGalaxyScore()) ? symbolRecord.getGalaxyScore() : symbol.getGalaxyScore())
                        .set(SYMBOL.ALT_RANK, Objects.nonNull(symbolRecord.getAltRank()) ? symbolRecord.getAltRank() : symbol.getAltRank())
                        .set(SYMBOL.STEP_SIZE, Objects.nonNull(symbolRecord.getStepSize()) ? symbolRecord.getStepSize() :symbol.getStepSize())
                        .set(SYMBOL.QUOTE_PRECISION, Objects.nonNull(symbolRecord.getQuotePrecision()) ? symbolRecord.getQuotePrecision() : symbol.getQuotePrecision())
                        .set(SYMBOL.PRICE, Objects.nonNull(symbolRecord.getPrice()) ? symbolRecord.getPrice() : symbol.getPrice())
                        .set(SYMBOL.ORDER_ID, Objects.nonNull(symbolRecord.getOrderId()) ? symbolRecord.getOrderId() : symbol.getOrderId())
                        .set(SYMBOL.IS_TRADING, Objects.nonNull(symbolRecord.getIsTrading()) ? symbolRecord.getIsTrading() : symbol.getIsTrading())
                        .where(SYMBOL.SYMBOL_.eq(symbolRecord.getSymbol()))
                        .execute();
            } else {
                dsl.insertInto(SYMBOL)
                        .set(SYMBOL.SYMBOL_, symbolRecord.getSymbol())
                        .set(SYMBOL.GALAXY_SCORE, symbolRecord.getGalaxyScore())
                        .set(SYMBOL.ALT_RANK, symbolRecord.getAltRank())
                        .set(SYMBOL.STEP_SIZE, symbolRecord.getStepSize())
                        .set(SYMBOL.QUOTE_PRECISION, symbolRecord.getQuotePrecision())
                        .set(SYMBOL.PRICE, symbolRecord.getPrice())
                        .set(SYMBOL.ORDER_ID, symbolRecord.getOrderId())
                        .set(SYMBOL.IS_TRADING, symbolRecord.getIsTrading())
                        .execute();
            }
        } catch (Exception e) {
            log.error(String.format("Failed to save symbol: %s", symbolRecord.getSymbol()));
        }
        return symbolRecord;
    }

    public List<SymbolRecord> findTradingSymbols() {
        return dsl.selectFrom(SYMBOL)
                .where(SYMBOL.IS_TRADING.eq(true))
                .fetch();
    }

    @Override
    public SymbolRecord findOne(Serializable primaryKey) {
        Objects.requireNonNull(primaryKey);
        return dsl.selectFrom(SYMBOL)
                .where(SYMBOL.SYMBOL_.equalIgnoreCase(primaryKey.toString()))
                .fetchOne();
    }
}
