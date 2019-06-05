package top.itning.elasticsearch.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.itning.elasticsearch.entity.User;
import top.itning.elasticsearch.service.UserService;

/**
 * 用户控制层
 *
 * @author itning
 * @date 2019/6/5 11:16
 */
@RestController
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/add")
    public User addUser(@RequestParam String name, @RequestParam int tel, @RequestParam String address) {
        logger.debug("add user");
        return userService.addUser(name, tel, address);
    }

    @GetMapping("/del")
    public void delUser(@RequestParam String id) {
        logger.debug("del user");
        userService.delUser(id);
    }

    @GetMapping("/search")
    public Page<User> searchUsers(@RequestParam String search,
                                  @PageableDefault(size = 20, sort = {"name"}, direction = Sort.Direction.ASC) Pageable pageable) {
        logger.debug("search user");
        return userService.search(search,pageable);
    }

}
