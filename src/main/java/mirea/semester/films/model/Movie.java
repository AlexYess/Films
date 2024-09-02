package mirea.semester.films.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "movies")
@Data  // Автоматически добавляет геттеры, сеттеры, методы equals, hashCode и toString
@NoArgsConstructor  // Автоматически создает конструктор без аргументов
@AllArgsConstructor // Автоматически создает конструктор со всеми аргументами
@Builder
public class Movie {
    @Id
    @GeneratedValue
    private int id;

    @Column(nullable = false)
    private String title;

    @Column(length = 500)
    private String description;

    @Column(nullable = false)
    private String relese_date;

    @Column(nullable = false)
    private int type;

    @Column
    private int duration;

    @Column
    private int episode_count;

    @Column
    private String poster_url;

    @ManyToMany(mappedBy = "movies")
    private List<Actor> actors;

    @ManyToOne
    @JoinColumn(name = "director_id")
    private Director director;

    @ManyToMany
    @JoinTable(
            name = "movie_genres",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id")
    )
    private List<Genre> genres;

    @OneToMany(mappedBy = "movie")
    private List<Rating> ratings;
}
