package com.squarepegsys.consumer.service;

import java.util.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import com.squarepegsys.consumer.data.SomeResult;

@Component
public class MyConsumerService {

    private static final String DOUBLE_VALUE = "http://localhost:9090/someService/{value}/double";
    private final RestTemplate restTemplate;

    static String buildUrl(Integer value) {
        UriComponents uriComponents = UriComponentsBuilder.fromUriString(DOUBLE_VALUE).buildAndExpand(value).encode();

        return uriComponents.toUriString();
    }


    @Autowired
    public MyConsumerService(RestTemplateBuilder restTemplateBuilder, MyResponseErrorHandler myResponseErrorHandler) {

        this.restTemplate = restTemplateBuilder.errorHandler(myResponseErrorHandler).build();
    }

    public ResponseEntity<SomeResult> calculate(Integer value) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        HttpEntity httpEntity = new HttpEntity(headers);

        ResponseEntity<SomeResult> responseEntity = restTemplate.exchange(buildUrl(value), HttpMethod.GET, httpEntity,
                SomeResult.class);

        return responseEntity;

    }

}
