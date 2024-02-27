package com.movie.ticket.booking.system.repositories;

import com.movie.ticket.booking.system.entities.MovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<MovieEntity,Integer> {
}
