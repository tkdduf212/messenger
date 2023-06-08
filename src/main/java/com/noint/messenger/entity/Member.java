package com.noint.messenger.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "member")
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;
    @Column(unique = true)
    private String id;
    @Column(unique = true)
    private String name;
}
