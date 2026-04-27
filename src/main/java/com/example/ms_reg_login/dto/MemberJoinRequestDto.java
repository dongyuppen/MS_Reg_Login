package com.example.ms_reg_login.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MemberJoinRequestDto {
    private String username;
    private String password;
}