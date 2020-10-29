package com.zserg.jpairsubs.data;

import com.zserg.jpairsubs.model.Movie;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MovieRepository extends CrudRepository<Movie, Long> {

    @Override
    List<Movie> findAll();

}
