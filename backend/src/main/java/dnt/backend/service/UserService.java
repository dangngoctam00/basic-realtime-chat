package dnt.backend.service;

import dnt.backend.model.entity.User;
import dnt.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepo;

    @Autowired
    public UserService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    public List<User> getFriends(String username) {
        return userRepo.findAllByUsernameIn(userRepo.getFriends(username));
    }
}
