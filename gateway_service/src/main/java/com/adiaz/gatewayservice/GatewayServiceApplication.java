package com.adiaz.gatewayservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@SpringBootApplication
public class GatewayServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(GatewayServiceApplication.class, args);
  }

  @Bean
  public RouteLocator myRoutes(RouteLocatorBuilder builder) {
    return builder.routes()
            .route(p ->
                    p.path("/adiaz/accounts/**")
                            .filters(f -> f
                                    .rewritePath("/adiaz/accounts/(?<segment>.*)", "/${segment}")
                                    .addRequestHeader("X-Response-Time", new Date().toString()))
                            .uri("lb://ACCOUNTS"))
            .route(p ->
                    p.path("/adiaz/cards/**")
                            .filters(f -> f
                                    .rewritePath("/adiaz/cards/(?<segment>.*)", "/${segment}")
                                    .addRequestHeader("X-Response-Time", new Date().toString()))
                            .uri("lb://CARDS"))
            .route(p ->
                    p.path("/adiaz/loans/**")
                            .filters(f -> f
                                    .rewritePath("/adiaz/loans/(?<segment>.*)", "/${segment}")
                                    .addRequestHeader("X-Response-Time", new Date().toString()))
                            .uri("lb://LOANS"))
            .build();
  }
}
