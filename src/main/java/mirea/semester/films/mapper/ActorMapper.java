package mirea.semester.films.mapper;

import mirea.semester.films.dto.ActorDto;
import mirea.semester.films.dto.MovieDto;
import mirea.semester.films.model.Actor;
import mirea.semester.films.model.Movie;
import mirea.semester.films.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ActorMapper {

    @Autowired
    private MovieRepository movieRepository;


    public ActorDto toDto(Actor actor) {
        // Преобразуем список фильмов в DTO
        List<String> movies = actor.getMovies().stream().map(Movie::getTitle).toList();
        return new ActorDto(
                actor.getId(),
                actor.getName(),
                actor.getBio(),
                actor.getBirthDate(),
                actor.getDeathDate(),
                actor.getPhoto_url(),
                movies // Передаем преобразованный список MovieDto
        );
    }

    public List<ActorDto> toDtoList(List<Actor> actors) {
        return actors.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public Actor toActor(ActorDto actorDto) {
        List<Movie> movies = actorDto.getMovies() != null
                ? actorDto.getMovies().stream()
                .map(movieTitle -> movieRepository.findByTitle(movieTitle).orElse(null))
                .collect(Collectors.toList())
                : null;

        String deathDate = (actorDto.getDeathDate() == null || actorDto.getDeathDate().isEmpty()) ? "-1" : actorDto.getDeathDate();


        return Actor.builder()
                .id(actorDto.getId())
                .name(actorDto.getName())
                .bio(actorDto.getBio())
                .birthDate(actorDto.getBirthDate())
                .deathDate(deathDate)
                .photo_url(actorDto.getPhotoUrl())
                .movies(movies)
                .build();
    }
}
