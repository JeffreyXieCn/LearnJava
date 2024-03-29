package com.in28minutes.microservices.currencyexchangeservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class CurrencyExchangeController {

  @Autowired private Environment env;
  @Autowired private ExchangeValueRepository repository;

  @GetMapping("/currency-exchange/from/{from}/to/{to}")
  public ExchangeValue retrieveExchangeValue(@PathVariable String from, @PathVariable String to) {
    // ExchangeValue exchangeValue = new ExchangeValue(1000L, from, to, BigDecimal.valueOf(65));
    ExchangeValue exchangeValue = repository.findByFromAndTo(from, to);
    exchangeValue.setPort(Integer.parseInt(env.getProperty("local.server.port")));
    log.info(">>>>>>>>>>>>>>>>>>>>{}", exchangeValue);
    return exchangeValue;
  }
}
