package mirea.semester.films.controller;


import mirea.semester.films.dto.DirectorDto;
import mirea.semester.films.facade.DirectorFacade;
import mirea.semester.films.model.Director;
import mirea.semester.films.service.DirectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/directors")
public class DirectorController {

    @Autowired
    private DirectorService directorService;

    @Autowired
    private DirectorFacade directorFacade;

    @GetMapping
    public List<DirectorDto> getAllDirectors() {
        return directorFacade.getAllDirectors();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DirectorDto> getDirectorById(@PathVariable Long id) {
        Optional<DirectorDto> director = directorFacade.getDirector(id);
        return director.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Director> createDirector(@RequestBody Director director) {
        Director savedDirector = directorService.saveDirector(director);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedDirector);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDirector(@PathVariable Long id) {
        directorService.deleteDirector(id);
        return ResponseEntity.noContent().build();
    }
}
