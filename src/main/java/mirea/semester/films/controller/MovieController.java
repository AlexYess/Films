package mirea.semester.films.controller;


import mirea.semester.films.dto.MovieDto;
import mirea.semester.films.facade.MovieFacade;
import mirea.semester.films.model.Movie;
import mirea.semester.films.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin/api/movies")
public class MovieController {

    @Autowired
    private MovieService movieService;
    @Autowired
    private MovieFacade movieFacade;

    @GetMapping
    public List<MovieDto> getAllMovies() {
        return movieFacade.getAllMovies();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieDto> getMovieById(@PathVariable Long id) {
        Optional<MovieDto> movie = movieFacade.getMovieById(id);
        return movie.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Movie> createMovie(@RequestBody Movie movie) {
        Movie savedMovie = movieService.saveMovie(movie);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedMovie);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovie(@PathVariable Long id) {
        movieService.deleteMovie(id);
        return ResponseEntity.noContent().build();
    }
}
