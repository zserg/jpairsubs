package com.zserg.jpairsubs.model;

import lombok.Data;

import java.util.Arrays;

@Data
public class SubItem {
    private String[] subs;

    public SubItem(String first, String second) {
        this.subs = new String[]{first, second};
    }
}
