package com.zserg.jpairsubs.opensubtitles.client;

import com.zserg.jpairsubs.model.Sub;
import com.zserg.jpairsubs.opensubtitles.OpensubtitlesServiceException;
import com.zserg.jpairsubs.opensubtitles.model.LoginResult;
import com.zserg.jpairsubs.opensubtitles.model.OsServerInfo;
import com.zserg.jpairsubs.opensubtitles.model.SearchSubtitlesResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

@Slf4j
@Service
public class OpensubtitlesClientImpl implements OpensubtitlesClient {
    private static final String XMLRPC_URL = "http://api.opensubtitles.org/xml-rpc";

    public OsServerInfo getServerInfo() throws OpensubtitlesServiceException {
        try {
            XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
            config.setServerURL(new URL(XMLRPC_URL));
            XmlRpcClient client = new XmlRpcClient();
            client.setConfig(config);

            Map<String, Object> result = (Map<String, Object>) client.execute("ServerInfo", new ArrayList());
            OsServerInfo info = new OsServerInfo(result);
            return info;
        } catch (Exception e) {
            throw new OpensubtitlesServiceException("Get ServerInfo exception", e);
        }

    }

    public LoginResult login(String userAgent) throws OpensubtitlesServiceException {
        try {
            XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
            config.setServerURL(new URL(XMLRPC_URL));
            XmlRpcClient client = new XmlRpcClient();
            client.setConfig(config);

            Map<String, String> params = new HashMap<>();
            params.put("useragent", userAgent);

            Map<String, Object> result = (Map<String, Object>) client.execute("LogIn", new Object[] {"", "", "en", userAgent});
            LoginResult login = new LoginResult(result);
            return login;
        } catch (Exception e) {
            throw new OpensubtitlesServiceException("Exception while login", e);
        }

    }

    public SearchSubtitlesResult searchSubtitlesByImdb(String token, String imdb, String language) throws OpensubtitlesServiceException {
        try {
            XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
            config.setServerURL(new URL(XMLRPC_URL));
            XmlRpcClient client = new XmlRpcClient();
            client.setConfig(config);

            Map<String, String> searchParams = new HashMap<>();
            searchParams.put("sublanguageid", language);
            searchParams.put("imdbid", imdb);

            Object[] params = new Object[] {token, new Object[]{searchParams}};
            Map<String, Object> result = (Map<String, Object>) client.execute("SearchSubtitles", params);
            SearchSubtitlesResult info = new SearchSubtitlesResult(result);
            return info;
        } catch (Exception e) {
            throw new OpensubtitlesServiceException("Get ServerInfo exception", e);
        }

    }

    @Override
    public byte[] downloadFile(String url) throws IOException {
        PipedOutputStream osPipe = new PipedOutputStream();
        PipedInputStream isPipe = new PipedInputStream(osPipe);

        WebClient client = WebClient.builder()
                .baseUrl(url)
                .build();
        Flux<DataBuffer> dataBufferFlux = client.get().retrieve().bodyToFlux(DataBuffer.class);
        DataBufferUtils.write(dataBufferFlux, osPipe).subscribe(DataBufferUtils.releaseConsumer());
        return isPipe.readAllBytes();
    }


    public Sub downloadSub(String imdb, String language) throws OpensubtitlesServiceException {
        return null;
    }


    public String unzipSrtFile(InputStream inputStream) throws IOException {
        byte[] buffer = new byte[1024];
        ZipInputStream zis = new ZipInputStream(inputStream);
        ZipEntry zipEntry = zis.getNextEntry();
        while (zipEntry != null) {
            log.info("file name in archive: {}", zipEntry.getName());
            if(zipEntry.getName().matches("^.*\\.(srt|SRT)$")) {
                log.info("file found: {}", zipEntry.getName());
                ByteArrayOutputStream fos = new ByteArrayOutputStream();
                int len;
                while ((len = zis.read(buffer)) > 0) {
                    fos.write(buffer, 0, len);
                }
                fos.close();
                return fos.toString();
            }else{
                zipEntry = zis.getNextEntry();
            }
        }
        zis.closeEntry();
        zis.close();
        log.info("Not found SRT file in zip archive");
        return null;
    }
}
