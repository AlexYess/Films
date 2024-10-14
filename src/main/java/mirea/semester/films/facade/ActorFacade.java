package mirea.semester.films.facade;
import mirea.semester.films.dto.ActorDto;
import mirea.semester.films.dto.MovieDto;
import mirea.semester.films.mapper.ActorMapper;
import mirea.semester.films.mapper.MovieMapper;
import mirea.semester.films.model.Actor;
import mirea.semester.films.model.Genre;
import mirea.semester.films.model.Movie;
import mirea.semester.films.service.ActorService;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class ActorFacade {

    @Autowired
    private ActorService actorService;

    @Autowired
    private MovieMapper movieMapper;

    @Autowired
    private ActorMapper actorMapper;

    public Optional<ActorDto> getActorById(Long id) {
        return actorService.getActorById(id)
                .map(actor -> {
                    // Преобразуем фильмы актера в DTO
                    List<String> movies = actor.getMovies().stream()
                            .map(Movie::getTitle)
                            .toList();


                    // Создаем ActorDto
                    return new ActorDto(
                            actor.getId(),
                            actor.getName(),
                            actor.getBio(),
                            actor.getBirthDate(),
                            actor.getDeathDate(),
                            actor.getPhoto_url(),
                            movies
                    );
                });
    }

    public List<ActorDto> getAllActors() {
        List<Actor> actors = actorService.getAllActors();
        // Преобразуем список актеров в список DTO с помощью ActorMapper
        return actorMapper.toDtoList(actors);
    }

    public Optional<ActorDto> getActorByName(String name) {
        return actorService.getActorByName(name);
    }

    public Actor saveActor(ActorDto actor) {
        Actor newActor = actorMapper.toActor(actor);
        return actorService.saveActor(newActor);
    }

    public void deleteActor(Long id) {
        actorService.deleteActor(id);
    }
}
