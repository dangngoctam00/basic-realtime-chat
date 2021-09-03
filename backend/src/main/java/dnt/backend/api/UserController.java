package dnt.backend.api;

import dnt.backend.model.dto.UserDto;
import dnt.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/api")
@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/friends/{username}")
    public List<UserDto> getFriends(@PathVariable String username) {
        return userService.getFriends(username)
                .stream()
                .map(user -> new UserDto(user.getUsername()))
                .collect(Collectors.toList());
    }
}
