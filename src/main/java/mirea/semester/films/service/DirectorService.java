package mirea.semester.films.service;


import mirea.semester.films.dto.DirectorDto;
import mirea.semester.films.mapper.DirectorMapper;
import mirea.semester.films.mapper.MovieMapper;
import mirea.semester.films.model.Director;
import mirea.semester.films.repository.DirectorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DirectorService {

    @Autowired
    private DirectorRepository directorRepository;

    @Autowired
    private DirectorMapper directorMapper;

    public List<Director> getAllDirectors() {
        return directorRepository.findAll();
    }

    public Optional<Director> getDirectorById(Long id) {
        return directorRepository.findById(id);
    }

    public Director saveDirector(Director director) {
        return directorRepository.save(director);
    }

    public void deleteDirector(Long id) {
        directorRepository.deleteById(id);
    }

    public Optional<DirectorDto> getDirectorByName(String name) {
        return directorRepository.getDirectorsByName(name).map(directorMapper::toDto);
    }
}
