package com.warriorsfly.col.domain.users;

import lombok.Data;
import java.io.Serializable;

@Data
public class User implements Serializable {
    private String id;
    /**
     * 用户类型
     * Employee 职工
     * Ip 指定Ip
     * Bot 机器人
     */
    private UserType type;
    /**
     * 用户名
     */
    private String name;
    /**
     * 别名
     */
    private String remark;
}
