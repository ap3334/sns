package com.example.fastcampusmysql.member.service;

import com.example.fastcampusmysql.member.dto.MemberDto;
import com.example.fastcampusmysql.member.dto.MemberNicknameHistoryDto;
import com.example.fastcampusmysql.member.entity.MemberNickNameHistory;
import com.example.fastcampusmysql.member.repository.MemberNicknameHistoryRepository;
import com.example.fastcampusmysql.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class MemberReadService {

    private final MemberRepository memberRepository;
    private final MemberNicknameHistoryRepository memberNicknameHistoryRepository;

    public MemberDto getMember(Long id) {
        return memberRepository.findById(id).orElseThrow().toDto();
    }

    public List<MemberNicknameHistoryDto> getNicknameHistories(Long memberId) {
        return memberNicknameHistoryRepository.findAllByMemberId(memberId).stream()
                .map(MemberNickNameHistory::toDto).collect(Collectors.toList());
    }

}
