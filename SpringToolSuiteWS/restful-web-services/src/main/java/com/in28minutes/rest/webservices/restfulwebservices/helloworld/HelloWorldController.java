package com.in28minutes.rest.webservices.restfulwebservices.helloworld;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
  @Autowired private MessageSource messageSource;

  @GetMapping(path = "/hello-world")
  public String helloWorld() {
    return "Hello World";
  }

  @GetMapping(path = "/hello-world-bean")
  public HelloWorldBean helloWorldBean() {
    return new HelloWorldBean("Hello World");
  }

  @GetMapping("/hello-world/path-variable/{name}")
  public HelloWorldBean helloWorldPathVariable(@PathVariable String name) {
    return new HelloWorldBean(String.format("Hello World, %s", name));
  }

  //  @GetMapping(path = "/hello-world-internationalized")
  //  public String helloWorldInternationalized(
  //      @RequestHeader(name = "Accept-Language", required = false) Locale locale) {
  //    String msg = messageSource.getMessage("good.morning.message", null, locale);
  //    System.out.println("message is: " + msg);
  //    return msg;
  //  }

  @GetMapping(path = "/hello-world-internationalized")
  public String helloWorldInternationalized() {
    String msg =
        messageSource.getMessage("good.morning.message", null, LocaleContextHolder.getLocale());
    System.out.println("message is: " + msg);
    return msg;
  }
}
