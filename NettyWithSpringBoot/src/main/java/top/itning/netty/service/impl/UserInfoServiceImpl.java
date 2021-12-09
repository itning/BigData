package top.itning.netty.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import top.itning.netty.model.User;
import top.itning.netty.service.UserInfoService;

import java.util.Arrays;
import java.util.List;

/**
 * @author itning
 * @since 2021/12/9 13:11
 */
@Slf4j
@Service
public class UserInfoServiceImpl implements UserInfoService {
    @Override
    public List<User> getAll() {
        log.info("获取所有用户信息");
        return Arrays.asList(new User(1L, "张三"), new User(2L, "王五"));
    }

    @Override
    public User findById(Long id) {
        return null;
    }
}
