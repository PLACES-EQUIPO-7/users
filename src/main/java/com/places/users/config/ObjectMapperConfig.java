package com.places.users.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

@Configuration
public class ObjectMapperConfig {

    @Bean
    public ObjectMapper objectMapper() {
        return new Jackson2ObjectMapperBuilder()
                .featuresToDisable(
                        JsonGenerator.Feature.IGNORE_UNKNOWN,
                        MapperFeature.DEFAULT_VIEW_INCLUSION,
                        DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
                        SerializationFeature.WRITE_DATES_AS_TIMESTAMPS
                )
                .serializationInclusion(JsonInclude.Include.ALWAYS)
                .propertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE)
                .build().registerModule(new JavaTimeModule());
    }
}
