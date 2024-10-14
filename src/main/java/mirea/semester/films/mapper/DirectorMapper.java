package mirea.semester.films.mapper;

import mirea.semester.films.dto.ActorDto;
import mirea.semester.films.dto.DirectorDto;
import mirea.semester.films.dto.MovieDto;
import mirea.semester.films.model.Actor;
import mirea.semester.films.model.Director;
import mirea.semester.films.model.Movie;
import mirea.semester.films.repository.MovieRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class DirectorMapper {

    private final MovieRepository movieRepository;

    public DirectorMapper(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public DirectorDto toDto(Director director) {
        // Преобразуем список фильмов в DTO
        List<String> movies = director.getMovies().stream().map(Movie::getTitle).toList();
    return new DirectorDto(
        director.getId(),
        director.getName(),
        director.getBio(),
        director.getBirthDate(),
        director.getDeathDate(),
        director.getPhoto_url(),
        movies // Передаем преобразованный список MovieDto
        );
    }

    public List<DirectorDto> toDtoList(List<Director> directors) {
        return directors.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public Director toDirector(DirectorDto directorDto) {
        List<Movie> movies = directorDto.getMovies() != null
                ? directorDto.getMovies().stream()
                .map(movieTitle -> movieRepository.findByTitle(movieTitle).orElse(null))
                .collect(Collectors.toList())
                : null;

        String deathDate = (directorDto.getDeathDate() == null || directorDto.getDeathDate().isEmpty()) ? "-1" : directorDto.getDeathDate();


        return Director.builder()
                .id(directorDto.getId())
                .name(directorDto.getName())
                .bio(directorDto.getBio())
                .birthDate(directorDto.getBirthDate())
                .deathDate(deathDate)
                .photo_url(directorDto.getPhotoUrl())
                .movies(movies)
                .build();
    }
}
