package com.noint.messenger.service;

import com.noint.messenger.entity.Member;
import com.noint.messenger.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public List<Member> findAll(){
        List<Member> members = new ArrayList<>();
        memberRepository.findAll().forEach(e -> members.add(e));
        return members;
    }

    public Optional<Member> findById(Long seq) {
        Optional<Member> member = memberRepository.findById(seq);
        return member;
    }

    public void deleteById(Long seq) {
        memberRepository.deleteById(seq);
    }

    public Member save(Member member) {
        memberRepository.save(member);
        return member;
    }

    public void updateById(Long seq, Member member) {
        Optional<Member> e = memberRepository.findById(seq);
        if (e.isPresent()) {
            e.get().setId(member.getId());
            e.get().setName(member.getName());
            memberRepository.save(e.get());
        }
    }
}
