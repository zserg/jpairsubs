package com.zserg.jpairsubs.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MovieExt {
    private String title;
    private String imdb;
    private int year;
    private List<String> languages;

    public MovieExt(Movie movie) {
        this.title = movie.getTitle();
        this.imdb = movie.getImdb();
        this.year = movie.getYear();
        this.languages = movie.getSubList().stream()
                .map(Sub::getLanguage)
                .collect(Collectors.toList());
    }
}
