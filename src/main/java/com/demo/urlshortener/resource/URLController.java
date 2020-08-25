package com.demo.urlshortener.resource;

import com.demo.urlshortener.service.ShortenerService;
import com.demo.urlshortener.store.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.net.URI;
import java.util.Optional;

@RestController
public class URLController {

    @Autowired
    private ShortenerService service;

    @Autowired
    private StoreService store;

    @GetMapping("/{code}")
    public ResponseEntity<Void> redirect(@PathVariable String code) throws IOException {
        try {
            Optional<String> originalUrl = store.findUrlByCode(code);

            return ResponseEntity.status(HttpStatus.FOUND)
                    .location(URI.create(originalUrl.orElseThrow()))
                    .build();
        }
        catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "URL not found", e);
        }
    }

    @PostMapping("/shorten")
    public ResponseEntity<String> getShortenedUrl(@RequestBody String url) throws IOException {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(service.createShortUrl(url));
        }
        catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, "Error saving URL", e);
        }
    }
}
