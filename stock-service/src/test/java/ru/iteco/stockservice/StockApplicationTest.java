package ru.iteco.stockservice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.iteco.stocksevice.service.StockApi;

@SpringBootTest
@Slf4j
public class StockApplicationTest {

    @Autowired
    private StockApi stockApi;
}
