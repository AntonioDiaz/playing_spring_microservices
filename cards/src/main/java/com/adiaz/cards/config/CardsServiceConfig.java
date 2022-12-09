package com.adiaz.cards.config;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "cards")
@Getter
@Setter
@ToString
public class CardsServiceConfig {
  private String emoji;
  private String welcome;
}
