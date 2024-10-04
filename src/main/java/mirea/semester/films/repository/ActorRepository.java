package mirea.semester.films.repository;


import mirea.semester.films.model.Actor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ActorRepository extends JpaRepository<Actor, Long> {
    // Вы можете добавить дополнительные методы поиска, если потребуется
    Optional<Actor> findByName(String name);
}
