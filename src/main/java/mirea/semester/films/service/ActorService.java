package mirea.semester.films.service;


import mirea.semester.films.dto.ActorDto;
import mirea.semester.films.mapper.ActorMapper;
import mirea.semester.films.model.Actor;
import mirea.semester.films.repository.ActorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ActorService {

    @Autowired
    private ActorRepository actorRepository;
    @Autowired
    private ActorMapper actorMapper;

    public List<Actor> getAllActors() {
        return actorRepository.findAll();
    }

    public Optional<Actor> getActorById(Long id) {
        return actorRepository.findById(id);
    }

    public Actor saveActor(Actor actor) {
        return actorRepository.save(actor);
    }

    public void deleteActor(Long id) {
        actorRepository.deleteById(id);
    }

    public Optional<ActorDto> getActorByName(String name) {
        return actorRepository.findByName(name).map(actorMapper::toDto);
    }
}
