package mirea.semester.films.service;


import jdk.jfr.Label;
import mirea.semester.films.dto.user.UserDTORequest;
import mirea.semester.films.dto.user.UserDTOResponse;
import mirea.semester.films.mapper.UserMapper;
import mirea.semester.films.model.User;
import mirea.semester.films.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper mapper;

    @Autowired
    @Lazy
    private BCryptPasswordEncoder passwordEncoder;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public Optional<User> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public UserDTOResponse  saveUser(UserDTORequest user) {
        User user1 = mapper.toUser(user);
        user1.setPassword(passwordEncoder.encode(user.getPassword()));
        return mapper.toUserDTOResponse(userRepository.save(user1));
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

  public boolean authenticate(String username, String password) {
    Optional<User> user = userRepository.findByUsername(username); // Поиск пользователя по имени

    // Если пользователь найден и пароли совпадают
      return user.isPresent() && passwordEncoder.matches(password, user.get().getPassword()); // Аутентификация успешна
  }
}
