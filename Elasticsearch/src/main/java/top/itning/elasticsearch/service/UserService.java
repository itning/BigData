package top.itning.elasticsearch.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import top.itning.elasticsearch.entity.User;

/**
 * @author itning
 * @date 2019/6/5 14:04
 */
public interface UserService {
    /**
     * 添加用户
     *
     * @param name    姓名
     * @param tel     电话
     * @param address 地址
     * @return 新增的用户
     */
    User addUser(String name, int tel, String address);

    /**
     * 删除用户
     *
     * @param id 用户ID
     */
    void delUser(String id);

    /**
     * 搜索
     *
     * @param search   关键字
     * @param pageable 分页
     * @return 搜索结果
     */
    Page<User> search(String search, Pageable pageable);
}
