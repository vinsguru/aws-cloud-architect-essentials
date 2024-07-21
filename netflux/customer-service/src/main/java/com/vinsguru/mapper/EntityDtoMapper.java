package com.vinsguru.mapper;

import com.vinsguru.dto.CustomerDto;
import com.vinsguru.dto.MovieDto;
import com.vinsguru.entity.Customer;

import java.util.List;

public class EntityDtoMapper {

    public static CustomerDto toDto(Customer customer, List<MovieDto> movies){
        return new CustomerDto(
                customer.getId(),
                customer.getName(),
                customer.getFavoriteGenre(),
                movies
        );
    }

}
