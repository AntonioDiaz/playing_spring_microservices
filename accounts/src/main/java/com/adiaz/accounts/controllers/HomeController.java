package com.adiaz.accounts.controllers;

import com.adiaz.accounts.config.AccountsServiceConfig;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class HomeController {

  private AccountsServiceConfig accountsServiceConfig;

  @RequestMapping("/")
  public ResponseEntity<String>home(){
    String msg = String.format("%s %s <br> password: %s",
            accountsServiceConfig.getWelcome(),
            accountsServiceConfig.getEmoji(),
            accountsServiceConfig.getPassword());
    return ResponseEntity.ok(msg);
  }
}
