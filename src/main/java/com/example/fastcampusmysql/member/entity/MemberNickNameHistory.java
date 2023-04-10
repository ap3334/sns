package com.example.fastcampusmysql.member.entity;

import com.example.fastcampusmysql.member.dto.MemberNicknameHistoryDto;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
public class MemberNickNameHistory {

    private final Long id;

    private final Long memberId;

    private final String nickname;

    private final LocalDateTime createdAt;

    @Builder
    public MemberNickNameHistory(Long id, Long memberId, String nickname, LocalDateTime createdAt) {
        this.id = id;
        this.memberId = Objects.requireNonNull(memberId);
        this.nickname = Objects.requireNonNull(nickname);
        this.createdAt = createdAt == null ? LocalDateTime.now() : createdAt;
    }

    public MemberNicknameHistoryDto toDto() {

        return new MemberNicknameHistoryDto(id, memberId, nickname, createdAt);
    }
}
