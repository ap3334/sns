package com.example.fastcampusmysql.member.service;

import com.example.fastcampusmysql.member.dto.MemberDto;
import com.example.fastcampusmysql.member.dto.MemberRegisterDto;
import com.example.fastcampusmysql.member.entity.Member;
import com.example.fastcampusmysql.member.entity.MemberNickNameHistory;
import com.example.fastcampusmysql.member.repository.MemberNicknameHistoryRepository;
import com.example.fastcampusmysql.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MemberWriteService {

    private final MemberRepository memberRepository;
    private final MemberNicknameHistoryRepository memberNicknameHistoryRepository;

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
                .birthday(dto.birthday())
                .build();

        Member savedMember = memberRepository.save(member);
        saveMemberNicknameHistory(savedMember);

        return savedMember.toDto();
    }

    public MemberDto changeNickname(Long memberId, String nickname) {
        /*
        1. 회원의 이름을 변경
        2. 변경 내역을 저장
         */

        Member member = memberRepository.findById(memberId).orElseThrow();
        member.changeNickname(nickname);
        Member savedMember = memberRepository.save(member);

        saveMemberNicknameHistory(member);

        return savedMember.toDto();
    }

    private void saveMemberNicknameHistory(Member member) {
        MemberNickNameHistory history = MemberNickNameHistory.builder()
                .memberId(member.getId())
                .nickname(member.getNickname())
                .build();

        memberNicknameHistoryRepository.save(history);
    }
}
