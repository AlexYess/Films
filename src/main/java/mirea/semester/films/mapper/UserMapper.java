package mirea.semester.films.mapper;

import mirea.semester.films.dto.UserDto;
import mirea.semester.films.dto.user.UserDTORequest;
import mirea.semester.films.dto.user.UserDTOResponse;
import mirea.semester.films.model.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    public User toUser(UserDTORequest userDTORequest) {
    return User.builder()
        .username(userDTORequest.getUsername()) // Мапим поле "name" на "username"
        .email(userDTORequest.getEmail()) // Мапим поле "email" из DTO на модель
        .password(userDTORequest.getPassword())
        .role("ROLE_USER") // По умолчанию присваиваем роль пользователя
        .build();
    }

    public UserDTOResponse toUserDTOResponse(User user) {
        return new UserDTOResponse(
                user.getUsername(),
                user.getEmail(),
                user.getRole()
        );
    }

    public UserDto toDto(User user) {
        return new UserDto(user.getId(), user.getUsername(), user.getEmail());
    }

    public List<UserDto> toDtoList(List<User> users) {
        return users.stream().map(this::toDto).collect(Collectors.toList());
    }
}
