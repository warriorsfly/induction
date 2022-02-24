package com.warriorsfly.induction.domain.rooms;

import com.warriorsfly.induction.domain.users.User;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.UUID;

@Data
public class Room implements Serializable {
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
