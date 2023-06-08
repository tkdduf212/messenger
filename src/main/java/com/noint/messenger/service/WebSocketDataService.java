package com.noint.messenger.service;

import com.noint.messenger.entity.Member;
import com.noint.messenger.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WebSocketDataService {
    private final MemberRepository memberRepository;

    public Member getData(Long seq) {
        return memberRepository.findById(seq).get();
    }
}
