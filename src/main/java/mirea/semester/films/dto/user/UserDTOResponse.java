package mirea.semester.films.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserDTOResponse {
    private String username;  // Имя пользователя
    private String email;     // Email
    private String role;      // Роль пользователя
}
