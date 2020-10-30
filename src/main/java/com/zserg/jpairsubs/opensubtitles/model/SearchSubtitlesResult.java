package com.zserg.jpairsubs.opensubtitles.model;

import lombok.Getter;

import java.util.List;
import java.util.Map;

@Getter
public class SearchSubtitlesResult {
    private List<Object> subtitles;
    private String status;

    public SearchSubtitlesResult(Map<String, Object> map){
        if (map.containsKey("status")) {
            this.status = (String) map.get("status");
        }
        if (map.containsKey("data")) {
            this.status = (String) map.get("status");
        }

    }
}
