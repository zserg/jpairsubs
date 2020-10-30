package com.zserg.jpairsubs.opensubtitles.service;

import com.zserg.jpairsubs.model.Sub;
import com.zserg.jpairsubs.opensubtitles.OpensubtitlesServiceException;
import com.zserg.jpairsubs.opensubtitles.model.OsServerInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;

import java.net.URL;
import java.util.ArrayList;
import java.util.Map;

@Slf4j
public class OpensubtitlesServiceImpl implements OpensubtitlesService {
    private static final String XMLRPC_URL = "https://api.opensubtitles.org/xml-rpc";

    @Override
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

    public Sub downloadSub(String imdb, String language) throws OpensubtitlesServiceException {
        return null;
    }

}
