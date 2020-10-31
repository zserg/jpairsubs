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

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
@SpringBootTest
public class OpensubtitlesClientTest {

    @Value("${opensubtitles.userAgent:TemporaryUserAgent}")
    String userAgent;


    @Test
    public void serverInfoTest(){
        OpensubtitlesClient client = new OpensubtitlesClientImpl();
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
        OpensubtitlesClient client = new OpensubtitlesClientImpl();
        try {
            LoginResult token = client.login(userAgent);
            assertEquals(true, token.getToken().get().length() > 0);
        }catch (OpensubtitlesServiceException e){
            log.error("Error", e);
        }
    }

    @Test
    public void searchSubtitlesByImdbTest(){
        OpensubtitlesClient client = new OpensubtitlesClientImpl();
        try {
            String token = client.login(userAgent).getToken().get();
            SearchSubtitlesResult result = client.searchSubtitlesByImdb(token, "1234", "en");
            assertEquals("gold is not all", result.getData());
        }catch (OpensubtitlesServiceException e){
            log.error("Error", e);
        }
    }
}
