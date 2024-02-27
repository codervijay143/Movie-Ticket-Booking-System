package com.movie.ticket.booking.system.apis;

import com.movie.ticket.booking.system.dtos.MovieDTO;
import com.movie.ticket.booking.system.exceptions.MovieNotFoundException;
import com.movie.ticket.booking.system.services.MovieService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("movies")
@RequiredArgsConstructor
public class MovieAPI {

    private final MovieService movieService;

    @PostMapping
    public MovieDTO createMovie(@Valid @RequestBody MovieDTO movieDTO){
      return  this.movieService.createMovie(movieDTO);
    }

    @GetMapping("/{movieId}")
    public MovieDTO movieById(@PathVariable Integer movieId) throws MovieNotFoundException {
        return this.movieService.movieById(movieId);
    }

    @GetMapping
    public List<MovieDTO> getMoviesList(){
        return this.movieService.getMoviesList();
    }
}
