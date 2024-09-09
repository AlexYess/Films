package mirea.semester.films.dto;

import lombok.*;

@Data
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
