package mirea.semester.films.facade;

import mirea.semester.films.dto.UserRegistrationDto;
import mirea.semester.films.mapper.UserMapper;
import mirea.semester.films.mapper.UserRegistrationMapper;
import mirea.semester.films.model.User;
import mirea.semester.films.repository.UserRepository;
import mirea.semester.films.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserRegistrationFacade {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private UserRegistrationMapper userRegistrationMapper;

    public User registerNewUser(User user) {
        // Валидация
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new RuntimeException("Email уже используется.");
        }

        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new RuntimeException("Имя пользователя уже используется.");
        }

        // Хеширование пароля
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        if (user.getRole() == null) {
            user.setRole("ROLE_USER");  // Назначаем роль "USER" по умолчанию
        }
        // Маппинг данных в DTO (опционально)
        UserRegistrationDto userRegistrationDto = userRegistrationMapper.toDto(user);

        // Сохранение пользователя через сервис
        return userService.saveUser(user);
    }
}
