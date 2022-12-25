package com.adiaz.accounts.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Card {

  private Long id;

  private String userCard;

  private Integer balance;

  @Override
  public String toString() {
    return "Card{" +
            "id=" + id +
            ", userCard='" + userCard + '\'' +
            ", balance=" + balance +
            '}';
  }
}
