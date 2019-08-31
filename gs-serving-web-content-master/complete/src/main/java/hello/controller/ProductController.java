package hello.controller;

import hello.entity.Product;
import hello.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/list")
    public String getAll(Model model) {
        String productsJson = productService.getAll();
        List<Product> products = productService.getAllAsList();
        model.addAttribute("productsJson", productsJson);
        model.addAttribute("products", products);
        return "product/list";
    }

    @GetMapping("/show/{id}")
    public String show(Model model, @PathVariable Long id) {
        Product product = productService.getByIdAsProduct(id);
        model.addAttribute("product", product);
        return "product/show";
    }

    @GetMapping("/edit/{id}")
    public String edit(Model model, @PathVariable Long id) {
        Product product = productService.getByIdAsProduct(id);
        model.addAttribute("product", product);
        return "product/edit";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("product", new Product());
        return "product/create";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Product product, Model model) {
        product = productService.saveOrUpdate(product);
        model.addAttribute("product", product);
        return "product/show";
    }
}
