package com.zserg.jpairsubs.opensubtitles.model;

import lombok.Getter;
import lombok.ToString;

import java.util.Map;

@Getter
@ToString
public class OsServerInfo {
    private int client24hDownloadLimit;
    private int client24hDownloadCount;
    private String xmlrpcUrl;

    public OsServerInfo(Map<String, Object> map){
        if(map.containsKey("download_limits")){
            Map<String, Object> limits = (Map<String, Object>) map.get("download_limits");
            if(limits.containsKey("client_24h_download_limit")){
                this.client24hDownloadLimit = (int) limits.get("client_24h_download_limit");
            }
            if(limits.containsKey("client_24h_download_count")){
                this.client24hDownloadCount = (int) limits.get("client_24h_download_count");
            }
        }
        if(map.containsKey("xmlrpc_url")) {
            this.xmlrpcUrl = (String) map.get("xmlrpc_url");
        }
    }
}
