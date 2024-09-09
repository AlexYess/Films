package mirea.semester.films.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserRegistrationDto {

    private String username;
    private String email;
    private String password;

    // Геттеры и сеттеры
}

