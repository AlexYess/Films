package mirea.semester.films.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserDTORequest {
    private String username;
    private String email;
    private String password;
}
