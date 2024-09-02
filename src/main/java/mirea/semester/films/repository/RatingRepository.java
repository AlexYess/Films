package mirea.semester.films.repository;


import mirea.semester.films.model.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RatingRepository extends JpaRepository<Rating, Long> {

    List<Rating> findByMovieId(Long movieId);

    List<Rating> findByUserId(Long userId);
}
