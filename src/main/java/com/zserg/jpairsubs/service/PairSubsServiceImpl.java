package com.zserg.jpairsubs.service;

import com.zserg.jpairsubs.data.MovieRepository;
import com.zserg.jpairsubs.data.SubRepository;
import com.zserg.jpairsubs.model.*;
import com.zserg.jpairsubs.utils.SRTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class PairSubsServiceImpl implements PairSubsService {
    private static final Pattern PATTERN_TIME = Pattern.compile("([\\d]{2}:[\\d]{2}:[\\d]{2},[\\d]{3}).*([\\d]{2}:[\\d]{2}:[\\d]{2},[\\d]{3})");
    private static final Pattern PATTERN_NUMBERS = Pattern.compile("(\\d+)");

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
                .peek(System.out::println)
                .map(MovieExt::new)
                .peek(System.out::println)
                .collect(Collectors.toList());
    }

    @Override
    public List<Subtitle> parseSrt(String srtAsOneString) throws Exception {
        List<String> srt = Arrays.asList(srtAsOneString.split("\\r?\\n"));
        return parseSrt(srt);
    }

    @Override
    public List<Subtitle> parseSrt(List<String> srt) throws Exception {
        List<Subtitle> subtitleList = new ArrayList<>();
        Subtitle sub = new Subtitle();
        StringBuilder sb = new StringBuilder();
        List<String> textList = new ArrayList<>();

        boolean flag = false;

        for (String line : srt) {
            Matcher matcher = PATTERN_NUMBERS.matcher(line);
            if (matcher.matches()) {
                sub = new Subtitle();
                sub.setNumber(Integer.parseInt(matcher.group(1)));
                continue;
            }

            matcher = PATTERN_TIME.matcher(line);
            if (matcher.matches()) {
                sub.setStart(SRTUtils.textTimeToMillis(matcher.group(1)));
                sub.setEnd(SRTUtils.textTimeToMillis(matcher.group(2)));
                textList = new ArrayList<>();
                flag = true;
                continue;
            }

            if(line.equals("")){
                sub.setText(String.join(System.lineSeparator(), textList));
                subtitleList.add(sub);
                flag = false;
            }else{
                textList.add(line);
            }
        }

        if (flag) {
            sub.setText(String.join(System.lineSeparator(), textList));
            subtitleList.add(sub);
        }
        return subtitleList;

    }
}
