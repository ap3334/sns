package com.example.fastcampusmysql.member.repository;

import com.example.fastcampusmysql.member.entity.MemberNickNameHistory;

import java.util.List;

public interface MemberNicknameHistoryRepository {

    MemberNickNameHistory save(MemberNickNameHistory memberNickNameHistory);

    List<MemberNickNameHistory> findAllByMemberId(Long memberId);

}
