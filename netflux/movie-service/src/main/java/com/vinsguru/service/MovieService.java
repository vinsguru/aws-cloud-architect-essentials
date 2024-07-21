package com.vinsguru.service;

import com.vinsguru.domain.Genre;
import com.vinsguru.dto.MovieDto;
import com.vinsguru.mapper.EntityDtoMapper;
import com.vinsguru.repository.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {

    private final MovieRepository repository;

    public MovieService(MovieRepository repository) {
        this.repository = repository;
    }

    public List<MovieDto> getAll() {
        return this.repository.findAll()
                              .stream()
                              .map(EntityDtoMapper::toDto)
                              .toList();
    }

    public List<MovieDto> getAll(Genre genre) {
        return this.repository.findByGenre(genre)
                              .stream()
                              .map(EntityDtoMapper::toDto)
                              .toList();
    }

}
