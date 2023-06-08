package com.noint.messenger.controller;

import com.noint.messenger.entity.Member;
import com.noint.messenger.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @GetMapping(value = "/all", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<Member>> getAllMembers() {
        List<Member> members = memberService.findAll();
        return new ResponseEntity<List<Member>>(members, HttpStatus.OK);
    }

    @GetMapping(value = "/{seq}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Member> getMember(@PathVariable Long seq) {
        Optional<Member> member = memberService.findById(seq);
        return new ResponseEntity<Member>(member.get(), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{seq}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Void> deleteMember(@PathVariable Long seq) {
        memberService.deleteById(seq);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{seq}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Member> updateMember(@PathVariable Long seq, @RequestBody Member member) {
        memberService.updateById(seq, member);
        return new ResponseEntity<Member>(member, HttpStatus.OK);
    }

    @PostMapping("/join")
    public ResponseEntity<Member> save(@RequestBody Member member) {
        return new ResponseEntity<Member>(memberService.save(member), HttpStatus.OK);
    }

    @GetMapping("/saveMember")
    public ResponseEntity<Member> save(HttpServletRequest req, @RequestBody Member member) {
        return new ResponseEntity<Member>(memberService.save(member), HttpStatus.OK);
    }
}
