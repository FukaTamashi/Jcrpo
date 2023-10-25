package com.example.lab6.service;

import com.example.lab6.model.Integral;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;

@Service
public class AggregationService {
    private final IntegralService integralService;

    @Autowired
    public AggregationService(IntegralService integralService) {
        this.integralService = integralService;
    }

    public Map<String, Object> bulkIntegrate(List<Integral> integrals) {
        List<Double> results = integralService.integrate(integrals);

        // Set the results for each integral object
        for (int i = 0; i < integrals.size(); i++) {
            integrals.get(i).setResult(results.get(i));
        }

        // Calculate aggregated values
        DoubleSummaryStatistics inputStartStats = integrals.stream()
                .mapToDouble(Integral::getStart)
                .summaryStatistics();

        DoubleSummaryStatistics inputEndStats = integrals.stream()
                .mapToDouble(Integral::getEnd)
                .summaryStatistics();

        DoubleSummaryStatistics resultStats = results.stream()
                .mapToDouble(Double::doubleValue)
                .summaryStatistics();

        // Build the response
        Map<String, Object> response = Map.of(
                "integrals", integrals,
                "start", Map.of(
                        "min", inputStartStats.getMin(),
                        "max", inputStartStats.getMax(),
                        "average", inputStartStats.getAverage()
                ),
                "end", Map.of(
                        "min", inputEndStats.getMin(),
                        "max", inputEndStats.getMax(),
                        "average", inputEndStats.getAverage()
                ),
                "results", Map.of(
                        "min", resultStats.getMin(),
                        "max", resultStats.getMax(),
                        "average", resultStats.getAverage()
                )
        );

        return response;
    }
}