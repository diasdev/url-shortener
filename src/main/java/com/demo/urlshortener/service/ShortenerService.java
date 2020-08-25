package com.demo.urlshortener.service;

import com.demo.urlshortener.store.StoreService;
import com.demo.urlshortener.strategy.Shortener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ShortenerService {

    @Value("${application.initial-url-size}")
    private  int INITIAL_URL_LENGTH;

    @Value("${application.base-path}")
    private String BASE_PATH;

    @Autowired
    private Shortener shortener;

    @Autowired
    private StoreService storeService;

    public String createShortUrl(String originalUrl) {
        int urlLength = INITIAL_URL_LENGTH;

        originalUrl = formatURL(originalUrl);
        String hashedUrl = shortener.shortenUrl(originalUrl);
        String code = hashedUrl.substring(0, urlLength);

        boolean collision = checkCollision(code, originalUrl);

        while (collision) {
            urlLength++;
            code = hashedUrl.substring(0, urlLength);

            collision = checkCollision(code, originalUrl);
        }

        storeService.put(code, originalUrl);

        return BASE_PATH + code;
    }

    private boolean checkCollision(String code, String url) {
        Optional<String> storedUrl = storeService.findUrlByCode(code);
        return storedUrl.isPresent() && !url.equals(storedUrl.get());
    }

    private String formatURL(String url) {
        if (!"http://".equals(url.substring(0,7)) && !"https://".equals(url.substring(0,8))) {
            url = "http://" + url;
        }
        return url;
    }
}
