package com.lickhunter.spotbot.converters;

import com.lickhunter.spotbot.dto.SymbolDto;
import com.lickhunter.spotbot.entities.tables.records.SymbolRecord;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Objects;

@Component
public class SymbolConverter extends Converter<SymbolDto, SymbolRecord> {

    public SymbolConverter() {
        super(SymbolConverter::convertToEntity, SymbolConverter::convertToDto);
    }

    private static SymbolDto convertToDto(SymbolRecord symbolRecord) {
        return new SymbolDto()
                .withSymbol(symbolRecord.getSymbol())
                .withGalaxyScore(symbolRecord.getGalaxyScore())
                .withAltRank(symbolRecord.getAltRank())
                .withQuotePrecision(symbolRecord.getQuotePrecision())
                .withStepSize(BigDecimal.valueOf(symbolRecord.getStepSize()))
                .withPrice(BigDecimal.valueOf(symbolRecord.getPrice()))
                .withOrderId(symbolRecord.getOrderId())
                .withIsTrading(symbolRecord.getIsTrading());
    }

    private static SymbolRecord convertToEntity(SymbolDto symbolDto) {
        SymbolRecord symbolRecord = new SymbolRecord();
        symbolRecord.setSymbol(symbolDto.getSymbol());
        symbolRecord.setGalaxyScore(Objects.nonNull(symbolDto.getGalaxyScore()) ? symbolDto.getGalaxyScore() : null);
        symbolRecord.setAltRank(Objects.nonNull(symbolDto.getAltRank()) ? symbolDto.getAltRank() : null);
        symbolRecord.setQuotePrecision(Objects.nonNull(symbolDto.getQuotePrecision()) ? symbolDto.getQuotePrecision() : null);
        symbolRecord.setStepSize(Objects.nonNull(symbolDto.getStepSize()) ? symbolDto.getStepSize().doubleValue() : null);
        symbolRecord.setPrice(Objects.nonNull(symbolDto.getPrice()) ? symbolDto.getPrice().doubleValue() : null);
        symbolRecord.setOrderId(Objects.nonNull(symbolDto.getOrderId()) ? symbolDto.getOrderId() : null);
        symbolRecord.setIsTrading(Objects.nonNull(symbolDto.getIsTrading()) ? symbolDto.getIsTrading() : null);
        return symbolRecord;
    }
}
