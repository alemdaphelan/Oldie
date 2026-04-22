package com.oldie.backend.core.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@Configuration
@EnableMongoAuditing
public class MongoConfig {
    // This class enables MongoDB auditing, which allows us to automatically
    // populate fields like createdAt and updatedAt in our MongoDB documents.
}