package mirea.semester.films.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MovieDto {
    private int id;
    private String title;
    private String releaseDate;
    private int episodeCount;
    private String posterUrl;

    // Конструкторы, геттеры, сеттеры
}
