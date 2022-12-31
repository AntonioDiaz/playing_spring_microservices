package com.adiaz.cards.controllers;

import com.adiaz.cards.config.CardsServiceConfig;
import com.adiaz.cards.model.Card;
import com.adiaz.cards.repositories.CardRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j
public class HomeController {

  private CardsServiceConfig cardsServiceConfig;
  private CardRepository cardRepository;


  @RequestMapping("/")
  public ResponseEntity<String>home(){
    String msg = String.format("*vamos -> %s %s",
            cardsServiceConfig.getWelcome(),
            cardsServiceConfig.getEmoji());
    return ResponseEntity.ok(msg);
  }

  @RequestMapping("/all-cards")
  public List<Card> allCards() {
    log.info("Start: getting all cards");
    return cardRepository.findAll();
  }

}
