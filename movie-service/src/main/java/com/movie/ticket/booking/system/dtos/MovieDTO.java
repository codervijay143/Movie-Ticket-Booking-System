package com.movie.ticket.booking.system.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.movie.ticket.booking.system.entities.MovieLanguage;
import com.movie.ticket.booking.system.entities.MovieType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Validated
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MovieDTO {

    private Integer movieId;
    @NotBlank(message = "movieName cannot be null")
    private String movieName;
    @NotBlank(message = "movieImageUrl cannot be null")
    private String movieImageUrl;
    @NotNull(message = "minimum one seat should be selected")
    private Integer seats;
    @NotNull(message = "price cannot be null")
    private Integer price;
    @NotNull(message = "movieDuration cannot be null")
    private Integer movieDuration;
//    @NotBlank(message = "movieType cannot be null")
    private List<MovieType> movieType;
//    @NotBlank(message = "movieLanguage cannot be null")
    private List<MovieLanguage> movieLanguage;
}
