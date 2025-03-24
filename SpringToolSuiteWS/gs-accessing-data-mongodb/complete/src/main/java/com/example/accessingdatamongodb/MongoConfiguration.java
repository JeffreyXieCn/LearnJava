package com.example.accessingdatamongodb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;

@Configuration
public class MongoConfiguration {

    // An exception is thrown when attempting to save a MongoDB document with a key containing dots,
    // unless a dot replacement has been configured.
    @Autowired
    public void setMapKeyDotReplacement(MappingMongoConverter mongoConverter) {
       // mongoConverter.setMapKeyDotReplacement(".");
         mongoConverter.setMapKeyDotReplacement("_");
    }
}
