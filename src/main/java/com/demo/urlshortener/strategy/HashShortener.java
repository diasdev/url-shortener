package com.demo.urlshortener.strategy;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Component;

import static org.apache.commons.codec.digest.MessageDigestAlgorithms.SHA_224;

@Component
public class HashShortener implements Shortener {
    private static final DigestUtils digest = new DigestUtils(SHA_224);

    public String shortenUrl(String url) {
            String result = digest.digestAsHex(url);
            return result;
    }
}
