package com.zserg.jpairsubs.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PairSub {
    Movie movie;
    private Sub subA;
    private Sub subB;
}
