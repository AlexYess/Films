package mirea.semester.films.controller;

import mirea.semester.films.dto.user.UserDTORequest;
import mirea.semester.films.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

    @Autowired
    private UserService userService;

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

    @GetMapping("/home")
    public String showHomePage() {
        return "home"; // Возвращаем имя шаблона без .html
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
}

