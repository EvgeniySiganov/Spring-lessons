package ru.iteco.account.service;

import ru.iteco.account.model.currency.AllCurrencyExchange;
import ru.iteco.account.model.currency.ConvertResult;
import ru.iteco.account.model.currency.ConverterRequest;

public interface CurrencyServiceApi {

    AllCurrencyExchange getAllExchange();

    ConvertResult convert(ConverterRequest converterRequest);
}
