package com.zserg.jpairsubs.opensubtitles.model;

import lombok.Getter;

import java.util.*;

@Getter
public class SearchSubtitlesResult {
    private String movieName;
    private String language;
    private int year;
    private String zipUrl;
    private String status;

    public SearchSubtitlesResult(Map<String, Object> map){
        if (map.containsKey("status")) {
            this.status = (String) map.get("status");
        }
        if (map.containsKey("data")) {
            Object[] listOfSubs = (Object[]) map.get("data");
            if(listOfSubs.length > 0) {
                Map data = Arrays.stream(listOfSubs)
                        .map(o -> (Map) o)
                        .max(Comparator.comparingDouble(e -> (Double) e.get("Score")))
                        .get();
                this.movieName = (String) data.get("MovieName");
                this.language = (String) data.get("ISO639");
                this.year = Integer.valueOf((String) data.get("MovieYear"));
                this.zipUrl = (String) data.get("ZipDownloadLink");
            }
        }

    }
}
