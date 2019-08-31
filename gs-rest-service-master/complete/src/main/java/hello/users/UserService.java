package hello.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public User getById(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        try {
            return optionalUser.get();
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    public User insert(User user) {
        return userRepository.save(user);
    }
}
