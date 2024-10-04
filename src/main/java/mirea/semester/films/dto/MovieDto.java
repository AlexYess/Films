package mirea.semester.films.dto;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieDto {
    private int id;
    private String title;
    private String description;
    private String releaseDate;
    private int duration;
    private List<String> genres;
    private List<String> directors;
    private List<String> actors;
    private int episodeCount;
    private String posterUrl;

    // Конструкторы, геттеры, сеттеры
}
