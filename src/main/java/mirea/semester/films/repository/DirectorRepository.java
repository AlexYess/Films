package mirea.semester.films.repository;


import mirea.semester.films.model.Director;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DirectorRepository extends JpaRepository<Director, Long> {
    // Вы можете добавить дополнительные методы поиска, если потребуется
    Optional<Director> getDirectorsByName(String name);
}
