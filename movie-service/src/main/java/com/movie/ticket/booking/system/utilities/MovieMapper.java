package com.movie.ticket.booking.system.utilities;

import com.movie.ticket.booking.system.dtos.MovieDTO;
import com.movie.ticket.booking.system.entities.MovieEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MovieMapper {

    MovieMapper INSTANCE = Mappers.getMapper(MovieMapper.class);

    MovieDTO entityToDTO(MovieEntity movieEntity);
    MovieEntity DTOtoEntity(MovieDTO movieDTO);
    MovieDTO entityToDTOWithDetails(MovieEntity movieEntity);
}
