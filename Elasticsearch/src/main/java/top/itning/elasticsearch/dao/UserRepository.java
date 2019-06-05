package top.itning.elasticsearch.dao;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import top.itning.elasticsearch.entity.User;

/**
 * @author itning
 * @date 2019/6/5 14:03
 */
public interface UserRepository extends ElasticsearchRepository<User, String> {
}
