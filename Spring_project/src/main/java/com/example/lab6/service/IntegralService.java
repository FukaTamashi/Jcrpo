package com.example.lab6.service;

import com.example.lab6.model.Integral;
import com.example.lab6.repository.IntegralRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class IntegralService {
    private static final Logger logger = LoggerFactory.getLogger(IntegralService.class);
    private final IntegralRepository repository;

    private final ExceptionService exceptionService;
    private final Counter counter;

    public IntegralService(IntegralRepository repository, Counter counter) {
        this.repository = repository;
        this.counter = counter;
        this.exceptionService = new ExceptionService();
    }

    public List<Double> integrate(List<Integral> integrals) {
        List<Double> results = new ArrayList<>();

        for (Integral integral : integrals) {
            double start = integral.getStart();
            double end = integral.getEnd();

            logger.info("Received integrate request with start={} and end={}", start, end);

            String cacheKey = String.format("%.2f_%.2f", start, end);
            Double cachedResult = repository.get(cacheKey);


            if (cachedResult != null) {
                logger.info("Returning cached result for key={}", cacheKey);
                counter.increment();
                results.add(cachedResult);
                continue;
            }

            exceptionService.checkException(integral);

            double result;

            try {
                result = integrateTrapezioidal(start, end);
            } catch (RuntimeException e) {
                logger.error("An internal error occurred while integrating function", e);
                throw new RuntimeException("An internal error occurred while integrating function", e);
            }

            repository.put(cacheKey, result);
            counter.increment();
            results.add(result);

            logger.info("Result of integration is {}", result);
        }

        return results;
    }

    public double integrateTrapezioidal(double start, double end) {
        double h = end - start;
        double fStart = Math.sin(start);
        double fEnd = Math.sin(end);
        return (h / 2.0) * (fStart + fEnd);
    }

    public void clearCache() {
        repository.clear();
    }
}
