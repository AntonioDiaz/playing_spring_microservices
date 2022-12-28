package com.adiaz.accounts.controllers;

import com.adiaz.accounts.config.AccountsServiceConfig;
import com.adiaz.accounts.feign.CardsFeignClients;
import com.adiaz.accounts.model.Card;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;


@RestController
@AllArgsConstructor
@EnableFeignClients
@Slf4j
public class HomeController {

  private AccountsServiceConfig accountsServiceConfig;
  private CardsFeignClients cardsFeignClients;

  @RequestMapping("/")
  public ResponseEntity<String>home(){
    String msg = String.format("%s %s <br> password: %s",
            accountsServiceConfig.getWelcome(),
            accountsServiceConfig.getEmoji(),
            accountsServiceConfig.getPassword());
    return ResponseEntity.ok(msg);
  }


  @RequestMapping("/cards")
  @CircuitBreaker(name = "cards-circuit-breaker", fallbackMethod = "cardsFallbackMethod")
  public List<Card> cards() {
    return cardsFeignClients.getAllCards();
  }

  @RequestMapping("/cards-retry")
  @Retry(name = "cards-retry")
  public List<Card> cardsWithRetry() {
    log.info("Calling cards-retry");
    return cardsFeignClients.getAllCards();
  }


  private List<Card> cardsFallbackMethod(Throwable t) {
    return List.of();
  }

}
