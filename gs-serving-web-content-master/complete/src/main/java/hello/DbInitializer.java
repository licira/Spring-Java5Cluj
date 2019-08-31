package hello;

import hello.entity.User;
import hello.repo.UserRepository;
import hello.role.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;

@Component
public class DbInitializer {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostConstruct
    public void init(){
        List<Role> adminAndUserRoles = Arrays.asList(new Role[]{Role.ROLE_ADMIN, Role.ROLE_USER});
        String encodedPassword = passwordEncoder.encode("password");
        User adminAndUser = new User(1L, "admin@yahoo.com", encodedPassword, adminAndUserRoles);
        userRepository.save(adminAndUser);

        List<Role> userRoles = Arrays.asList(new Role[]{Role.ROLE_USER});
        encodedPassword = passwordEncoder.encode("password");
        User user = new User(2L, "user@yahoo.com", encodedPassword, userRoles);
        userRepository.save(user);
    }
}
