package com.zserg.jpairsubs.service;

import com.zserg.jpairsubs.model.Movie;
import com.zserg.jpairsubs.model.PairSub;

import java.util.List;

public interface PairSubsService {
    List<PairSub> getPairSubsList();

    PairSub getPairSubs(long id, String[] languages);

    List<Movie> getMoviesList();
}
