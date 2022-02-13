package com.cheeseocean.service;

public class FlyService {

    private final String moduleName;

    public FlyService(String moduleName){
        this.moduleName = moduleName;
    }
    public String getModuleName() {
        return moduleName;
    }
}
