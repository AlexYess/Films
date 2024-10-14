package mirea.semester.films.mapper;

import mirea.semester.films.dto.MovieDto;
import mirea.semester.films.model.Actor;
import mirea.semester.films.model.Director;
import mirea.semester.films.model.Genre;
import mirea.semester.films.model.Movie;
import mirea.semester.films.service.ActorService;
import mirea.semester.films.service.DirectorService;
import mirea.semester.films.service.GenreService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MovieMapper {

    private final ActorService actorService;
    private final DirectorService directorService;
    private final GenreService genreService;

    public MovieMapper(ActorService actorService, DirectorService directorService, GenreService genreService) {
        this.actorService = actorService;
        this.directorService = directorService;
        this.genreService = genreService;
    }

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

    public Movie toMovie(MovieDto movieDto) {
        Movie movie = new Movie();
        movie.setId(movieDto.getId());
        movie.setTitle(movieDto.getTitle());
        movie.setDescription(movieDto.getDescription());
        movie.setRelease_date(movieDto.getReleaseDate());
        movie.setDuration(movieDto.getDuration());
        movie.setEpisode_count(movieDto.getEpisodeCount());
        movie.setPoster_url(movieDto.getPosterUrl());

        // Маппинг актеров по ID
        List<Actor> actors = movieDto.getActors().stream()
                .map(actorId -> actorService.getActorById(Long.parseLong(actorId))
                        .orElseThrow(() -> new RuntimeException("Actor not found with id: " + actorId)))
                .collect(Collectors.toList());
        movie.setActors(actors);

        // Маппинг режиссеров по ID
        List<Director> directors = movieDto.getDirectors().stream()
                .map(directorId -> directorService.getDirectorById(Long.parseLong(directorId))
                        .orElseThrow(() -> new RuntimeException("Director not found with id: " + directorId)))
                .collect(Collectors.toList());
        movie.setDirectors(directors);

        // Маппинг жанров по ID
        List<Genre> genres = movieDto.getGenres().stream()
                .map(genreId -> genreService.getGenreById(Long.parseLong(genreId))
                        .orElseThrow(() -> new RuntimeException("Genre not found with id: " + genreId)))
                .collect(Collectors.toList());
        movie.setGenres(genres);

        return movie;
    }
}


