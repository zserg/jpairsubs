package com.zserg.jpairsubs.opensubtitles;

import com.zserg.jpairsubs.opensubtitles.model.OsServerInfo;
import com.zserg.jpairsubs.opensubtitles.service.OpensubtitlesService;
import com.zserg.jpairsubs.opensubtitles.service.OpensubtitlesServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@Slf4j
public class OpensubtitlesServiceTest {
//    @Test
//    public void searchByImdb(){
//        OsServerInfo osServerInfo = opensubtitlesService.getServerInfo();
//        assertEquals("http://www.opensubtitles.org", osServerInfo.getUrl());
//    }

    @Test
    public void serverInfoTest(){
        OpensubtitlesService service = new OpensubtitlesServiceImpl();
        try {
            OsServerInfo osServerInfo = service.getServerInfo();
            assertEquals("http://api.opensubtitles.org/xml-rpc", osServerInfo.getXmlrpcUrl());
        }catch (OpensubtitlesServiceException e){
            log.error("Error", e);
        }
    }
}
