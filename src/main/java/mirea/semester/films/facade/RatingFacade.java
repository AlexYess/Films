package mirea.semester.films.facade;

import mirea.semester.films.model.Rating;
import mirea.semester.films.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RatingFacade {

    @Autowired
    private RatingService ratingService;

    public Double getAverageRatingByMovieId(Long movieId) {
        List<Rating> ratings = ratingService.getRatingsByMovieId(movieId);

        if (ratings.isEmpty()) {
            return 0.0; // Если нет рейтингов, возвращаем 0
        }

        return ratings.stream()
                .mapToDouble(Rating::getRating)
                .average()
                .orElse(0.0);
    }
}

