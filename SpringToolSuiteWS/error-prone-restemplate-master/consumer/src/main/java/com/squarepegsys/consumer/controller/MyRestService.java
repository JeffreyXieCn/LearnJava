package com.squarepegsys.consumer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.squarepegsys.consumer.data.SomeResult;
import com.squarepegsys.consumer.service.MyConsumerService;

@RestController
@RequestMapping("/myService")
public class MyRestService {

    @Autowired
    private MyConsumerService myConsumerService;

    @RequestMapping(method = RequestMethod.GET, value = "{value}/calculate")
    public ResponseEntity<SomeResult> vinStatus(@PathVariable Integer value) {

        return myConsumerService.calculate(value);

    }
}
