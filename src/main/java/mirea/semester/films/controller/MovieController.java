package mirea.semester.films.controller;


import mirea.semester.films.dto.MovieDto;
import mirea.semester.films.facade.MovieFacade;
import mirea.semester.films.model.Movie;
import mirea.semester.films.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/movies")
public class MovieController {

    @Autowired
    private MovieService movieService;
    @Autowired
    private MovieFacade movieFacade;

    @GetMapping
    public List<MovieDto> getAllMovies() {
        return movieFacade.getAllMovies();
    }

    @GetMapping("/admin/{id}")
    public ResponseEntity<MovieDto> getMovieById(@PathVariable Long id) {
        Optional<MovieDto> movie = movieFacade.getMovieById(id);
        return movie.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/admin/create-movie")
    public ResponseEntity<Movie> createMovie(@RequestBody MovieDto movie) {
        Movie savedMovie = movieFacade.createMovie(movie);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedMovie);
    }

    @DeleteMapping("/admin/{id}")
    public ResponseEntity<Void> deleteMovie(@PathVariable Long id) {
        movieService.deleteMovie(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{title}")
    public String getMovieByTitle(@PathVariable String title, Model model) {
    Optional<MovieDto> movie = movieService.getMovieByTitle(title);

    if (movie.isPresent()) {
      model.addAttribute("movie", movie.get());
      return "movie-details"; // Возвращаем шаблон movie-details
    } else {
      return "404"; // Возвращаем страницу 404, если фильм не найден
    }
    }
}
