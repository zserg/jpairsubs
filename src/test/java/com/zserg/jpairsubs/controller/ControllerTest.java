package com.zserg.jpairsubs.controller;

import com.zserg.jpairsubs.model.PairSubs;
import com.zserg.jpairsubs.model.SubItem;
import com.zserg.jpairsubs.service.PairSubsService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.autoconfigure.web.servlet.*;
import org.springframework.boot.test.mock.mockito.*;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
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
    void getSubsList() throws Exception {
        when(pairSubsService.getPairSubsList()).thenReturn(List.of(new PairSubs(), new PairSubs()));

        this.mvc.perform(get("/api/v1/subs"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", is(2)));
    }

    @Test
    void getSubsListAndCheck() throws Exception {
        PairSubs pairSubs = new PairSubs();
        pairSubs.setTitle("Nice movie");
        pairSubs.setYear(2020);
        pairSubs.setLang(Arrays.asList("ru", "en"));
        when(pairSubsService.getPairSubsList()).thenReturn(List.of(pairSubs));

        this.mvc.perform(get("/api/v1/subs"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].title", is("Nice movie")))
                .andExpect(jsonPath("$.[0].lang.[0]", is("ru")))
                .andExpect(jsonPath("$.[0].lang.[1]", is("en")))
                .andExpect(jsonPath("$.[0].year", is(2020)));
    }

    @Test
    void getSubs() throws Exception {
        List<String[]> list = new ArrayList<>();
        list.add(new String[]{"Привет", "Hello"});
        list.add(new String[]{"1", "2"});
        when(pairSubsService.getPairSubs(1L)).thenReturn(list);

        this.mvc.perform(get("/api/v1/subs/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", is(2)))
                .andExpect(jsonPath("$.[0].length()", is(2)))
                .andExpect(jsonPath("$.[0].[0]", is("Привет")))
                .andExpect(jsonPath("$.[0].[1]", is("Hello")));
    }




}

