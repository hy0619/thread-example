package com.hy.callback.db.user;

import com.hy.callback.entity.User;

import java.util.List;

public interface IUserDao {

    /**
     * 根据用户名查找用户
     * @param userName 用户名
     * @return UserEntity
     */
    User findByName(String userName);

    /**
     * 添加一个UserEntity
     */
    void addUserEntity(User userEntity);

    /**
     * 查找所有用户
     */
    List<User> findAll();
}
