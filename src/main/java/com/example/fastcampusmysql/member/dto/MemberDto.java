package com.example.fastcampusmysql.member.dto;

import java.time.LocalDateTime;

public record MemberDto(
        Long id,
        String email,
        String nickname,
        LocalDateTime birthday
) {

}
