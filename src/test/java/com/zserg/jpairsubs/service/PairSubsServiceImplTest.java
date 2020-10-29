package com.zserg.jpairsubs.service;

import com.zserg.jpairsubs.data.MovieRepository;
import com.zserg.jpairsubs.data.SubRepository;
import com.zserg.jpairsubs.model.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Import(TestConfiguration.class)
class PairSubsServiceImplTest {

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    SubRepository subRepository;

    @Autowired
    PairSubsService pairSubsService;

    @Test
    void getMoviesList() {
        Movie movie1 = new Movie("Movie #1", "IMDB001", 1990);
        Movie movie2 = new Movie("Movie #2", "IMDB002", 2000);

        Subtitle subtitle1 = new Subtitle(1, 10, 15, "текст1");
        Subtitle subtitle2 = new Subtitle(2, 16, 18, "текст2");
        Sub sub1 = new Sub();
        sub1.setMovie(movie1);
        sub1.setLanguage("ru");
        sub1.setSubs(List.of(subtitle1, subtitle2));
        movie1.setSubList(List.of(sub1));

        Subtitle subtitle3 = new Subtitle(1, 10, 15, "text1");
        Subtitle subtitle4 = new Subtitle(2, 16, 18, "text2");
        Sub sub2 = new Sub();
        sub2.setMovie(movie2);
        sub2.setLanguage("en");
        sub2.setSubs(List.of(subtitle3, subtitle4));
        movie2.setSubList(List.of(sub2));

        subRepository.save(sub1);
        subRepository.save(sub2);
        movieRepository.save(movie1);
        movieRepository.save(movie2);

        List<MovieExt> movies = pairSubsService.getMoviesList();
        assertEquals(2, movies.size());
    }

    @Test
    void getPairSubTest() {

        Movie movie = new Movie("Nice movie", "IMDB001", 1997);
        Subtitle subtitle1 = new Subtitle(1, 10, 15, "текст1");
        Subtitle subtitle2 = new Subtitle(2, 16, 18, "текст2");
        Sub sub1 = new Sub();
        sub1.setMovie(movie);
        sub1.setLanguage("ru");
        sub1.setSubs(List.of(subtitle1, subtitle2));
        Subtitle subtitle3 = new Subtitle(1, 10, 15, "text1");
        Subtitle subtitle4 = new Subtitle(2, 16, 18, "text2");
        Sub sub2 = new Sub();
        sub2.setMovie(movie);
        sub2.setLanguage("en");
        sub2.setSubs(List.of(subtitle3, subtitle4));

        Movie movieSaved = movieRepository.save(movie);
        subRepository.save(sub1);
        subRepository.save(sub2);

        PairSub result = pairSubsService.getPairSubs(movieSaved.getId(), "ru", "en").get();

        assertEquals("Nice movie", result.getMovie().getTitle());
        assertEquals("text1", result.getSubB().getSubs().get(0).getText());


    }


}