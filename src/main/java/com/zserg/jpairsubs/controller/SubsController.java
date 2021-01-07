package com.zserg.jpairsubs.controller;

import com.zserg.jpairsubs.model.Movie;
import com.zserg.jpairsubs.model.MovieExt;
import com.zserg.jpairsubs.model.PairSub;
import com.zserg.jpairsubs.opensubtitles.service.OpensubtitlesService;
import com.zserg.jpairsubs.service.PairSubsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1")
public class SubsController {

    @Autowired
    private PairSubsService pairSubsService;

    @Autowired
    private OpensubtitlesService opensubtitlesService;

    @GetMapping("movies")
    public List<MovieExt> getMoviesList() {
        return pairSubsService.getMoviesList();
    }

    @GetMapping("pairsubs")
    public PairSub getPairSubs(@RequestParam("movie") long movieId,
                               @RequestParam("lang") String[] languages) {
        if (languages.length != 2) {
            throw new BadRequestException();
        }

        return pairSubsService.getPairSubs(movieId, languages[0], languages[1])
                .orElseThrow(PairSubsNotFoundException::new);
    }

    @PostMapping("download")
    public PairSub searchAndDownloadPairSubs(@RequestParam("imdb") String imdb,
                                             @RequestParam("lang") String[] languages) {
        if (languages.length != 2) {
            throw new BadRequestException();
        }

        return opensubtitlesService.searchMovie(imdb, languages[0], languages[1])
                .map(movie -> {
                    PairSub pairSub = new PairSub();
                    pairSub.setMovie(movie);
                    opensubtitlesService.downloadSub(imdb, languages[0])
                            .ifPresent(pairSub::setSubA);
                    opensubtitlesService.downloadSub(imdb, languages[1])
                            .ifPresent(pairSub::setSubB);
                    pairSubsService.storePairSubs(pairSub);
                    return pairSub;
                })
                .orElseThrow(() -> new PairSubsNotFoundException());
    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "pairsubs not found")  // 404
    public class PairSubsNotFoundException extends RuntimeException {
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "invalid request")  // 404
    public class BadRequestException extends RuntimeException {
    }


}
