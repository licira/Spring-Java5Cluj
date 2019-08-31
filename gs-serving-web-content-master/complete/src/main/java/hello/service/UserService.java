package hello.service;

import hello.entity.User;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserService {

    private final RestTemplate restTemplate = new RestTemplate();

    public String getAll() {
        return restTemplate.getForObject("http://localhost:8080/users/", String.class);
    }

    public String getById(Long id) {
        return restTemplate.getForObject("http://localhost:8080/user/" + id, String.class);
    }

    public User getUserById(Long id) {
        return restTemplate.getForObject("http://localhost:8080/user/" + id, User.class);
    }

    public String saveOrUpdate(Object o) {
        ResponseEntity<String> responseEntity = restTemplate.postForEntity("http://localhost:8080/users/save", "", String.class);
        return responseEntity.getBody();
    }


}
