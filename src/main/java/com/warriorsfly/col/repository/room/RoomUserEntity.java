package com.warriorsfly.col.repository.room;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Timestamp;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table("room_users")
public class RoomUserEntity {
    @Id
    private Long id;
    /**
     * 房间id
     */
    private Long room_id;
    /**
     * 用户id
     */
    private Long user_id;
    /**
     * 加入时间
     */
    private Timestamp joined_time;
}
