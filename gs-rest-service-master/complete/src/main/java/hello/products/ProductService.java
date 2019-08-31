package hello.products;

import hello.users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAll() {
        return productRepository.findAll();
    }

    public Product getById(Long id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        try {
            return optionalProduct.get();
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    public Product insert(Product product) {
        return productRepository.save(product);
    }
}
