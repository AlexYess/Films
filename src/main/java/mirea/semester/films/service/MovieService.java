package mirea.semester.films.service;


import mirea.semester.films.dto.MovieDto;
import mirea.semester.films.mapper.MovieMapper;
import mirea.semester.films.model.Movie;
import mirea.semester.films.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private MovieDescriptionService movieDescriptionService;

    @Autowired
    private MovieMapper movieMapper;

    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    public Optional<Movie> getMovieById(Long id) {
        return movieRepository.findById(id);
    }

    public Movie saveMovie(Movie movie) {
        if (movie.getDescription() == null)
        {
            movie.setDescription(movieDescriptionService.generateDescription(movie.getTitle()));
        }
        return movieRepository.save(movie);
    }

    public Optional<MovieDto> getMovieByTitle(String title) {
        return movieRepository.findByTitle(title).map(movieMapper::toDto);
    }

    public void deleteMovie(Long id) {
        movieRepository.deleteById(id);
    }

    public List<MovieDto> getRandomMovies(int count) {
        return movieMapper.toDtoList(movieRepository.findRandomMovies(count));
    }
}
