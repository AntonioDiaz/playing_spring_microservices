package com.adiaz.loans.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

  @RequestMapping("/")
  public ResponseEntity<String>home(){
    return ResponseEntity.ok("loans \uD83D\uDE0E\uD83D\uDE07");
  }
}
