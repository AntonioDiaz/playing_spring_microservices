package com.adiaz.accounts.controllers;

import com.adiaz.accounts.config.AccountsServiceConfig;
import com.adiaz.accounts.feign.CardsFeignClients;
import com.adiaz.accounts.model.Card;
import lombok.AllArgsConstructor;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;


@RestController
@AllArgsConstructor
@EnableFeignClients
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
  public List<Card> cards() {
    return cardsFeignClients.getAllCards();
  }

}
