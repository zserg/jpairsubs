package com.zserg.jpairsubs.opensubtitles.service;

import com.zserg.jpairsubs.opensubtitles.client.OpensubtitlesClient;
import com.zserg.jpairsubs.service.PairSubsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.core.AutoConfigureCache;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@TestConfiguration
@ComponentScan(lazyInit = true)
public class TestConfig {

//    @Bean
//    OpensubtitlesService getOpensubtitlesService(OpensubtitlesClient client, PairSubsService pairSubsService){
//        return new OpensubtitlesServiceImpl(client, pairSubsService);
//    }
}
