package com.vinsguru.mapper;

import com.vinsguru.dto.MovieDto;
import com.vinsguru.entity.Movie;

public class EntityDtoMapper {

    public static MovieDto toDto(Movie movie){
        return new MovieDto(
                movie.getId(),
                movie.getTitle(),
                movie.getReleaseYear(),
                movie.getGenre()
        );
    }

}
