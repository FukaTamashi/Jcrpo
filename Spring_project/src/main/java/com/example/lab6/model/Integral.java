package com.example.lab6.model;

public class Integral {
    private double start;
    private double end;

    private double result;

    public Integral(double start, double end) {
        this.start = start;
        this.end = end;
    }

    public double getStart() {
        return start;
    }

    public double getResult() {return result;}

    public void setStart(double start) {this.start = start;}

    public double getEnd() {
        return end;
    }

    public void setEnd(double end) {
        this.end = end;
    }

    public void setResult(double result) {this.result = result;}
}