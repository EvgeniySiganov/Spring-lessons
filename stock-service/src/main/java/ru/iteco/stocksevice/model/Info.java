package ru.iteco.stocksevice.model;

import lombok.Data;

@Data
public class Info {
    private String ticker;
    private String name;
    private String currency;
    private String price;
}
