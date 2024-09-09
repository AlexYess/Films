package mirea.semester.films.controller;


import mirea.semester.films.facade.RatingFacade;
import mirea.semester.films.model.Rating;
import mirea.semester.films.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/ratings")
public class RatingController {

    @Autowired
    private RatingService ratingService;
    @Autowired
    private RatingFacade ratingFacade;

    @GetMapping
    public List<Rating> getAllRatings() {
        return ratingService.getAllRatings();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Rating> getRatingById(@PathVariable Long id) {
        Optional<Rating> rating = ratingService.getRatingById(id);
        return rating.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/movie/{movieId}")
    public List<Rating> getRatingsByMovieId(@PathVariable Long movieId) {
        return ratingService.getRatingsByMovieId(movieId);
    }

    @GetMapping("/user/{userId}")
    public List<Rating> getRatingsByUserId(@PathVariable Long userId) {
        return ratingService.getRatingsByUserId(userId);
    }

    @PostMapping
    public ResponseEntity<Rating> createRating(@RequestBody Rating rating) {
        Rating savedRating = ratingService.saveRating(rating);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedRating);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRating(@PathVariable Long id) {
        ratingService.deleteRating(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{movieId}/average-rating")
    public ResponseEntity<Double> getAverageRating(@PathVariable Long movieId) {
        Double averageRating = ratingFacade.getAverageRatingByMovieId(movieId);
        return ResponseEntity.ok(averageRating);
    }
}
