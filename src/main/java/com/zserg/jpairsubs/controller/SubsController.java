package com.zserg.jpairsubs.controller;

import com.zserg.jpairsubs.model.Movie;
import com.zserg.jpairsubs.model.MovieExt;
import com.zserg.jpairsubs.model.PairSub;
import com.zserg.jpairsubs.service.PairSubsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1")
public class SubsController {

    @Autowired
    PairSubsService pairSubsService;

    @GetMapping("movies")
    public List<MovieExt> getMoviesList(){
        return pairSubsService.getMoviesList();
    }

    @GetMapping("pairsubs")
    public PairSub getPairSubs(@RequestParam("movie") long movieId,
                                     @RequestParam("lang") String[] languages){
        if(languages.length != 2){
            throw new BadRequestException();
        }

        Optional<PairSub> pairSubs = pairSubsService.getPairSubs(movieId, languages[0], languages[1]);
        if(!pairSubs.isPresent()){
            throw new PairSubsNotFoundException();
        }else{
           return pairSubs.get();
        }
    }

    @ResponseStatus(value= HttpStatus.NOT_FOUND, reason="pairsubs not found")  // 404
    public class PairSubsNotFoundException extends RuntimeException {
    }

    @ResponseStatus(value= HttpStatus.BAD_REQUEST, reason="invalid request")  // 404
    public class BadRequestException extends RuntimeException {
    }


}
