package com.warriorsfly.induction.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.sql.Timestamp;
import java.util.UUID;

@Data
public class User {
    @Id
    private UUID id;
    /**
     * 所属应用
     */
    private String appId;
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
    /**
     * 是否机器人
     */
    private boolean isBot;
}
