package com.example.lab6.controller;

import com.example.lab6.model.Integral;
import com.example.lab6.service.AggregationService;
import com.example.lab6.service.Counter;
import com.example.lab6.service.IntegralDeffaultService;
import com.example.lab6.service.IntegralService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@AllArgsConstructor
public class IntegralController {
    private final IntegralService service;
    private final IntegralDeffaultService deffaultService;
    private final AggregationService aggregationService;


    @Autowired
    private Counter counter;

    @GetMapping("/integrate")
    public ResponseEntity<Double> integrate(@ModelAttribute Integral inte) {
        double result = deffaultService.integrate(inte);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/counter")
    public int count() {
        return counter.getCount();
    }

    @DeleteMapping("/cache")
    public ResponseEntity<Void> clearCache() {
        service.clearCache();
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/bulk-integrate")
    public ResponseEntity<Map<String, Object>> bulkIntegrate(@RequestBody List<Integral> integrals) {
        Map<String, Object> response = aggregationService.bulkIntegrate(integrals);
        return ResponseEntity.ok(response);
    }
}