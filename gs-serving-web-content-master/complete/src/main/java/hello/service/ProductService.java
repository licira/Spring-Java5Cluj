package hello.service;

import hello.entity.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ProductService {

    private final RestTemplate restTemplate = new RestTemplate();

    public String getAll() {
        return restTemplate.getForObject("http://localhost:8080/product/list", String.class);
    }

    public List<Product> getAllAsList() {
        return restTemplate.getForObject("http://localhost:8080/product/list", List.class);
    }

    public String getById(Long id) {
        return restTemplate.getForObject("http://localhost:8080/product/show/" + id, String.class);
    }

    public Product getByIdAsProduct(Long id) {
        return restTemplate.getForObject("http://localhost:8080/product/show/" + id, Product.class);
    }

    public Product saveOrUpdate(Product product) {
        ResponseEntity<Product> responseEntity = restTemplate.postForEntity("http://localhost:8080/product/save", product, Product.class);
        return responseEntity.getBody();
    }
}
