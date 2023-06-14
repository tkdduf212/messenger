package com.noint.messenger.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity(name = "chat_tb")
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;

    private String msg;

    @Column(name = "send_user_seq")
    private Long sendUserSeq;

    private Integer type; //chat type 0: 일반 채탱, 1: 알림 메시지(출입, 초대)

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "reg_dage")
    private Date regDate;
}
