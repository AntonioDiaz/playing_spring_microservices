package com.adiaz.accounts.feign;

import com.adiaz.accounts.model.Card;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient("cards")
public interface CardsFeignClients {
  @RequestMapping(method = RequestMethod.GET, value="all-cards", consumes = "application/json")
  List<Card> getAllCards();
}
