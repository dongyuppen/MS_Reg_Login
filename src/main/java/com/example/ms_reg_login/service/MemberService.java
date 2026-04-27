package com.example.ms_reg_login.service;

import com.example.ms_reg_login.domain.Member;
import com.example.ms_reg_login.dto.MemberJoinRequestDto;
import com.example.ms_reg_login.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public void join(MemberJoinRequestDto requestDto) {
        if (memberRepository.findByUsername(requestDto.getUsername()).isPresent()) {
            throw new IllegalArgumentException("이미 존재하는 아이디입니다.");
        }

        Member member = new Member();
        member.setUsername(requestDto.getUsername());
        member.setPassword(passwordEncoder.encode(requestDto.getPassword()));
        member.setRole("ROLE_USER");

        memberRepository.save(member);
    }
}