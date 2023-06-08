package com.noint.messenger.service;

import com.noint.messenger.entity.Member;
import com.noint.messenger.mq.Rabbit;
import com.noint.messenger.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GenericService {
    private final MemberRepository memberRepository;
    private final Rabbit rabbit;
    public Member login(String name) {
        Member member = memberRepository.findByName(name);
        if (member == null) {
            member = memberRepository.save(Member.builder().id(UUID.randomUUID().toString()).name(name).build());
        }
        rabbit.ReceiveQueueRegister(name);
        return member;
    }
}
