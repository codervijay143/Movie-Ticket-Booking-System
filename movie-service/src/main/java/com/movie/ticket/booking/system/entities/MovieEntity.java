package com.movie.ticket.booking.system.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "movies")
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class MovieEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer movieId;
    private String movieName;
    private String movieImageUrl;
    private Integer price;
    private Integer seats;
    private Integer movieDuration;
    @Enumerated(EnumType.STRING)
    private List<MovieType> movieType;
    @Enumerated(EnumType.STRING)
    private List<MovieLanguage> movieLanguage;
}
