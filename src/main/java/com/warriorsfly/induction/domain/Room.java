package com.warriorsfly.induction.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.sql.Timestamp;
import java.util.UUID;

@Data
public class Room {
    @Id
    private UUID id;

    private User creator;
    /**
     * 所属应用
     */
    private String appId;
    /**
     * 用户名
     */
    private String name;
    /**
     * 别名
     */
    private String remark;

    private Timestamp createTime;

}
