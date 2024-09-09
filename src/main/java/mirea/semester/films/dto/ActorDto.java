package mirea.semester.films.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mirea.semester.films.mapper.MovieMapper;
import mirea.semester.films.model.Actor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Component
public class ActorDto {
    private int id;
    private String name;
    private String bio;
    private String birthDate;
    private String deathDate;
    private String photo_url;
    private List<MovieDto> movies;
}

