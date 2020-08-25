package com.demo.urlshortener.store;

import org.springframework.stereotype.Component;

import java.util.Optional;

@Component("RedisStore")
public class RedisStore implements InMemoryStore {
    @Override
    public void put(String code, String url) {
    }

    @Override
    public Optional<String> get(String shortenedUrl) {
        return Optional.ofNullable(null);
    }
}
