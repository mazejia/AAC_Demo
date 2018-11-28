package com.example.mazejia.aac.aac_demo.bean;

import java.util.List;

public class GirlData {

    public final boolean error;

    public final List<Girl> results;

    public GirlData(boolean error, List<Girl> results) {
        this.error = error;
        this.results = results;
    }
}
