package com.zserg.jpairsubs.service;

import com.zserg.jpairsubs.data.MovieRepository;
import com.zserg.jpairsubs.data.PairSubRepository;
import com.zserg.jpairsubs.model.Movie;
import com.zserg.jpairsubs.model.PairSub;
import com.zserg.jpairsubs.model.Sub;
import com.zserg.jpairsubs.model.Subtitle;
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
    PairSubRepository pairSubRepository;

    @Autowired
    PairSubsService pairSubsService;

    @Test
    void getMoviesList() {
        movieRepository.save(new Movie("Movie #1", "IMDB001", 1990));
        movieRepository.save(new Movie("Movie #2", "IMDB002", 2000));

        List<Movie> movies = pairSubsService.getMoviesList();
        assertEquals(2, movies.size());
    }

    @Test
    void getPairSubTest() {

        Movie movie = new Movie("Nice movie", "IMDB001", 1997);
        Subtitle subtitle1 = new Subtitle(1, 10, 15, "текст1");
        Subtitle subtitle2 = new Subtitle(2, 16, 18, "текст2");
        Sub sub1 = new Sub();
        sub1.setLanguage("ru");
        sub1.setSubs(List.of(subtitle1, subtitle2));
        Subtitle subtitle3 = new Subtitle(1, 10, 15, "text1");
        Subtitle subtitle4 = new Subtitle(2, 16, 18, "text2");
        Sub sub2 = new Sub();
        sub2.setLanguage("en");
        sub2.setSubs(List.of(subtitle3, subtitle4));

        PairSub pairSubs = new PairSub();
        pairSubs.setMovie(movie);
        pairSubs.setSubA(sub1);
        pairSubs.setSubB(sub2);

        Movie movieSaved = movieRepository.save(movie);
        PairSub saved = pairSubRepository.save(pairSubs);

        PairSub result = pairSubsService.getPairSubs(movieSaved.getId(), "ru", "en");

        assertEquals("Nice movie", result.getMovie().getTitle());

    }


}