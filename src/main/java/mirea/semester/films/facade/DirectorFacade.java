package mirea.semester.films.facade;

import mirea.semester.films.dto.DirectorDto;
import mirea.semester.films.dto.MovieDto;
import mirea.semester.films.mapper.DirectorMapper;
import mirea.semester.films.mapper.MovieMapper;
import mirea.semester.films.model.Director;
import mirea.semester.films.model.Movie;
import mirea.semester.films.service.DirectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class DirectorFacade {
    @Autowired
    private DirectorService directorService;

    @Autowired
    private MovieMapper movieMapper;

    @Autowired
    private DirectorMapper directorMapper;

    public Optional<DirectorDto> getDirector(Long id) {
        return directorService.getDirectorById(id)
                .map(director -> {
                    List<String> movies = director.getMovies().stream()
                            .map(Movie::getTitle)
                            .toList();
                    return new DirectorDto(
                            director.getId(),
                            director.getName(),
                            director.getBio(),
                            director.getBirthDate(),
                            director.getDeathDate(),
                            director.getPhoto_url(),
                            movies
                    );
                });
    }

    public List<DirectorDto> getAllDirectors() {
        return directorMapper.toDtoList(directorService.getAllDirectors());
    }

    public Optional<DirectorDto> getDirectorByName(String name) {
        return directorService.getDirectorByName(name);
    }

    public Director saveDirector(DirectorDto director) {
        Director newDirector = directorMapper.toDirector(director);
        return directorService.saveDirector(newDirector);
    }
}
