package mirea.semester.films.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "genres")
@Data  // Автоматически добавляет геттеры, сеттеры, методы equals, hashCode и toString
@NoArgsConstructor  // Автоматически создает конструктор без аргументов
@AllArgsConstructor // Автоматически создает конструктор со всеми аргументами
@Builder
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String name;

    @ManyToMany(mappedBy = "genres")
    private List<Movie> movies;
}
