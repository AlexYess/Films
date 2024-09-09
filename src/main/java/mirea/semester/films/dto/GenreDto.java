package mirea.semester.films.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class GenreDto {
    private int id;
    private String name;
    private List<MovieDto> movies;
}
