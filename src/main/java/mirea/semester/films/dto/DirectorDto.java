package mirea.semester.films.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Component
public class DirectorDto {
    private int id;
    private String name;
    private String bio;
    private String birthDate;
    private String deathDate;
    private String photo_url;
    private List<MovieDto> movies;
}