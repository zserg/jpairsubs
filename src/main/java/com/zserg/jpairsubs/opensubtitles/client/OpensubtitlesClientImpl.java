package com.zserg.jpairsubs.opensubtitles.client;

import com.zserg.jpairsubs.model.Sub;
import com.zserg.jpairsubs.opensubtitles.OpensubtitlesServiceException;
import com.zserg.jpairsubs.opensubtitles.model.LoginResult;
import com.zserg.jpairsubs.opensubtitles.model.OsServerInfo;
import com.zserg.jpairsubs.opensubtitles.model.SearchSubtitlesResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Slf4j
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


    public Sub downloadSub(String imdb, String language) throws OpensubtitlesServiceException {
        return null;
    }

}
