package com.zserg.jpairsubs.controller;

import com.zserg.jpairsubs.model.Movie;
import com.zserg.jpairsubs.model.PairSub;
import com.zserg.jpairsubs.service.PairSubsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class SubsController {

    @Autowired
    PairSubsService pairSubsService;

    @GetMapping("movies")
    public List<Movie> getMoviesList(){
        return pairSubsService.getMoviesList();
    }

    @GetMapping("pairsubs")
    public PairSub getPairSubs(@RequestParam("movie") long movieId,
                                     @RequestParam("lang") String[] languages){
        try {
            return pairSubsService.getPairSubs(movieId, languages);
        }catch (IllegalArgumentException e){
            throw new PairSubsNotFoundException();
        }
    }

    @ResponseStatus(value= HttpStatus.NOT_FOUND, reason="No such pairsubs")  // 404
    public class PairSubsNotFoundException extends RuntimeException {
    }


}
