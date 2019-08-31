package hello.controller;

import hello.entity.User;
import hello.event.UserEvent;
import hello.event.publisher.UserPublisher;
import hello.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserPublisher userPublisher;

    @GetMapping("/list")
    public String getAll(Model model) {
        String json = userService.getAll(); //getMessage(something.something)
        model.addAttribute("json", json);
        return "greeting";
    }

    @GetMapping("/{id}")
    public String getById(Model model, @PathVariable Long id) {
        String json = userService.getById(id);
        User user = userService.getUserById(id);

        userPublisher.publish(user);

        model.addAttribute("json", json);
        model.addAttribute("name", user);
        return "view";
    }

    @GetMapping("/create")
    public String create(Model model) {
        return "create";
    }

    public String saveOrUpdate(Model model) {
        userService.saveOrUpdate(null);
        return "create";
    }

    @ExceptionHandler({ Exception.class })
    public void handleException() {
        System.out.println("Exception");
    }

}
