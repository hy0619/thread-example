package com.hy.test.callback.service;

import com.hy.callback.db.user.IUserDao;
import com.hy.callback.entity.User;
import com.hy.callback.util.SqlSessionFactoryUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

/**
 * @author Hero
 * @description sqlSessionFactory test
 * @create 2021-06-11
 **/
public class SqlSessionFactoryTest {

    public static void main(String[] args) {
        SqlSessionFactoryUtil.init();
        SqlSession sqlSession = SqlSessionFactoryUtil.openSession();
        IUserDao userDao = sqlSession.getMapper(IUserDao.class);
        List<User> all = userDao.findAll();
        System.out.println(all.toString());
        sqlSession.close();
    }
}
