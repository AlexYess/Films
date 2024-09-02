package mirea.semester.films.model;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "actors")
@Data  // Автоматически добавляет геттеры, сеттеры, методы equals, hashCode и toString
@NoArgsConstructor  // Автоматически создает конструктор без аргументов
@AllArgsConstructor // Автоматически создает конструктор со всеми аргументами
@Builder
public class Actor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String name;

    @Column(length = 500)
    private String bio;

    @Column(nullable = false)
    private String birthDate;
    private String deathDate;

    @ManyToMany
    @JoinTable(
            name = "movie_actors",
            joinColumns = @JoinColumn(name = "actor_id"),
            inverseJoinColumns = @JoinColumn(name = "movie_id")
    )
    private List<Movie> movies;
}
