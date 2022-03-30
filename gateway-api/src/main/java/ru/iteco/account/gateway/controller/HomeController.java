package ru.iteco.account.gateway.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.iteco.account.gateway.service.CurrencyApiService;
import ru.iteco.account.gateway.service.StockApiService;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class HomeController {

    private final CurrencyApiService currencyApiService;
    private final StockApiService stockApiService;

    @GetMapping("/")
    public String getHome(Model model, @AuthenticationPrincipal OidcUser oidcUser){
        if(oidcUser != null){
            log.info("USER: {}", oidcUser.getClaims());
            log.info("USER: {}", oidcUser.getAuthorities());
            model.addAttribute("profile", oidcUser.getClaims());
        }
        return "index";
    }

    @GetMapping("/all-exchange")
    public @ResponseBody String getAllExchange(){
        return currencyApiService.getAllExchange();
    }

    @GetMapping("/convert")
    public @ResponseBody String convert(){
        return currencyApiService.convert();
    }

    @GetMapping("/get-stock-quotes")
    @ModelAttribute(value = "tickets")
    public @ResponseBody String getStockQuotes(@ModelAttribute("tickets") List<String>tickets){
        return stockApiService.getStockQuotes(tickets);
    }
}
