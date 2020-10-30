package com.zserg.jpairsubs.opensubtitles.client;

import com.zserg.jpairsubs.opensubtitles.OpensubtitlesServiceException;
import com.zserg.jpairsubs.opensubtitles.model.LoginResult;
import com.zserg.jpairsubs.opensubtitles.model.OsServerInfo;
import com.zserg.jpairsubs.opensubtitles.model.SearchSubtitlesResult;

public interface OpensubtitlesClient {
    public OsServerInfo getServerInfo() throws OpensubtitlesServiceException;
    public LoginResult login(String userAgent) throws OpensubtitlesServiceException;
    public SearchSubtitlesResult searchSubtitlesByImdb(String token, String imdb, String language) throws OpensubtitlesServiceException;
}
