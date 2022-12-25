package com.adiaz.cards.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="CARD")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Card {

  @Id
  @GeneratedValue(strategy= GenerationType.AUTO)
  private Long id;

  private String userCard;

  private Integer balance;
}
