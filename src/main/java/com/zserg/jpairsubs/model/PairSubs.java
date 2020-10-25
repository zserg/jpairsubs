package com.zserg.jpairsubs.model;

import lombok.Data;

import java.util.List;

@Data
public class PairSubs {
    private String title;
    private int year;
    private List<String> lang;

}
