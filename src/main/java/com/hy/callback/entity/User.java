package com.hy.callback.entity;

import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * @author Hero
 * @description todo
 * @create 2021-06-11
 **/
@Data
@Accessors(chain = true)
@ToString
public class User {

    private Long uid;

    private String uname;

    private String pwd;
}
