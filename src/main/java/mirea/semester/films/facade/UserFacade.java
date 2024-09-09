package mirea.semester.films.facade;

import mirea.semester.films.dto.UserDto;
import mirea.semester.films.mapper.UserMapper;
import mirea.semester.films.service.UserService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserFacade {
    private final UserService userService;
    private final UserMapper userMapper;

    public UserFacade(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    public List<UserDto> getAllUsers() {
        return userMapper.toDtoList(userService.getAllUsers());
    }

    public Optional<UserDto> getUserById(Long id) {
        return userService.getUserById(id).map(user -> {
            return new UserDto(user.getId(), user.getUsername(), user.getEmail());
        });
    }
}
