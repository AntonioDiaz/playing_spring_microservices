package com.adiaz.cards.controllers;

import com.adiaz.cards.config.CardsServiceConfig;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class HomeController {

  private CardsServiceConfig cardsServiceConfig;

  @RequestMapping("/")
  public ResponseEntity<String>home(){
    String msg = String.format("*vamos -> %s %s",
            cardsServiceConfig.getWelcome(),
            cardsServiceConfig.getEmoji());
    return ResponseEntity.ok(msg);


  }
}
