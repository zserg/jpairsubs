package com.zserg.jpairsubs.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Movie {
    private String title;
    private String imdb;
    private int year;
    private String[] lang;
}
