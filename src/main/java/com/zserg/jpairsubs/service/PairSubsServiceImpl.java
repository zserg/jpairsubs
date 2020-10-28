package com.zserg.jpairsubs.service;

import com.zserg.jpairsubs.data.MovieRepository;
import com.zserg.jpairsubs.model.Movie;
import com.zserg.jpairsubs.model.PairSub;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PairSubsServiceImpl implements PairSubsService {

    private final MovieRepository repository;

    @Autowired
    public PairSubsServiceImpl(MovieRepository repository){
        this.repository = repository;
    }

    @Override
    public List<PairSub> getPairSubsList() {
        return null;
    }

    @Override
    public PairSub getPairSubs(long id, String[] languages) {
        return null;
    }

    @Override
    public List<Movie> getMoviesList() {
        return repository.findAll();
    }
}
