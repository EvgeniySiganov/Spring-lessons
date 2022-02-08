package ru.iteco.stocksevice.service;

import ru.iteco.stocksevice.model.HistoricalQuotesRequest;
import ru.iteco.stocksevice.model.HistoricalQuotesResponse;
import ru.iteco.stocksevice.model.StockQuotesResDto;

import java.net.URISyntaxException;
import java.util.List;

public interface StockApi {
    StockQuotesResDto getActualRates(List<String> rates) throws URISyntaxException;

    HistoricalQuotesResponse getHistoricalQuotes(HistoricalQuotesRequest request);


}
