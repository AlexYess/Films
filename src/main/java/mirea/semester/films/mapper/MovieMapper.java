package mirea.semester.films.mapper;

import mirea.semester.films.dto.MovieDto;
import mirea.semester.films.model.Actor;
import mirea.semester.films.model.Director;
import mirea.semester.films.model.Genre;
import mirea.semester.films.model.Movie;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MovieMapper {

    public MovieDto toDto(Movie movie) {
        List<String> genres = movie.getGenres().stream().map(Genre::getName).toList();
        List<String> directors = movie.getDirectors().stream().map(Director::getName).toList();
        List<String> actors = movie.getActors().stream().map(Actor::getName).toList();
        return new MovieDto(
                movie.getId(),
                movie.getTitle(),
                movie.getDescription(),
                movie.getRelease_date(),
                movie.getDuration(),
                genres,
                directors,
                actors,
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


