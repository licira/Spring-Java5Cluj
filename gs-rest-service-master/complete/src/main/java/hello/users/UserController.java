package hello.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(path = "/",
            produces = "application/json",
            method = RequestMethod.GET)
    public List<User> getAll() {
        return userService.getAll();
    }

    @RequestMapping(path = "/{id}",
            produces = "application/json",
            method = RequestMethod.GET)
    public User getById(@PathVariable Long id) {
        return userService.getById(id);
    }

    @RequestMapping(path = "/save",
            consumes = "application/json",
            produces = "application/json",
            method = RequestMethod.POST)
    public User post(@RequestBody User user) {
        return userService.insert(user);
    }
}
