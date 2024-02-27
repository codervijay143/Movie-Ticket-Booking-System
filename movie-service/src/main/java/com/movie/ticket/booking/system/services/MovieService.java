package com.movie.ticket.booking.system.services;

import com.movie.ticket.booking.system.dtos.MovieDTO;
import com.movie.ticket.booking.system.exceptions.MovieNotFoundException;

import java.util.List;

public interface MovieService {

    MovieDTO createMovie(MovieDTO movieDTO);
    MovieDTO movieById(Integer movieId) throws MovieNotFoundException;

    List<MovieDTO> getMoviesList();
}
