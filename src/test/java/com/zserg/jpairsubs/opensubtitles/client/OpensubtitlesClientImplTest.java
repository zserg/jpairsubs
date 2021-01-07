package com.zserg.jpairsubs.opensubtitles.client;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class OpensubtitlesClientImplTest {

    @Value("${opensubtitles.userAgent:TemporaryUserAgent}")
    String userAgent;

    @Test
    void unzipSrtFileTest() throws IOException {
        String path = "src/test/resources/file-with-srt.zip";
        File file = new File(path);
        FileInputStream fileInputStream = new FileInputStream(file);


        OpensubtitlesClientImpl client = new OpensubtitlesClientImpl(userAgent);
        String srtContent = client.unzipSrtFile(fileInputStream);
        assertTrue(srtContent.contains("WHEN NO ONE ELSE IS LEFT"));


    }
}