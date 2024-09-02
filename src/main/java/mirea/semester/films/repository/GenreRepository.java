package mirea.semester.films.repository;


import mirea.semester.films.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<Genre, Long> {
    // Вы можете добавить дополнительные методы поиска, если потребуется
}
