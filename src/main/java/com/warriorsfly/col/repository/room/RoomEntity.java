package com.warriorsfly.col.repository.room;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table("rooms")
public class RoomEntity {
    @Id
    private Long id;
    private String name;
    private String description;
    private String memo;
    
}
