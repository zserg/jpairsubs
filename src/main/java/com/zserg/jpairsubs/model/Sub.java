package com.zserg.jpairsubs.model;

import com.zserg.jpairsubs.data.SubConverter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
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
    @Lob
    @Column(columnDefinition="LONGTEXT")
    private List<Subtitle> subs;

    public Sub(String imdb, String language, List<Subtitle> subs) {
        this.language = language;
        this.subs = subs;
    }
}
