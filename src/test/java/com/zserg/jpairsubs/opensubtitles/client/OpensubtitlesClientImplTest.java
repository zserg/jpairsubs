package com.zserg.jpairsubs.opensubtitles.client;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class OpensubtitlesClientImplTest {

    @Test
    void unzipSrtFileTest() throws IOException {
        String path = "src/test/resources/file-with-srt.zip";
        File file = new File(path);
        FileInputStream fileInputStream = new FileInputStream(file);


        OpensubtitlesClientImpl client = new OpensubtitlesClientImpl();
        String srtContent = client.unzipSrtFile(fileInputStream);
        assertTrue(srtContent.contains("WHEN NO ONE ELSE IS LEFT"));


    }
}