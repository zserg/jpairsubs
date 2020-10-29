package com.zserg.jpairsubs.service;

import com.zserg.jpairsubs.data.MovieRepository;
import com.zserg.jpairsubs.data.SubRepository;
import com.zserg.jpairsubs.model.Movie;
import com.zserg.jpairsubs.model.MovieExt;
import com.zserg.jpairsubs.model.PairSub;
import com.zserg.jpairsubs.model.Sub;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PairSubsServiceImpl implements PairSubsService {

    private final MovieRepository movieRepository;
    private final SubRepository pairSubRepository;


    @Autowired
    public PairSubsServiceImpl(MovieRepository repository, SubRepository pairSubRepository) {
        this.movieRepository = repository;
        this.pairSubRepository = pairSubRepository;
    }

    @Override
    public List<PairSub> getPairSubsList() {
        return null;
    }

    @Override
    public Optional<PairSub> getPairSubs(Long movieId, String langA, String langB) {
        return movieRepository.findById(movieId)
                .map(movie -> {
                    Sub subA = pairSubRepository.findByMovieIdAndLanguage(movieId, langA);
                    Sub subB = pairSubRepository.findByMovieIdAndLanguage(movieId, langB);
                    return new PairSub(movie, subA, subB);
                })
                .filter(pairSub -> pairSub.getSubA() != null && pairSub.getSubB() != null);
    }

    @Override
    public List<MovieExt> getMoviesList() {
        return movieRepository.findAll().stream()
                .peek(e -> System.out.println(e))
                .map(MovieExt::new)
                .peek(e -> System.out.println(e))
                .collect(Collectors.toList());
    }
}
