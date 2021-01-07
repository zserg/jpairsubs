package com.zserg.jpairsubs.opensubtitles.service;

import com.zserg.jpairsubs.model.Movie;
import com.zserg.jpairsubs.model.Sub;
import com.zserg.jpairsubs.opensubtitles.OpensubtitlesServiceException;

import java.util.Optional;

public interface OpensubtitlesService {
    Optional<Movie> searchMovie(String imdb, String language) throws OpensubtitlesServiceException;

    Optional<Movie> searchMovie(String imdb, String lang1, String lang2);

    Optional<Sub> downloadSub(String imdb, String language);
}
