package com.zserg.jpairsubs.opensubtitles.service;

import com.zserg.jpairsubs.model.Sub;
import com.zserg.jpairsubs.opensubtitles.OpensubtitlesServiceException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@Import(TestConfig.class)
@SpringBootTest
class OpensubtitlesServiceImplTest {

    @Autowired
    private OpensubtitlesService opensubtitlesService;

    @Test
    void downloadSubTest() throws OpensubtitlesServiceException {
        Sub en = opensubtitlesService.downloadSub("1234", "en").get();
        assertTrue(en.getSubs().size() > 0);
    }
}