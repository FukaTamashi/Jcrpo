package com.example.lab6.service;

import com.example.lab6.model.Integral;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.security.InvalidParameterException;

@Service
@Scope("singleton")
public class ExceptionService {

    ExceptionService(){};
    public void checkException(Integral integral) throws InvalidParameterException, IllegalArgumentException
    {
        if(integral.getStart() < 0) {
            throw new InvalidParameterException("start less than zero");
        }
        if(integral.getEnd() < 0) {
            throw new InvalidParameterException("start less than zero");
        }
        if(Double.isInfinite(integral.getStart()) || Double.isInfinite(integral.getEnd())) {
            throw new InvalidParameterException("one of the numbers is infinite");
        }
        if(integral.getStart() >= integral.getEnd()){
            throw new InvalidParameterException("start param should be less than end param");
        }
    }
}
