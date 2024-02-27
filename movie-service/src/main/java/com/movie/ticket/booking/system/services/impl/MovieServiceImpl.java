package com.movie.ticket.booking.system.services.impl;

import com.movie.ticket.booking.system.dtos.MovieDTO;
import com.movie.ticket.booking.system.entities.MovieEntity;
import com.movie.ticket.booking.system.exceptions.MovieNotFoundException;
import com.movie.ticket.booking.system.repositories.MovieRepository;
import com.movie.ticket.booking.system.services.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;

    @Override
    public MovieDTO createMovie(MovieDTO movieDTO) {

        MovieEntity movieEntity= MovieEntity.builder()
                .movieImageUrl(movieDTO.getMovieImageUrl())
                .movieName(movieDTO.getMovieName())
                .movieDuration(movieDTO.getMovieDuration())
                .movieLanguage(movieDTO.getMovieLanguage())
                .movieType(movieDTO.getMovieType())
                .seats(movieDTO.getSeats())
                .price(movieDTO.getPrice())
                .build();
        this.movieRepository.save(movieEntity);
        return MovieDTO.builder()
                .movieId(movieEntity.getMovieId())
                .movieImageUrl(movieEntity.getMovieImageUrl())
                .movieName(movieEntity.getMovieName())
                .movieDuration(movieEntity.getMovieDuration())
                .movieLanguage(movieEntity.getMovieLanguage())
                .movieType(movieEntity.getMovieType())
                .seats(movieDTO.getSeats())
                .price(movieDTO.getPrice())
                .build();
    }

    @Override
    public MovieDTO movieById(Integer movieId) throws MovieNotFoundException {
        MovieEntity movieEntity = this.movieRepository.findById(movieId)
                .orElseThrow(() -> new MovieNotFoundException("movie with " + movieId + " not found"));
        return MovieDTO.builder()
                .movieId(movieEntity.getMovieId())
                .movieImageUrl(movieEntity.getMovieImageUrl())
                .movieName(movieEntity.getMovieName())
                .movieDuration(movieEntity.getMovieDuration())
                .movieLanguage(movieEntity.getMovieLanguage())
                .movieType(movieEntity.getMovieType())
                .seats(movieEntity.getSeats())
                .price(movieEntity.getPrice())
                .build();
    }

    @Override
    public List<MovieDTO> getMoviesList() {
        List<MovieDTO> movieDTOList=new ArrayList<>();
        List<MovieEntity> movieEntityList = this.movieRepository.findAll();
        for (MovieEntity entity:movieEntityList) {
            MovieDTO movieDTO = MovieDTO.builder()
                            .movieImageUrl(entity.getMovieImageUrl())
                            .price(entity.getPrice())
                            .movieId(entity.getMovieId())
                            .movieName(entity.getMovieName())
                            .movieLanguage(entity.getMovieLanguage())
                            .movieType(entity.getMovieType())
                            .seats(entity.getSeats())
                             .movieDuration(entity.getMovieDuration())
                    .build();

            movieDTOList.add(movieDTO);
        }
        return movieDTOList;
    }


}
