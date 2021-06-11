package com.hy.callback.processor;

/**
 * @author Hero
 * @description 异步接口
 * @create 2021-06-11
 **/
public interface IAsync {
    /**
     * @description: 异步之前操作
     * @return: void
     * @author: Hero
     * @date: 2021/6/11
     */
    default void doBefore(){}

    /**
     * @description: 异步操作
     * @param
     * @return: void
     * @author: Hero
     * @date: 2021/6/11
     */
    void doOperate();

    /**
     * @description: 异步之后操作
     * @return: void
     * @author: Hero
     * @date: 2021/6/11
     */
    default void doFinish(){};
}
