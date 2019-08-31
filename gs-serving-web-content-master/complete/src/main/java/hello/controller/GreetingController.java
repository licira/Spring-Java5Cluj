package hello.controller;

import hello.dto.ShoppingCartDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class GreetingController {

    private static final String SHOPPING_CART_SESSION_VARIABLE_NAME = "shoppingCart";

    @Autowired
    private ShoppingCartDTO shoppingList;

    @GetMapping("/greeting")
    public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name,
                           Model model,
                           HttpServletRequest request) {
        model.addAttribute("name", name);

        HttpSession session = request.getSession();
        Object shoppingCartAsObject = session.getAttribute(SHOPPING_CART_SESSION_VARIABLE_NAME);
        ShoppingCartDTO shoppingCartDTO = null;
        if (shoppingCartAsObject != null) {
            shoppingCartDTO = (ShoppingCartDTO) shoppingCartAsObject;
        } else {
            shoppingCartDTO = new ShoppingCartDTO();
        }
        shoppingCartDTO.count++;
        session.setAttribute(SHOPPING_CART_SESSION_VARIABLE_NAME, shoppingCartDTO);

        return "greeting";
    }

}
