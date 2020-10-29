package com.zserg.jpairsubs.service;

import com.zserg.jpairsubs.data.MovieRepository;
import com.zserg.jpairsubs.data.PairSubRepository;
import com.zserg.jpairsubs.model.Movie;
import com.zserg.jpairsubs.model.PairSub;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PairSubsServiceImpl implements PairSubsService {

    private final MovieRepository movieRepository;
    private final PairSubRepository pairSubRepository;


    @Autowired
    public PairSubsServiceImpl(MovieRepository repository, PairSubRepository pairSubRepository){
        this.movieRepository = repository;
        this.pairSubRepository = pairSubRepository;
    }

    @Override
    public List<PairSub> getPairSubsList() {
        return null;
    }

    @Override
    public PairSub getPairSubs(Long movieId, String langA, String langB) {
        List<PairSub> byMovie = pairSubRepository.findByMovieId(movieId);

        return byMovie.get(0);
    }

    @Override
    public List<Movie> getMoviesList() {
        return movieRepository.findAll();
    }
}
