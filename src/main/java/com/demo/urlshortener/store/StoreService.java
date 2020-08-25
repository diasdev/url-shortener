package com.demo.urlshortener.store;

import com.demo.urlshortener.model.ShortURL;
import com.demo.urlshortener.repository.UrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StoreService {

    @Autowired
    @Qualifier(value = "LocalStore")
    private InMemoryStore inMemStore;

    @Autowired
    private UrlRepository repository;

    public Optional<String> findUrlByCode(String code) {
        return inMemStore.get(code)
                .or(() -> findPersistedUrlByCode(code));
    }

    public Optional<String> findPersistedUrlByCode(String code) {
        return Optional.ofNullable(repository.findByCode(code)).map(url -> url.getOriginalUrl());
    }

    public void put(String code, String originalUrl) {
        inMemStore.put(code, originalUrl);

        ShortURL url = new ShortURL();
        url.setCode(code);
        url.setOriginalUrl(originalUrl);
        repository.save(url);
    }
}
