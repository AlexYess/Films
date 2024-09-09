package mirea.semester.films.controller;

import mirea.semester.films.facade.UserRegistrationFacade;
import mirea.semester.films.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/register")
public class RegistrationController {

    @Autowired
    private UserRegistrationFacade userRegistrationFacade;

    @PostMapping
    public ResponseEntity<String> registerUser(@RequestBody User user) {
        try {
            userRegistrationFacade.registerNewUser(user);
            return ResponseEntity.ok("Регистрация прошла успешно");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}

