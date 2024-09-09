package mirea.semester.films.mapper;

import mirea.semester.films.dto.DirectorDto;
import mirea.semester.films.dto.MovieDto;
import mirea.semester.films.model.Director;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class DirectorMapper {
    private final MovieMapper movieMapper;

    public DirectorMapper(MovieMapper movieMapper) {
        this.movieMapper = movieMapper;
    }

    public DirectorDto toDto(Director director) {
        // Преобразуем список фильмов в DTO
        List<MovieDto> movieDtos = movieMapper.toDtoList(director.getMovies());
    return new DirectorDto(
        director.getId(),
        director.getName(),
        director.getBio(),
        director.getBirthDate(),
        director.getDeathDate(),
        director.getPhoto_url(),
        movieDtos // Передаем преобразованный список MovieDto
        );
    }

    public List<DirectorDto> toDtoList(List<Director> directors) {
        return directors.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}
