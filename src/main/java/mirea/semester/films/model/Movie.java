package mirea.semester.films.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "movies")
@Data  // Автоматически добавляет геттеры, сеттеры, методы equals, hashCode и toString
@NoArgsConstructor  // Автоматически создает конструктор без аргументов
@AllArgsConstructor // Автоматически создает конструктор со всеми аргументами
@Builder
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Movie {
    @Id
    @GeneratedValue
    private int id;

    @Column(nullable = false)
    private String title;

    @Column(length = 1000)
    private String description;

    @Column(name = "release_date", nullable = false)
    private String release_date;

    @Column(nullable = false)
    private int type;

    @Column
    private int duration;

    @Column
    private int episode_count;

    @Column
    private String poster_url;

    @ManyToMany
    @JoinTable(
            name = "movies_has_actors",
            joinColumns = @JoinColumn(name = "movies_id"),
            inverseJoinColumns = @JoinColumn(name = "actors_id")
    )
    private List<Actor> actors;

    @ManyToMany
    @JoinTable(
            name = "movies_has_directors",
            joinColumns = @JoinColumn(name = "movies_id"),
            inverseJoinColumns = @JoinColumn(name = "directors_id")
    )
    private List<Director> directors;

    @ManyToMany
    @JoinTable(
            name = "movies_has_genres",
            joinColumns = @JoinColumn(name = "movies_id"),
            inverseJoinColumns = @JoinColumn(name = "genres_id")
    )
    private List<Genre> genres;

    @OneToMany(mappedBy = "movie")
    private List<Rating> ratings;
}
