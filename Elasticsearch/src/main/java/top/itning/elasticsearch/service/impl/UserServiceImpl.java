package top.itning.elasticsearch.service.impl;

import org.elasticsearch.index.query.QueryBuilders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;
import top.itning.elasticsearch.dao.UserRepository;
import top.itning.elasticsearch.entity.User;
import top.itning.elasticsearch.service.UserService;

import java.util.Objects;
import java.util.UUID;

/**
 * @author itning
 * @date 2019/6/5 14:06
 */
@Service
public class UserServiceImpl implements UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User addUser(String name, int tel, String address) {
        String id = UUID.randomUUID().toString().replace("-", "");
        User user = new User();
        user.setId(id);
        user.setName(name);
        user.setTel(tel);
        user.setAddress(address);
        logger.debug("save new user: {}", user);
        return userRepository.save(user);
    }

    @Override
    public void delUser(String id) {
        Objects.requireNonNull(id, "用户ID不能为空");
        logger.debug("del user id: {}", id);
        userRepository.deleteById(id);
    }

    @Override
    public Page<User> search(String search, Pageable pageable) {
        User user = new User();
        user.setName(search);
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.wildcardQuery("name",search))
                .withPageable(pageable)
                .build();
        Page<User> users = userRepository.search(searchQuery);
        //Page<User> name = userRepository.search(QueryBuilders.matchQuery("name", search), pageable);
        return users;
    }
}
