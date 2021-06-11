package com.hy.callback.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author Hero
 * @description sqlSessionFactory
 * @create 2021-06-11
 **/
public final class SqlSessionFactoryUtil {

    private static final SqlSessionFactoryUtil sqlSessionFactoryUtil
            = new SqlSessionFactoryUtil();

    private static SqlSessionFactory sqlSessionFactory ;

    private SqlSessionFactoryUtil(){}

    public SqlSessionFactoryUtil getInstance(){
        return sqlSessionFactoryUtil;
    }


    /**
     * @description: 初始化
     * @author: Hero
     * @date: 2021/6/11
     */
    public static void init(){
        try {
            InputStream inputStream = Resources.getResourceAsStream("mybatisConfig.xml");
            sqlSessionFactory =
                    new SqlSessionFactoryBuilder().build(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 开启MySql会话
     * @return
     */
    public static SqlSession openSession() {
        if(sqlSessionFactory==null) {
            throw new RuntimeException("SqlSessionFactory尚未初始化");
        }
        // 设置自动提交，不然没有提交
        return sqlSessionFactory.openSession(true);
    }



}
