package ru.iteco.stocksevice.model;

import lombok.Data;

import java.util.List;

@Data
public class StockQuotesResDto {

    List<Info> data;

}
