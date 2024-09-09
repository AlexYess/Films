package mirea.semester.films.facade;

import mirea.semester.films.dto.MovieDto;
import mirea.semester.films.mapper.MovieMapper;
import mirea.semester.films.service.MovieService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

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
        .map(
            movie -> {
              return new MovieDto(
                  movie.getId(),
                  movie.getTitle(),
                  movie.getRelease_date(),
                  movie.getEpisode_count(),
                  movie.getPoster_url());
            });
    }

    public List<MovieDto> getAllMovies() {
        return movieMapper.toDtoList(movieService.getAllMovies());
    }
}
