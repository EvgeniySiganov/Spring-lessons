package ru.iteco.account.gateway.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.client.RestTemplate;

import org.springframework.http.HttpHeaders;
import java.util.List;

@Component
@RequiredArgsConstructor
public class StockApiServiceImpl implements StockApiService {

    @Value("${spring.security.oauth2.client.registration.auth0.client-id}")
    private String clientId;

    @Value("${spring.security.oauth2.client.registration.auth0.client-secret}")
    private String clientSecret;

    @Value("${service.stock.audience}")
    private String audience;

    @Value("${service.stock.uri}")
    private String uri;

    @Value("${service.stock.path.get-stock-quotes}")
    private String getStockQuotes;

    @Value("${service.stock.path.get-historical-quotes}")
    private String getHistoricalQuotes;

    private final RestTemplate restTemplate;
    private final TokenApiService tokenApiService;

    @Override
    public String getStockQuotes(List<String> tickets) {
        String token = tokenApiService.getToken(clientId, clientSecret, audience);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setBearerAuth(token);
        RequestEntity<List<String>> request = RequestEntity
                .post(uri + getStockQuotes)
                .headers(httpHeaders)
                .body(tickets);
        ResponseEntity<String> responseEntity = restTemplate.exchange(request, String.class);
        return responseEntity.getBody();
    }

    @Override
    public String getHistoricalQuotes() {
        return null;
    }
}
