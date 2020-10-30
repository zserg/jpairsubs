package com.zserg.jpairsubs.opensubtitles.service;

import com.zserg.jpairsubs.model.Sub;
import com.zserg.jpairsubs.opensubtitles.OpensubtitlesServiceException;
import com.zserg.jpairsubs.opensubtitles.model.OsServerInfo;

public interface OpensubtitlesService {
    public OsServerInfo getServerInfo() throws OpensubtitlesServiceException;
    public Sub downloadSub(String imdb, String language) throws OpensubtitlesServiceException;
}
