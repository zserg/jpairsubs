package com.zserg.jpairsubs.model;

import org.junit.jupiter.api.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import static org.junit.jupiter.api.Assertions.*;

class SubTest {

    @Test
    public void movieModelTest(){
        Movie movie = new Movie("Nice movie", "IMDB0123456", 1997, new String[]{"en", "ru"});
        assertThat("Nice movie", equalTo(movie.getTitle()));
    }

}