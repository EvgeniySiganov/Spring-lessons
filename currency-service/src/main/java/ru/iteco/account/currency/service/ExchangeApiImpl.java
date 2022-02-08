package ru.iteco.account.currency.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.iteco.account.currency.model.AllCurrencyExchange;
import ru.iteco.account.currency.model.ConvertResult;
import ru.iteco.account.currency.model.ConverterRequest;

import java.math.BigDecimal;


@Service
public class ExchangeApiImpl implements ExchangeApi {

    private final RestTemplate restTemplate;
    private final String token;
    private final String urlExchange;
    private final String urlAllExchange;

    public ExchangeApiImpl(@Qualifier("restTemplateExchange") RestTemplate restTemplate,
                           @Value("${currency.token}") String token,
                           @Value("${currency.url.convert}") String urlConvert,
                           @Value("${currency.url.all-exchange}") String urlAllExchange) {
        this.restTemplate = restTemplate;
        this.token = token;
        this.urlExchange = urlConvert;
        this.urlAllExchange = urlAllExchange;
    }

    @Override
    public ConvertResult convert(ConverterRequest converterRequest) {
//        String urlConvert = String.format(urlExchange, token, converterRequest.getFrom(), converterRequest.getTo(),
//                converterRequest.getAmount());
//        ResponseEntity<ConvertResult> resultResponseEntity = restTemplate.getForEntity(urlConvert, ConvertResult.class);
//        return resultResponseEntity.getBody();
        return ConvertResult.builder()
                .result(new BigDecimal(100))
                .query(
                        ConverterRequest.builder()
                                .amount(converterRequest.getAmount())
                                .from(converterRequest.getFrom())
                                .to(converterRequest.getTo())
                                .build()
                ).build();
    }

    @Override
    public AllCurrencyExchange getAllCurrencyExchange() {
        String url = String.format(urlAllExchange, token);
        ResponseEntity<AllCurrencyExchange> resultResponseEntity = restTemplate.getForEntity(url, AllCurrencyExchange.class);
        return resultResponseEntity.getBody();
    }
}
