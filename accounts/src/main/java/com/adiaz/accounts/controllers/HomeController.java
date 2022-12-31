package com.adiaz.accounts.controllers;

import com.adiaz.accounts.config.AccountsServiceConfig;
import com.adiaz.accounts.feign.CardsFeignClients;
import com.adiaz.accounts.model.Card;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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


  @GetMapping("/cards")
  @CircuitBreaker(name = "cards-circuit-breaker", fallbackMethod = "cardsFallbackMethod")
  public List<Card> cards() {
    log.info("Start from accounts getting cards");
    List<Card> allCards = cardsFeignClients.getAllCards();
    log.info("after getAllCards from accounts getting cards");
    return allCards;
  }

  @GetMapping("/cards-retry")
  @Retry(name = "cards-retry")
  public List<Card> cardsWithRetry() {
    log.info("Calling cards-retry");
    return cardsFeignClients.getAllCards();
  }

  @GetMapping("/hello")
  @RateLimiter(name = "hello", fallbackMethod = "helloLimit")
  public String hello() {
    log.info("Calling cards-rate-limit");
    return "hello";
  }

  private String helloLimit(Throwable t){
    return "hello LIMIT";
  }

  private List<Card> cardsFallbackMethod(Throwable t) {
    return List.of();
  }
}
