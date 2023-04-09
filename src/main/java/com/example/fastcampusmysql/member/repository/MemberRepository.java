package com.example.fastcampusmysql.member.repository;

import com.example.fastcampusmysql.member.entity.Member;

import java.util.Optional;

public interface MemberRepository {

    Member save(Member member);

    Optional<Member> findById(Long id);
}
