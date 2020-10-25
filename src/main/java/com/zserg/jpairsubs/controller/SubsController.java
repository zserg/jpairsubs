package com.zserg.jpairsubs.controller;

import com.zserg.jpairsubs.model.PairSubs;
import com.zserg.jpairsubs.service.PairSubsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class SubsController {

    @Autowired
    PairSubsService pairSubsService;

    @GetMapping("subs")
    public List<PairSubs> getPairSubsList(){
        return pairSubsService.getPairSubsList();
    }

    @GetMapping("subs/{id}")
    public List<String[]> getPairSubs(@PathVariable("id") long id) {
        return pairSubsService.getPairSubs(id);
    }
}
