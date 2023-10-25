package com.example.lab6.repository;

import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class IntegralRepository {
    private final Map<String, Double> cache = new ConcurrentHashMap<>();

    public void put(String key, double value) {
        cache.put(key, value);
    }

    public Double get(String key) {
        return cache.get(key);
    }

    public void clear() {
        cache.clear();
    }
}