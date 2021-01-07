package com.zserg.jpairsubs.opensubtitles;

import com.zserg.jpairsubs.opensubtitles.client.OpensubtitlesClient;
import com.zserg.jpairsubs.opensubtitles.client.OpensubtitlesClientImpl;
import com.zserg.jpairsubs.opensubtitles.model.LoginResult;
import com.zserg.jpairsubs.opensubtitles.model.OsServerInfo;
import com.zserg.jpairsubs.opensubtitles.model.SearchSubtitlesResult;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
public class OpensubtitlesClientTest {

    @Value("${opensubtitles.userAgent:TemporaryUserAgent}")
    String userAgent;


    @Test
    public void serverInfoTest(){
        OpensubtitlesClient client = new OpensubtitlesClientImpl(userAgent);
        try {
            OsServerInfo osServerInfo = client.getServerInfo();
            log.info("{}", osServerInfo);
            assertEquals("http://api.opensubtitles.org/xml-rpc", osServerInfo.getXmlrpcUrl());
        }catch (OpensubtitlesServiceException e){
            log.error("Error", e);
        }
    }

    @Test
    public void loginTest(){
        OpensubtitlesClient client = new OpensubtitlesClientImpl(userAgent);
        try {
            LoginResult token = client.login(userAgent);
            assertEquals(true, token.getToken().get().length() > 0);
        }catch (OpensubtitlesServiceException e){
            log.error("Error", e);
        }
    }

    @Test
    public void searchSubtitlesByImdbTest(){
        OpensubtitlesClient client = new OpensubtitlesClientImpl(userAgent);
        SearchSubtitlesResult result = client.searchSubtitlesByImdb("1234", "en").get();
        assertEquals("Gold Is Not All", result.getMovieName());
    }

    @Test
    public void downloadSubtitlesByImdbTest(){
        OpensubtitlesClient client = new OpensubtitlesClientImpl(userAgent);
        String srtFile = null;
        try {
            SearchSubtitlesResult result = client.searchSubtitlesByImdb("1234", "en").get();
            String link = result.getZipUrl();
            InputStream is = client.downloadFile(link);
            srtFile = client.unzipSrtFile(is);
        }catch (IOException e){
            log.error("Error", e);
        }
        assertTrue(srtFile.contains("that's the only demon"));
    }
}
