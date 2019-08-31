package hello.security;

import hello.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Component
public class CustomInMemoryAuthenticationProvider implements AuthenticationProvider {

    private Map<String, String> userToPasswordMap = new HashMap<>();

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public CustomInMemoryAuthenticationProvider() {
        userToPasswordMap.put("user", "password");
    }

    @Override
    public Authentication authenticate(Authentication auth)
            throws AuthenticationException {
        String username = auth.getName();
        String password = auth.getCredentials()
                .toString();

        if (userToPasswordMap.containsKey(username) && userToPasswordMap.get(username).equals(password)) {
            return new UsernamePasswordAuthenticationToken
                    (username, password, Collections.emptyList());
        } else {
            if (userRepository.findByUsername(username).isPresent() &&
                    //userRepository.findByUsername(username).get().getPassword().equals(passwordEncoder.encode(password)
                    passwordEncoder.matches(password, userRepository.findByUsername(username).get().getPassword())
                    ) {
                return new UsernamePasswordAuthenticationToken
                        (username, password, Collections.emptyList());
            } else {
                throw new
                        BadCredentialsException("External system authentication failed");
            }
        }
    }

    @Override
    public boolean supports(Class<?> auth) {
        return auth.equals(UsernamePasswordAuthenticationToken.class);
    }
}