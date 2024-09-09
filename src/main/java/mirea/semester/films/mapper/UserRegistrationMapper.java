package mirea.semester.films.mapper;

import mirea.semester.films.dto.UserRegistrationDto;
import mirea.semester.films.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserRegistrationMapper {

    public UserRegistrationDto toDto(User user) {
        UserRegistrationDto dto = new UserRegistrationDto();
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        dto.setPassword(user.getPassword());  // Обычно хешированный
        return dto;
    }
}

