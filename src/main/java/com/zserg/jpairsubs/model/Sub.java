package com.zserg.jpairsubs.model;

import com.zserg.jpairsubs.data.SubConverter;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Sub {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name="movie_id")
    private Movie movie;

    private String language;

    @Convert(converter = SubConverter.class)
    private List<Subtitle> subs;
}
