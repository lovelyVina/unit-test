package com.vina.test.demo.service;


import com.vina.test.demo.domain.UserDO;

import java.util.List;

/**
 * @author fWang
 * @Description:
 * @date 2017/9/26 13:27
 */
public interface UserService {
    /**
     * 添加用户
     * @param user
     * @return
     */
    public int addUser(UserDO user);

    /**
     * 根据用户Id获取用户
     * @param userId
     * @return
     */
    public UserDO get(Long userId);

    /**
     * 根据用户名称获取用户
     * @param name
     * @return
     */
    public List<UserDO> getByName(String name);

    /**
     * 打印用户信息
     * @param user
     */
    public void printUserInfo(UserDO user);

}
