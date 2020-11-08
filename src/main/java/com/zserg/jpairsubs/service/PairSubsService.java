package com.zserg.jpairsubs.service;

import com.zserg.jpairsubs.model.*;

import java.util.List;
import java.util.Optional;

public interface PairSubsService {
    List<PairSub> getPairSubsList();

    Optional<PairSub> getPairSubs(Long movieId, String langA, String langB);

    List<MovieExt> getMoviesList();

    List<Subtitle> parseSrt(List<String> srt) throws Exception;
}
