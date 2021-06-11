1.callback
测试类 ： com.hy.test.callback.service.LoginServiceTest
实现：
    分为两种线程：
        1.主线程【单线程】 MainThreadProcessor 内存级数据处理线程 
        2.子线程【多线程】 AsyncProcessor  io数据处理线程
            a.doOperate 多线程异步执行
            b.doFinish 交给MainThreadProcessor处理

  
数据库参数配置 ：src\main\resources\mybatisConfig.xml