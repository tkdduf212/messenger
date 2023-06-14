package com.noint.messenger.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity(name = "user_room_tb")
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {"user_seq", "room_seq"})
})
public class UserRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;

    @Column(name = "room_seq")
    private Long roomSeq;

    @Column(name = "user_seq")
    private Long userSeq;

    @Column(name = "reg_date")
    private Date regDate;

    @Column(name = "del_date")
    private Date delDate;
}
