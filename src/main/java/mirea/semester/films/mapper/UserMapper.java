package mirea.semester.films.mapper;

import mirea.semester.films.dto.UserDto;
import mirea.semester.films.model.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {
    public UserDto toDto(User user) {
        return new UserDto(user.getId(), user.getUsername(), user.getEmail());
    }

    public List<UserDto> toDtoList(List<User> users) {
        return users.stream().map(this::toDto).collect(Collectors.toList());
    }
}
