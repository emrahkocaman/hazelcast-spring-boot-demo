package com.example;

import org.springframework.cache.annotation.Cacheable;

/**
 * Created by emrah on 19/11/15.
 */
public interface IDemoBean {
    void addToMap(String key, String value);
    void offerToQueue(String key);

    @Cacheable("methodCache")
    String getItem(String key);
}
