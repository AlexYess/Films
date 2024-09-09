package mirea.semester.films.facade;

import mirea.semester.films.dto.GenreDto;
import mirea.semester.films.dto.MovieDto;
import mirea.semester.films.mapper.GenreMapper;
import mirea.semester.films.mapper.MovieMapper;
import mirea.semester.films.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class GenreFacade {
    @Autowired
    private MovieMapper movieMapper;

    @Autowired
    private GenreMapper genreMapper;

    @Autowired
    private GenreService genreService;

    public Optional<GenreDto> getGenreById(Long id) {
        return genreService.getGenreById(id).map(genre -> {
            List<MovieDto> movieDtos = movieMapper.toDtoList(genre.getMovies());
            return new GenreDto(genre.getId(), genre.getName(), movieDtos);
        });
    }

    public List<GenreDto> getAllGenres() {
        return genreMapper.toDtoList(genreService.getAllGenres());
    }
}
