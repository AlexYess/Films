package mirea.semester.films.mapper;

import mirea.semester.films.dto.GenreDto;
import mirea.semester.films.dto.MovieDto;
import mirea.semester.films.model.Genre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GenreMapper {
    private final MovieMapper movieMapper;

    public GenreMapper(MovieMapper movieMapper) {
        this.movieMapper = movieMapper;
    }

    public GenreDto toDto(Genre genre) {
        List<MovieDto> movieDtos = movieMapper.toDtoList(genre.getMovies());
        return new GenreDto(genre.getId(), genre.getName(), movieDtos);
    }

    public List<GenreDto> toDtoList(List<Genre> genres) {
        return genres.stream().map(this::toDto).toList();
    }
}
