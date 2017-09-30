package com.vina.test.demo.service;

import com.vina.test.demo.domain.UserDO;
import com.vina.test.demo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author fWang
 * @Description:
 * @date 2017/9/26 13:29
 */
@Service
public class UserServiceImpl implements UserService{

    @Autowired(required = false)
    private UserMapper userMapper;

    @Override
    public int addUser(UserDO user) {
        UserDO userTemp = get(user.getUserID());
        if(userTemp != null){
            return userMapper.addUser(userTemp);
        }
        return userMapper.addUser(user);
    }

    @Override
    public UserDO get(Long userId) {
        return userMapper.get(userId);
    }

    @Override
    public List<UserDO> getByName(String name) {
        return userMapper.getByName(name);
    }

    @Override
    public void printUserInfo(UserDO user) {
        System.out.println();
    }

    public UserMapper getUserMapper() {
        return userMapper;
    }

    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }
}
