package com.csw.java.bean;

import java.io.Serializable;

public class Site implements Serializable {

    private static final long serialVersionUID = -4345938479956046629L;
    private String name;
    private String key;
    private String baseUrl;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }
}
