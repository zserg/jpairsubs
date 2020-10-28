package com.zserg.jpairsubs.model;

import com.zserg.jpairsubs.data.SubConverter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class PairSub {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name="movie_id")
    private Movie movie;

    @Convert(converter = SubConverter.class)
    private Sub subA;

    @Convert(converter = SubConverter.class)
    private Sub subB;
}
