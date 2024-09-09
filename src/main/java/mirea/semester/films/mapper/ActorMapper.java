package mirea.semester.films.mapper;

import mirea.semester.films.dto.ActorDto;
import mirea.semester.films.dto.MovieDto;
import mirea.semester.films.model.Actor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ActorMapper {

    @Autowired
    private MovieMapper movieMapper;

    public ActorDto toDto(Actor actor) {
        // Преобразуем список фильмов в DTO
        List<MovieDto> movieDtos = movieMapper.toDtoList(actor.getMovies());
        return new ActorDto(
                actor.getId(),
                actor.getName(),
                actor.getBio(),
                actor.getBirthDate(),
                actor.getDeathDate(),
                actor.getPhoto_url(),
                movieDtos // Передаем преобразованный список MovieDto
        );
    }

    public List<ActorDto> toDtoList(List<Actor> actors) {
        return actors.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}
