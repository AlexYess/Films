package mirea.semester.films.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

import jakarta.persistence.*;

@Entity
@Table(name = "ratings")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "Movies_id", nullable = false)
    private Movie movie;

    @ManyToOne
    @JoinColumn(name = "Users_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private Float rating;
}
