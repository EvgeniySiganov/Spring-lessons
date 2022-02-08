package ru.iteco.stocksevice.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.iteco.stocksevice.model.HistoricalQuotesRequest;
import ru.iteco.stocksevice.model.HistoricalQuotesResponse;
import ru.iteco.stocksevice.model.StockQuotesResDto;

import java.net.URISyntaxException;
import java.util.List;


@Component
public class StockApiImpl implements StockApi {
    private final RestTemplate restTemplate;
    private final String token;
    private final String urlActualQuotes;
    private final String urlHistoricalQuotes;

    public StockApiImpl(@Qualifier("restTemplateStock") RestTemplate restTemplate,
                        @Value("stock.token") String token,
                        @Value("stock.url.actual") String urlActualQuotes,
                        @Value("stock.url.historical-quote") String urlHistoricalQuotes) {
        this.restTemplate = restTemplate;
        this.token = token;
        this.urlActualQuotes = urlActualQuotes;
        this.urlHistoricalQuotes = urlHistoricalQuotes;
    }

    @Override
    public StockQuotesResDto getActualRates(List<String> rates) throws URISyntaxException {
        String stringRates = String.join(",", rates);
        String url = String.format(urlActualQuotes, stringRates, token);

        ResponseEntity<StockQuotesResDto>  resp = restTemplate.getForEntity(url, StockQuotesResDto.class);

        return resp.getBody();
    }

    @Override
    public HistoricalQuotesResponse getHistoricalQuotes(HistoricalQuotesRequest request) {
        String stringRates = String.join(",", request.getTickets());
        String url = String.format(urlHistoricalQuotes, request.getDate(), token);

        ResponseEntity<HistoricalQuotesResponse> response = restTemplate.getForEntity(url, HistoricalQuotesResponse.class);
        return response.getBody();
    }
}
