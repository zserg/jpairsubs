package com.zserg.jpairsubs.opensubtitles.service;

import com.zserg.jpairsubs.model.Movie;
import com.zserg.jpairsubs.model.Sub;
import com.zserg.jpairsubs.model.Subtitle;
import com.zserg.jpairsubs.opensubtitles.OpensubtitlesServiceException;
import com.zserg.jpairsubs.opensubtitles.client.OpensubtitlesClient;
import com.zserg.jpairsubs.opensubtitles.model.SearchSubtitlesResult;
import com.zserg.jpairsubs.service.PairSubsService;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;

@Slf4j
public class OpensubtitlesServiceImpl implements OpensubtitlesService {
    private static final String XMLRPC_URL = "https://api.opensubtitles.org/xml-rpc";

    private final OpensubtitlesClient client;
    private final PairSubsService pairSubsService;

    public OpensubtitlesServiceImpl(OpensubtitlesClient client, PairSubsService pairSubsService) {
        this.client = client;
        this.pairSubsService = pairSubsService;
    }

    @Override
    public Optional<Movie> searchMovie(String imdb, String language) throws OpensubtitlesServiceException {
        return client.searchSubtitlesByImdb(imdb, language)
                .map(result -> {
                    Movie movie = new Movie();
                    movie.setImdb(imdb);
                    movie.setTitle(result.getMovieName());
                    movie.setYear(result.getYear());
                    return movie;
                });
    }

    @Override
    public Optional<Sub> downloadSub(String imdb, String language) {

        return client.downloadSubtitle(imdb, language)
                .map(subs -> {
                    try {
                        List<Subtitle> subtitles = pairSubsService.parseSrt(subs);
                        return new Sub(imdb, language, subtitles);
                    } catch (Exception e) {
                        log.error("error", e);
                        return null;
                    }
                });
    }

}
