package com.cursojava.appautonomo.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

public class TokenBody implements Serializable {

    @JsonAlias(value = "sub")
    private String sub;

    private String iss;

    private String iat;

    private String exp;

    public String getSub() {
        return sub;
    }

    public void setSub(String sub) {
        this.sub = sub;
    }
}
