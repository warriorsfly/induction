package com.warriorsfly.induction.domain;

public enum MessageTo {
    /**
     * App内所有人
     */
    All,
    /**
     * 发送给机器人
     */
    Bot,
    /**
     * 房间中发消息
     */
    Room,
    /**
     * 给某人发消息
     */
    Employee,
    /**
     * 给某些发消息
     */
    Some,
    /**
     * 给某Ip发消息
     */
    Ip
}
