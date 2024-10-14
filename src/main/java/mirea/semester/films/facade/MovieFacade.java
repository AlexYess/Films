package mirea.semester.films.facade;

import mirea.semester.films.dto.MovieDto;
import mirea.semester.films.mapper.MovieMapper;
import mirea.semester.films.model.Actor;
import mirea.semester.films.model.Director;
import mirea.semester.films.model.Genre;
import mirea.semester.films.model.Movie;
import mirea.semester.films.service.MovieService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class MovieFacade {
    private final MovieService movieService;
    private final MovieMapper movieMapper;

    public MovieFacade(MovieService movieService, MovieMapper movieMapper) {
        this.movieService = movieService;
        this.movieMapper = movieMapper;
    }

    public Optional<MovieDto> getMovieById(Long id) {
        return movieService
                .getMovieById(id)
                .map(movie -> {
                    // Получение имен жанров, режиссеров и актеров
                    List<String> genres = movie.getGenres().stream()
                            .map(Genre::getName)
                            .collect(Collectors.toList());

                    List<String> directors = movie.getDirectors().stream()
                            .map(Director::getName)
                            .collect(Collectors.toList());

                    List<String> actors = movie.getActors().stream()
                            .map(Actor::getName)
                            .collect(Collectors.toList());

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
                });
    }

    public List<MovieDto> getAllMovies() {
        return movieMapper.toDtoList(movieService.getAllMovies());
    }

    public List<MovieDto> getRandomMovies(int i) {
        return movieService.getRandomMovies(10);
    }

    public Optional<MovieDto> getMovieByTitle(String title) {
        return movieService.getMovieByTitle(title);
    }

    public Movie createMovie(MovieDto movie) {
        Movie newMovie = movieMapper.toMovie(movie);
        return movieService.saveMovie(newMovie);
    }
}
