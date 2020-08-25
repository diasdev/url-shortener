package com.demo.urlshortener.store;

import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Component("LocalStore")
public class LocalStore implements InMemoryStore {
    private static ConcurrentHashMap<String, String> store = new ConcurrentHashMap<>();

    @Override
    public void put(String code, String url) {
        store.put(code, url);
    }

    @Override
    public Optional<String> get(String shortenedUrl) {
        return Optional.ofNullable(store.get(shortenedUrl));
    }
}
