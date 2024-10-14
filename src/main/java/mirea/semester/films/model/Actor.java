package mirea.semester.films.model;
import jakarta.persistence.*;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import java.util.List;

@Entity
@Table(name = "actors")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Actor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String name;

    @Column(length = 500)
    private String bio;

    @Column(nullable = false)
    private String birthDate;
    private String deathDate;
    private String photo_url;

    @ManyToMany
    @JoinTable(
            name = "movies_has_actors",
            joinColumns = @JoinColumn(name = "actors_id"),
            inverseJoinColumns = @JoinColumn(name = "movies_id")
    )
    private List<Movie> movies;
}
