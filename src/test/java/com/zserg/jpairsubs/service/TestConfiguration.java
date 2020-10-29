package com.zserg.jpairsubs.service;

import com.zserg.jpairsubs.data.MovieRepository;
import com.zserg.jpairsubs.data.SubRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

public class TestConfiguration {

    @Bean
    PairSubsService pairSubsService(@Autowired MovieRepository repository,
                                    @Autowired SubRepository pairSubRepository){
        return new PairSubsServiceImpl(repository, pairSubRepository);
    }
}
