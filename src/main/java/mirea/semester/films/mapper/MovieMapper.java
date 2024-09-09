package mirea.semester.films.mapper;

import mirea.semester.films.dto.MovieDto;
import mirea.semester.films.model.Movie;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MovieMapper {

    public MovieDto toDto(Movie movie) {
        return new MovieDto(
                movie.getId(),
                movie.getTitle(),
                movie.getRelease_date(),
                movie.getEpisode_count(),
                movie.getPoster_url()
        );
    }

    public List<MovieDto> toDtoList(List<Movie> movies) {
        return movies.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}


