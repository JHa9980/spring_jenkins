package com.skala.springbootsample.service;


public class RealServiceImpl implements MyService {
    @Override
    public String getMessage(String name) {
        return "Hello, " + name;
    }
}
