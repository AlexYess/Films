package mirea.semester.films.repository;


import mirea.semester.films.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Long> {
    // Вы можете добавить дополнительные методы поиска, если потребуется
}
