package com.adiaz.loans.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

  @Value("${loans.emoji}")
  String emoji;
  @Value("${loans.welcome}")
  String welcome;

  @RequestMapping("/")
  public ResponseEntity<String>home(){
    String msg = String.format("%s %s", welcome, emoji);
    return ResponseEntity.ok(msg);
  }
}
