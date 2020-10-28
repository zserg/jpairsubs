package com.zserg.jpairsubs.data;

import com.zserg.jpairsubs.model.Movie;
import com.zserg.jpairsubs.model.PairSub;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PairSubRepository extends CrudRepository<PairSub, Long> {

    @Override
    List<PairSub> findAll();
}
