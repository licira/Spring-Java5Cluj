package hello;

import hello.products.Product;
import hello.products.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class DbInitializer {

    @Autowired
    private ProductRepository productRepository;

    @PostConstruct
    public void init(){
        Product samsungTv = new Product(1L, "Samsung TV", 500.0);
        productRepository.save(samsungTv);

        Product sonyTv = new Product(2L, "Sony TV", 600.0);
        productRepository.save(sonyTv);

        Product fujitsuTv = new Product(3L, "Fujitsu TV", 700.0);
        productRepository.save(fujitsuTv);
    }
}
