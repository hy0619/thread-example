package com.hy.test.callback.service;

import com.hy.callback.processor.MainThreadProcessor;
import com.hy.callback.service.LoginService;
import com.hy.callback.util.SqlSessionFactoryUtil;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Hero
 * @description 登录服务测试类
 * @create 2021-06-11
 **/
public class LoginServiceTest {

    @Before
    public void testBefore(){
        SqlSessionFactoryUtil.init();
    }

    private static Logger logger = LoggerFactory.getLogger(LoginServiceTest.class);
    @Test
    public void testLogin(){
        String uname = "1";
        String pwd = "1";
        //获取主线程
        MainThreadProcessor.getInstance().submit(() -> {
            LoginService.getInstance().doLogin(uname , pwd , (user)->{
                logger.info("回调函数,当前线程为：{} " , Thread.currentThread().getName());
                if(user == null ){
                    logger.info("登录失败 , user 为空");
                }else{
                    logger.info( "回调用户信息：{}", user);
                }
                return null;
            });
        });


        for(;;) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
