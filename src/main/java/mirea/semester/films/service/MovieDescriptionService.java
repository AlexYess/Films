package mirea.semester.films.service;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import mirea.semester.films.model.Movie;
import mirea.semester.films.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;

@Service
public class MovieDescriptionService {

    private final String FLASK_API_URL = "http://localhost:5000/generate-description";  // URL вашего Flask API

    public String generateDescription(String title) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.set("Content-Type", "application/json");

            String jsonRequest = String.format("{\"title\": \"%s\"}", title);
            HttpEntity<String> request = new HttpEntity<>(jsonRequest, headers);

            ResponseEntity<String> response = restTemplate.exchange(
                    FLASK_API_URL,
                    HttpMethod.POST,
                    request,
                    String.class
            );

            // Парсинг JSON-ответа
            if (response.getStatusCode().is2xxSuccessful()) {
                ObjectMapper mapper = new ObjectMapper();
                JsonNode root = mapper.readTree(response.getBody());
                return root.path("description").asText();
            } else {
                throw new RuntimeException("Ошибка при генерации описания: " + response.getStatusCode());
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Ошибка при соединении с сервисом генерации описаний.");
        }
    }
}

