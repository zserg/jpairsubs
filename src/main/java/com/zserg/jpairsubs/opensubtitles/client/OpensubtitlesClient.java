package com.zserg.jpairsubs.opensubtitles.client;

import com.zserg.jpairsubs.opensubtitles.OpensubtitlesServiceException;
import com.zserg.jpairsubs.opensubtitles.model.LoginResult;
import com.zserg.jpairsubs.opensubtitles.model.OsServerInfo;
import com.zserg.jpairsubs.opensubtitles.model.SearchSubtitlesResult;

import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

public interface OpensubtitlesClient {
    OsServerInfo getServerInfo() throws OpensubtitlesServiceException;
    LoginResult login(String userAgent) throws OpensubtitlesServiceException;
    Optional<SearchSubtitlesResult> searchSubtitlesByImdb(String imdb, String language);
    InputStream downloadFile(String url) throws IOException;
    String unzipSrtFile(InputStream inputStream) throws IOException;
    Optional<String> downloadSubtitle(String imdb, String language);

}
