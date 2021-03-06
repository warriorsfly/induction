package com.warriorsfly.col.repository.user;

import com.warriorsfly.col.domain.users.UserType;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table("users")
public class UserEntity {
    @Id
    private Long id;
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
     * hash过的密码
     */
    private String hashed_password;
    /**
     * 别名
     */
    private String remark;
}
