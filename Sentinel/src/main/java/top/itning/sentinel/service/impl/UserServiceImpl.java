package top.itning.sentinel.service.impl;

import org.springframework.stereotype.Service;
import top.itning.sentinel.config.annotation.SentinelResourcePro;
import top.itning.sentinel.dto.UserDTO;
import top.itning.sentinel.service.UserService;

import java.util.UUID;

/**
 * @author itning
 * @date 2020/8/22 15:30
 */
@Service
public class UserServiceImpl implements UserService {
    @SentinelResourcePro(value = "test")
    @Override
    public UserDTO getRandomUser() {
        return new UserDTO(UUID.randomUUID().toString());
    }
}
