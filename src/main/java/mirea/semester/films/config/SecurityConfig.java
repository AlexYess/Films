package mirea.semester.films.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)  // Отключаем CSRF для примера
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/admin/**").hasRole("ADMIN")  // Доступ к маршрутам /admin только для администраторов
                        .requestMatchers("/user/**").hasRole("USER")    // Доступ к маршрутам /user только для пользователей
                        .requestMatchers("/api/register", "/login").permitAll()  // Открытые маршруты
                        .anyRequest().authenticated()  // Все остальные маршруты требуют аутентификации
                )
                .formLogin(form -> form
                        .loginPage("/login")  // Указываем страницу логина
                        .defaultSuccessUrl("/", true)  // После успешного логина перенаправляем на домашнюю страницу
                        .permitAll()
                )
                .logout(LogoutConfigurer::permitAll);

        return http.build();
    }
}


