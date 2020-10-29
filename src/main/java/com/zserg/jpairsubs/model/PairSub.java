package com.zserg.jpairsubs.model;

import com.zserg.jpairsubs.data.SubConverter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
public class PairSub {

    private Movie movie;

    private Sub subA;
    private Sub subB;

    public PairSub(Movie movie, Sub subA, Sub subB) {

        this.movie = movie;
        this.subA = subA;
        this.subB = subB;
    }
}
