package mirea.semester.films.controller;


import mirea.semester.films.dto.ActorDto;
import mirea.semester.films.facade.ActorFacade;
import mirea.semester.films.model.Actor;
import mirea.semester.films.service.ActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin/api/actors")
public class ActorController {

    @Autowired
    private ActorService actorService;
    @Autowired
    private ActorFacade actorFacade;

    @GetMapping
    public List<ActorDto> getAllActors() {
        return actorFacade.getAllActors();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ActorDto> getActorById(@PathVariable Long id) {
        Optional<ActorDto> actorDto = actorFacade.getActorById(id);
        return actorDto.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Actor> createActor(@RequestBody Actor actor) {
        Actor savedActor = actorService.saveActor(actor);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedActor);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteActor(@PathVariable Long id) {
        actorService.deleteActor(id);
        return ResponseEntity.noContent().build();
    }
}
