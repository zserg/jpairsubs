package com.zserg.jpairsubs.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Movie {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private String title;
    private String imdb;
    private int year;

    @OneToMany(mappedBy = "movie")
    private List<PairSub> pairSubList;

    public Movie(String title, String imdb, int year) {
        this.title = title;
        this.imdb = imdb;
        this.year = year;
    }
}