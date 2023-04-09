package com.example.fastcampusmysql.member.dto;

import java.time.LocalDateTime;

public record MemberRegisterDto(
        String email,
        String nickname,
        LocalDateTime birthDay
) {
}
