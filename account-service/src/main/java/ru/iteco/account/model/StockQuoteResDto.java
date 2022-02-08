package ru.iteco.account.model;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class StockQuoteResDto {

    private String name;
    private String currency;
    private BigDecimal price;

}
