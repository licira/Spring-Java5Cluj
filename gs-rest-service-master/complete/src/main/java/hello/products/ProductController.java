package hello.products;

import hello.users.User;
import hello.users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @RequestMapping(path = "/list",
            produces = "application/json",
            method = RequestMethod.GET)
    public List<Product> getAll() {
        return productService.getAll();
    }

    @RequestMapping(path = "/show/{id}",
            produces = "application/json",
            method = RequestMethod.GET)
    public Product getById(@PathVariable Long id) {
        return productService.getById(id);
    }

    @RequestMapping(path = "/save",
            consumes = "application/json",
            produces = "application/json",
            method = RequestMethod.POST)
    public Product post(@RequestBody Product product) {
        return productService.insert(product);
    }
}
