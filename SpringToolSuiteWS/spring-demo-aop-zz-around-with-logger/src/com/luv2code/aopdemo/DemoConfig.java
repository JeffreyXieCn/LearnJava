package com.luv2code.aopdemo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
@ComponentScan("com.luv2code.aopdemo")
public class DemoConfig {

  @Bean
  public MyLoggerConfig myLoggerConfig() {
    MyLoggerConfig logger = new MyLoggerConfig("FINEST", "FINEST");
    logger.initLogger();
    return logger;
  }
}
