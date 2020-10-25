package com.zserg.jpairsubs.service;

import com.zserg.jpairsubs.model.PairSubs;

import java.util.List;

public interface PairSubsService {
    List<PairSubs> getPairSubsList();

    List<String[]> getPairSubs(long id);
}
