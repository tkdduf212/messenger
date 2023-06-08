package com.noint.messenger.repository;

import com.noint.messenger.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {
    public List<Member> findById(String id);

    public Member findByName(String name);

    public List<Member> findByNameLike(String keyword);

}
