package ru.iteco.account.gateway.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpHeaders;
import ru.iteco.account.gateway.model.ConverterRequest;

import java.math.BigDecimal;
import java.math.BigInteger;

@Component
@RequiredArgsConstructor
class CurrencyApiServiceImpl implements CurrencyApiService {

    @Value("${spring.security.oauth2.client.registration.auth0.client-id}")
    private String clientId;

    @Value("${spring.security.oauth2.client.registration.auth0.client-secret}")
    private String clientSecret;

    @Value("${service.currency.audience}")
    private String audience;

    @Value("${service.currency.uri}")
    private String uri;

    @Value("${service.currency.path.all-exchange}")
    private String allExchangePath;

    @Value("${service.currency.path.convert}")
    private String convert;

    private final RestTemplate restTemplate;
    private final TokenApiService tokenApiService;

    @Override
    public String getAllExchange() {
        String token = tokenApiService.getToken(clientId, clientSecret, audience);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setBearerAuth(token);
        RequestEntity<Void> request = RequestEntity
                .get(uri + allExchangePath)
                .headers(httpHeaders)
                .build();
        ResponseEntity<String> responseEntity = restTemplate.exchange(request, String.class);
        return responseEntity.getBody();
    }

    @Override
    public String convert(){
        String token = tokenApiService.getToken(clientId, clientSecret, audience);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setBearerAuth(token);
        ConverterRequest converterRequest = ConverterRequest.builder()
                .from("RUB")
                .to("EUR")
                .amount(BigDecimal.TEN)
                .build();
        RequestEntity<ConverterRequest> request = RequestEntity
                .post(uri + convert)
                .headers(httpHeaders)
                .body(converterRequest);
        ResponseEntity<String> responseEntity = restTemplate
                .exchange(request, String.class);
        return responseEntity.getBody();

    }
}
