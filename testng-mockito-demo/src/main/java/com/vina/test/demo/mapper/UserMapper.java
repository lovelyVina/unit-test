package com.vina.test.demo.mapper;

import com.vina.test.demo.domain.UserDO;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * @author fWang
 * @Description:
 * @date 2017/9/26 13:31
 */
@Repository
public interface UserMapper {
    /**
     * 新增用户
     * @param user
     * @return
     */
    int addUser(UserDO user);

    /**
     * 根据用户ID获取用户
     * @param userId
     * @return
     */
    UserDO get(Long userId);

    /**
     * 根据用户名称获取用户
     * @param name
     * @return
     */
    List<UserDO> getByName(String name);
}
