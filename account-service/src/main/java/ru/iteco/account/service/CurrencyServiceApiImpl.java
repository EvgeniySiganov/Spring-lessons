package ru.iteco.account.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.iteco.account.model.currency.AllCurrencyExchange;
import ru.iteco.account.model.currency.ConvertResult;
import ru.iteco.account.model.currency.ConverterRequest;

import java.util.UUID;

@Slf4j
@Component
public class CurrencyServiceApiImpl implements CurrencyServiceApi {

    private final RestTemplate restTemplate;
    private final String urlAllExchange;
    private final String urlConvert;

    public CurrencyServiceApiImpl(@Qualifier("restTemplateExchangeApi") RestTemplate restTemplate,
                                  @Value("${service.currency.all-exchange}") String urlAllExchange,
                                  @Value("${service.currency.convert}") String urlConvert) {
        this.restTemplate = restTemplate;
        this.urlAllExchange = urlAllExchange;
        this.urlConvert = urlConvert;
    }

    @Override
    public AllCurrencyExchange getAllExchange() {
        String traceId = UUID.randomUUID().toString();
        log.info("REQUEST WITH TRACE ID: {}", traceId);

        RequestEntity<Void> request = RequestEntity.get(urlAllExchange)
                .header("trace-id", traceId)
                .build();
        ResponseEntity<AllCurrencyExchange> responseEntity = restTemplate.exchange(request, AllCurrencyExchange.class);
        if(responseEntity.getStatusCode().isError()){
            log.error("CURRENCY SERVICE RETURN ERROR: {}", responseEntity);
        }

        return responseEntity.getBody();
    }

//    @Override
//    public ConvertResult convert(ConverterRequest converterRequest) {
//        String traceId = UUID.randomUUID().toString();
//        log.info("REQUEST WITH TRACE ID: {}", traceId);
//        RequestEntity<ConverterRequest> request = RequestEntity
//                .post(urlConvert)
//                .header("trace-id", traceId)
//                .body(converterRequest);
//        ResponseEntity<ConvertResult> response = restTemplate.exchange(request, ConvertResult.class);
//        if(response.getStatusCode().isError()){
//            log.error("CURRENCY SERVICE RETURN ERROR: {}", response);
//        }
//        return response.getBody();
//    }

    @Override
    public ConvertResult convert(ConverterRequest request) {
        String traceId = UUID.randomUUID().toString();
        log.info("Request with trace-id: {}", traceId);
        RequestEntity<ConverterRequest> requestEntity = RequestEntity.post(urlConvert)
                .header("trace-id", traceId)
                .body(request);
        ResponseEntity<ConvertResult> responseEntity = restTemplate.exchange(requestEntity, ConvertResult.class);
        if (responseEntity.getStatusCode().isError()) {
            log.error("Currency service returned error: {}", responseEntity);
        }
        return responseEntity.getBody();
    }
}
