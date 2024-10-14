package mirea.semester.films.controller;

import mirea.semester.films.dto.ActorDto;
import mirea.semester.films.dto.DirectorDto;
import mirea.semester.films.dto.MovieDto;
import mirea.semester.films.dto.user.UserDTORequest;
import mirea.semester.films.facade.ActorFacade;
import mirea.semester.films.facade.DirectorFacade;
import mirea.semester.films.facade.MovieFacade;
import mirea.semester.films.model.Actor;
import mirea.semester.films.model.Director;
import mirea.semester.films.model.Movie;
import mirea.semester.films.service.MovieService;
import mirea.semester.films.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
public class WebController {

    @Autowired
    private UserService userService;

    @Autowired
    private MovieFacade movieFacade;
    @Autowired
    private ActorFacade actorFacade;
    @Autowired
    private DirectorFacade directorFacade;

    @GetMapping("/register")
    public String showRegistrationPage() {
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(UserDTORequest userDTORequest, Model model) {
        userService.saveUser(userDTORequest);
        return "redirect:/login";
    }

    // Страница логина
    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    @GetMapping("/admin/dashboard")
    public String showAdminDashboardPage() {
        return "dashboard";
    }

    @PostMapping("/login")
    public String login(String username, String password, Model model) {
        // Логика аутентификации пользователя
        if (userService.authenticate(username, password)) {
            return "redirect:/home"; // Перенаправление на главную страницу после успешного входа
        } else {
            model.addAttribute("error", "Invalid username or password");
            return "login"; // Возврат на страницу логина с сообщением об ошибке
        }
    }

    @GetMapping("/home")
    public String homePage(Model model) {
        // Получение случайных фильмов (например, 5 случайных фильмов)
        List<MovieDto> randomMovies = movieFacade.getRandomMovies(5);
        model.addAttribute("movies", randomMovies);
        return "home";
    }

    @GetMapping("/search")
    public String search(@RequestParam("query") String query, @RequestParam("type") String type, Model model) {
        switch (type) {
            case "movie":
                Optional<MovieDto> movie = movieFacade.getMovieByTitle(query);
                if (movie.isPresent()) {
                    model.addAttribute("movie", movie.get());
                    return "movie-details"; // Шаблон для отображения фильма
                }
                break;
            case "actor":
                Optional<ActorDto> actor = actorFacade.getActorByName(query);
                if (actor.isPresent()) {
                    model.addAttribute("actor", actor.get());
                    return "actor-details"; // Шаблон для отображения актера
                }
                break;
            case "director":
                Optional<DirectorDto> director = directorFacade.getDirectorByName(query);
                if (director.isPresent()) {
                    model.addAttribute("director", director.get());
                    return "director-details"; // Шаблон для отображения режиссера
                }
                break;
        }
        return "404"; // Возвращаем страницу 404, если ничего не найдено
    }

    @GetMapping("admin/create-movie")
    public String showCreateMovieForm(Model model) {
        model.addAttribute("actors", actorFacade.getAllActors());
        model.addAttribute("directors", directorFacade.getAllDirectors());
        return "create-movie";
    }

    @GetMapping("admin/create-actor")
    public String showCreateActorForm() {
        return "create-actor";
    }

    @GetMapping("admin/create-director")
    public String showCreateDirectorForm() {
        return "create-director";
    }

}

