package com.example.fastcampusmysql.member.service;

import com.example.fastcampusmysql.member.dto.MemberDto;
import com.example.fastcampusmysql.member.dto.MemberRegisterDto;
import com.example.fastcampusmysql.member.entity.Member;
import com.example.fastcampusmysql.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MemberWriteService {

    private final MemberRepository memberRepository;

    public MemberDto create(MemberRegisterDto dto) {
        /*
            목표 - 회원정보를 등록한다.(이메일, 닉네임, 생년월일)
                 - 닉네임은 10자를 넘길 수 없다.

            파라미터 - memberRegisterCommand
            val member = Member.of(memberRegisterCommand)
            memberRepository.save()
         */

        Member member = Member.builder()
                .nickname(dto.nickname())
                .email(dto.email())
                .birthDay(dto.birthDay())
                .build();

        return memberRepository.save(member).toDto();
    }
}
