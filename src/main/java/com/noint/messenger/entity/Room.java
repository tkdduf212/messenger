package com.noint.messenger.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity(name = "room_tb")
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;

    @Column(name = "room_id", unique = true)
    private String roomId;

    @Column(nullable = false)
    private Integer type; //0 : 개인채팅방, 1: 단체채팅방

    @Column(name = "reg_date", nullable = false)
    private Date regDate;

    @Column(name = "del_date")
    private Date delDate;
}
