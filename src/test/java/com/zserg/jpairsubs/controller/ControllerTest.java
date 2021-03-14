package com.zserg.jpairsubs.controller;

import com.zserg.jpairsubs.model.*;
import com.zserg.jpairsubs.service.PairSubsService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.autoconfigure.web.servlet.*;
import org.springframework.boot.test.mock.mockito.*;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebFluxTest
public class ControllerTest {

    @Autowired
    private WebTestClient webClient;

    @MockBean
    PairSubsService pairSubsService;

    @Test
    void getMoviesList() throws Exception {
        MovieExt movie1 = new MovieExt(1L, "Nice movie", "IMDB001", 1997, List.of("ru", "en", "fr"));
        MovieExt movie2 = new MovieExt(2L, "Nice movie 2", "IMDB002", 1997, List.of("ru", "en"));
        when(pairSubsService.getMoviesList()).thenReturn(List.of(movie1, movie2));

        this.webClient.get().uri("/api/v1/movies")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.length()", is(2)).hasJsonPath()
                .jsonPath("$.[0].title", is("Nice movie")).hasJsonPath()
                .jsonPath("$.[0].imdb", is("IMDB001")).hasJsonPath()
                .jsonPath("$.[0].year", is(1997)).hasJsonPath()
                .jsonPath("$.[0].languages.length()", is(3)).hasJsonPath()
                .jsonPath("$.[0].languages.[0]", is("ru")).hasJsonPath();
    }

    @Test
    void getPairSubsTest() throws Exception {
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

        PairSub pairSubs = new PairSub();
        pairSubs.setMovie(movie);
        pairSubs.setSubA(sub1);
        pairSubs.setSubB(sub2);

        when(pairSubsService.getPairSubs(1L, "ru", "en")).thenReturn(Optional.of(pairSubs));

        this.webClient.get().uri("/api/v1/pairsubs?movie=1&lang=ru,en")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.movie.title", is("Nice movie")).hasJsonPath()
                .jsonPath("$.movie.year", is(1997)).hasJsonPath()
                .jsonPath("$.subA.language", is("ru")).hasJsonPath()
                .jsonPath("$.subA.subs.[0].text", is("текст1")).hasJsonPath();
    }

    @Test
    void pairSubsNotFoundTest() throws Exception {

        when(pairSubsService.getPairSubs(1L, "ru", "it")).thenReturn(Optional.empty());

        this.webClient.get().uri("/api/v1/pairsubs?movie=1&lang=ru,it")
                .exchange()
                .expectStatus().isNotFound();

    }

}

