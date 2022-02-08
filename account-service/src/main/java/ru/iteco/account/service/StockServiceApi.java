package ru.iteco.account.service;

import ru.iteco.account.model.StockQuoteResDto;

import java.net.URISyntaxException;
import java.util.List;

public interface StockServiceApi {
    List<StockQuoteResDto> getActualRates(List<String> rates) throws URISyntaxException;


}
