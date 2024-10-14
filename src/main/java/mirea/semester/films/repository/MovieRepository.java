package mirea.semester.films.repository;


import mirea.semester.films.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface MovieRepository extends JpaRepository<Movie, Long> {
    Optional<Movie> findByTitle(String title);

    @Query(value = "SELECT * FROM movies ORDER BY RAND() LIMIT ?1", nativeQuery = true)
    List<Movie> findRandomMovies(int count);
}
