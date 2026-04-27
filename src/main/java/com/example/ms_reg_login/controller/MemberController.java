package com.example.ms_reg_login.controller;

import com.example.ms_reg_login.dto.MemberJoinRequestDto;
import com.example.ms_reg_login.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/join")
    public String join(@RequestBody MemberJoinRequestDto requestDto) {
        memberService.join(requestDto);
        return "회원가입이 완료되었습니다.";
    }
}