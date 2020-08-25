package com.demo.urlshortener.store;

import java.util.Optional;

public interface InMemoryStore {
    void put(String code, String url);

    Optional<String> get(String shortenedUrl);
}
