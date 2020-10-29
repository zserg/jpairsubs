package com.zserg.jpairsubs.data;

import com.zserg.jpairsubs.model.Sub;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SubRepository extends CrudRepository<Sub, Long> {

    @Override
    List<Sub> findAll();

    Sub findByMovieIdAndLanguage(Long movieId, String lang);

}
