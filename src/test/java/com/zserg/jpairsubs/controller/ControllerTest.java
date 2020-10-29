package com.zserg.jpairsubs.controller;

import com.zserg.jpairsubs.model.Movie;
import com.zserg.jpairsubs.model.PairSub;
import com.zserg.jpairsubs.model.Sub;
import com.zserg.jpairsubs.model.Subtitle;
import com.zserg.jpairsubs.service.PairSubsService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.autoconfigure.web.servlet.*;
import org.springframework.boot.test.mock.mockito.*;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest
public class ControllerTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    PairSubsService pairSubsService;

    @Test
    void getMoviesList() throws Exception {
        Movie movie1 = new Movie("Nice movie", "IMDB001", 1997);
        Movie movie2 = new Movie("Nice movie 2", "IMDB002", 1997);
        when(pairSubsService.getMoviesList()).thenReturn(List.of(movie1, movie2));

        this.mvc.perform(get("/api/v1/movies"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", is(2)))
                .andExpect(jsonPath("$.[0].title", is("Nice movie")))
                .andExpect(jsonPath("$.[0].imdb", is("IMDB001")))
                .andExpect(jsonPath("$.[0].year", is(1997)))
                .andExpect(jsonPath("$.[0].lang.length()", is(3)))
                .andExpect(jsonPath("$.[0].lang.[0]", is("ru")));
    }

    @Test
    void getPairSubsTest() throws Exception {
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

        when(pairSubsService.getPairSubs(1L, "ru", "en")).thenReturn(pairSubs);

        this.mvc.perform(get("/api/v1/pairsubs?movie=1&lang=ru,en"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.movie.title", is("Nice movie")))
                .andExpect(jsonPath("$.movie.year", is(1997)))
                .andExpect(jsonPath("$.subA.language", is("ru")))
                .andExpect(jsonPath("$.subA.subs.[0].text", is("текст1")));
    }

    @Test
    void pairSubsNotFoundTest() throws Exception {

        when(pairSubsService.getPairSubs(1L, "ru", "it")).thenThrow(new IllegalArgumentException("not found"));

        this.mvc.perform(get("/api/v1/pairsubs?movie=1&lang=ru,it"))
                .andExpect(status().isNotFound());

    }

}

