package com.hy.callback.service;

import com.hy.callback.db.user.IUserDao;
import com.hy.callback.processor.IAsync;
import com.hy.callback.entity.User;
import com.hy.callback.processor.AsyncProcessor;
import com.hy.callback.util.SqlSessionFactoryUtil;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Function;

/**
 * @author Hero
 * @description 登陆服务类
 * @create 2021-06-11
 **/
public class LoginService {
    private static Logger logger = LoggerFactory.getLogger(LoginService.class);
    private static final LoginService loginService = new LoginService();

    private LoginService(){}

    public static LoginService getInstance(){
        return loginService;
    }

    public void doLogin(String uname , String pwd , Function<User, Void> callBackFn){
        logger.info("doLogin开始，当前线程：{}" , Thread.currentThread().getName());
        IAsync async = new IAsync() {
            User user = null;
            @Override
            public void doOperate() {
                logger.info("async doOperate开始，当前线程：{}" , Thread.currentThread().getName());
                try (SqlSession session = SqlSessionFactoryUtil.openSession()) {
                    IUserDao userDao = session.getMapper(IUserDao.class);
                    User one = userDao.findByName(uname);
                    if (one == null) {
                        return;
                    }
                    if (!one.getPwd().equals(pwd)) {
                        return;
                    }
                    user = new User().setPwd(one.getPwd())
                            .setUid(one.getUid())
                            .setUname(one.getUname());
                    return;
                } catch (Exception e) {
                    logger.error(e.getMessage());
                }

            }

            @Override
            public void doFinish() {
                logger.info("async doFinish开始，当前线程：{}" , Thread.currentThread().getName());
                callBackFn.apply(user);
            }
        };
        AsyncProcessor.getInstance().submit(async);
    }
}
