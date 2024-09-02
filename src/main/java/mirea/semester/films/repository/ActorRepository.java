package mirea.semester.films.repository;


import mirea.semester.films.model.Actor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActorRepository extends JpaRepository<Actor, Long> {
    // Вы можете добавить дополнительные методы поиска, если потребуется
}
