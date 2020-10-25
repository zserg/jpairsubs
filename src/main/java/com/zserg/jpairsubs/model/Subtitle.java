package com.zserg.jpairsubs.model;

import lombok.Getter;

import java.time.LocalTime;

@Getter
public class Subtitle {
    private int number;
    private int start;
    private int end;
    String text;

    public Subtitle(int number, int start, int end, String text){
        if(start >= end){
            throw new IllegalArgumentException("'start' time is less than 'end' time");
        }
        this.number = number;
        this.start = start;
        this.end = end;
        this.text = text;
    }
}
