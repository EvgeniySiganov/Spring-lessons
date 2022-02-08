package ru.iteco.account.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.iteco.account.model.StockQuoteResDto;

import java.net.URISyntaxException;
import java.util.List;


@Service
@Slf4j
public class StockServiceApiImpl implements StockServiceApi {
    private final RestTemplate restTemplate;
    private final String urlActualQuotes;

    public StockServiceApiImpl(@Qualifier("restTemplateExchangeApi") RestTemplate restTemplate,
                               @Value("${service.stock.actual}") String urlActualQuotes) {
        this.restTemplate = restTemplate;
        this.urlActualQuotes = urlActualQuotes;
    }

    @Override
    public List<StockQuoteResDto> getActualRates(List<String> rates) throws URISyntaxException {
//        String stringRates = String.join(",", rates);
//        String url = String.format(urlActualQuotes, stringRates);

//        ResponseEntity<List<StockQuoteResDto>>  resp = restTemplate.exchange(urlActualQuotes, HttpMethod.GET, null,
//                new ParameterizedTypeReference<List<StockQuoteResDto>>(){});

//        if(resp != null && resp.hasBody()){
//            List<StockQuoteResDto> myList = resp.getBody();
//            return myList;
//        }

        RequestEntity<List<String>> request = RequestEntity.post(urlActualQuotes)
                .body(rates);
        ResponseEntity<List<StockQuoteResDto>> response = restTemplate.exchange(request,
                new ParameterizedTypeReference<List<StockQuoteResDto>>() {});
        if(response.getStatusCode().isError()){
            log.info("RETURNED ERROR RESPONSE ENTITY: {}", response);
        }
        return response.getBody();
    }
}
