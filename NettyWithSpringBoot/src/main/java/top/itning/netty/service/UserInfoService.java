package top.itning.netty.service;

import top.itning.netty.model.User;

import java.util.List;

/**
 * @author itning
 * @since 2021/12/9 13:11
 */
public interface UserInfoService {
    List<User> getAll();

    User findById(Long id);
}
