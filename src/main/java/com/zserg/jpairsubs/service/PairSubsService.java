package com.zserg.jpairsubs.service;

import com.zserg.jpairsubs.model.Movie;
import com.zserg.jpairsubs.model.MovieExt;
import com.zserg.jpairsubs.model.PairSub;

import java.util.List;
import java.util.Optional;

public interface PairSubsService {
    List<PairSub> getPairSubsList();

    Optional<PairSub> getPairSubs(Long movieId, String langA, String langB);

    List<MovieExt> getMoviesList();
}
