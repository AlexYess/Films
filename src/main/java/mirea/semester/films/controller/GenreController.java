package mirea.semester.films.controller;


import mirea.semester.films.dto.GenreDto;
import mirea.semester.films.facade.GenreFacade;
import mirea.semester.films.mapper.GenreMapper;
import mirea.semester.films.model.Genre;
import mirea.semester.films.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin/api/genres")
public class GenreController {

    @Autowired
    private GenreService genreService;
    @Autowired
    private GenreMapper genreMapper;
    @Autowired
    private GenreFacade genreFacade;

    @GetMapping
    public List<GenreDto> getAllGenres() {
        return genreFacade.getAllGenres();
    }

    @GetMapping("/{id}")
    public ResponseEntity<GenreDto> getGenreById(@PathVariable Long id) {
        Optional<GenreDto> genre = genreFacade.getGenreById(id);
        return genre.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Genre> createGenre(@RequestBody Genre genre) {
        Genre savedGenre = genreService.saveGenre(genre);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedGenre);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGenre(@PathVariable Long id) {
        genreService.deleteGenre(id);
        return ResponseEntity.noContent().build();
    }
}
