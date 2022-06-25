package com.zushiye.pojo.vo;

import lombok.Data;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author : zushiye
 * @create 2022/5/31 13:37
 */

@Data
public class AddUser {
    private String id;
    private String name;
    private String iden;
    private String college_id;
    private Integer grade;
    private String password;
    private String info;
    private String type;
}
