package ru.iteco.account.currency.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.iteco.account.currency.model.AllCurrencyExchange;
import ru.iteco.account.currency.model.ConvertResult;
import ru.iteco.account.currency.model.ConverterRequest;
import ru.iteco.account.currency.service.ExchangeApi;

@RestController
@RequiredArgsConstructor
@Slf4j
public class CurrencyController {

    private final ExchangeApi exchangeApi;

    @PostMapping("/convert")
    public ConvertResult convertAmount(@RequestHeader("trace-id") String traceId,
                                       @RequestBody ConverterRequest converterRequest){
        log.info("REQUEST WITH TRACE ID: {}", traceId);
        return exchangeApi.convert(converterRequest);
    }

    @GetMapping("/all-exchange")
    public AllCurrencyExchange getAllExchange(@RequestHeader("trace-id") String traceId){
        log.info("REQUEST WITH TRACE ID: {}", traceId);
        return exchangeApi.getAllCurrencyExchange();
    }
}
