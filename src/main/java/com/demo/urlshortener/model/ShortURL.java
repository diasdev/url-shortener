package com.demo.urlshortener.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ShortURL {
    @Id
    private String code;
    private String originalUrl;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getOriginalUrl() {
        return originalUrl;
    }

    public void setOriginalUrl(String originalUrl) {
        this.originalUrl = originalUrl;
    }
}
