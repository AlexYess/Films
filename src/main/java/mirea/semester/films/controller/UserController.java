package mirea.semester.films.controller;


import mirea.semester.films.dto.UserDto;
import mirea.semester.films.dto.user.UserDTORequest;
import mirea.semester.films.dto.user.UserDTOResponse;
import mirea.semester.films.facade.UserFacade;
import mirea.semester.films.mapper.UserMapper;
import mirea.semester.films.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin/api/users")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserFacade userFacade;

    @Autowired
    private UserMapper mapper;

    @GetMapping
    public List<UserDto> getAllUsers() {
        return userFacade.getAllUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        Optional<UserDto> user = userFacade.getUserById(id);
        return user.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<UserDTOResponse> createUser(@RequestBody UserDTORequest user) {
        UserDTOResponse savedUser = userService.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
