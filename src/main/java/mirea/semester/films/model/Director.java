package mirea.semester.films.model;

import jakarta.persistence.*;
import lombok.*;
import mirea.semester.films.model.Movie;

import java.util.List;

@Entity
@Table(name = "directors")
@Data  // Автоматически добавляет геттеры, сеттеры, методы equals, hashCode и toString
@NoArgsConstructor  // Автоматически создает конструктор без аргументов
@AllArgsConstructor // Автоматически создает конструктор со всеми аргументами
@Builder
public class Director {

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

    private String photo_url;

    @OneToMany(mappedBy = "director", cascade = CascadeType.ALL)
    private List<Movie> movies;

}
