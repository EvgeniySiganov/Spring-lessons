package ru.iteco.stocksevice.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.iteco.stocksevice.model.HistoricalQuotesRequest;
import ru.iteco.stocksevice.model.HistoricalQuotesResponse;
import ru.iteco.stocksevice.model.StockQuotesResDto;
import ru.iteco.stocksevice.service.StockApi;

import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class StockController {

    private final StockApi stockApi;

    @PostMapping("/actual")
    public StockQuotesResDto getRealTimeStockQuotes(@RequestBody List<String> symbols) throws URISyntaxException {
        return stockApi.getActualRates(symbols);
    }

    @PostMapping("/get-historical-quotes")
    public HistoricalQuotesResponse getStockQuotes(@RequestBody HistoricalQuotesRequest request){
        return stockApi.getHistoricalQuotes(request);
    }
}
