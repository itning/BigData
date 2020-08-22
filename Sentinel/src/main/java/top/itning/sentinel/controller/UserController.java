package top.itning.sentinel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import top.itning.sentinel.dto.UserDTO;
import top.itning.sentinel.service.UserService;

/**
 * @author itning
 * @date 2020/8/22 15:23
 */
@RestController
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/test")
    public UserDTO test() {
        return userService.getRandomUser();
    }
}
