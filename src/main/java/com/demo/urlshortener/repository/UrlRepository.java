package com.demo.urlshortener.repository;

import com.demo.urlshortener.model.OriginalUrlView;
import com.demo.urlshortener.model.ShortURL;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UrlRepository extends CrudRepository<ShortURL, String> {
    OriginalUrlView findByCode(String code);
}
