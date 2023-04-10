package com.example.fastcampusmysql.member.controller;

import com.example.fastcampusmysql.member.dto.MemberDto;
import com.example.fastcampusmysql.member.dto.MemberNicknameHistoryDto;
import com.example.fastcampusmysql.member.dto.MemberRegisterDto;
import com.example.fastcampusmysql.member.service.MemberReadService;
import com.example.fastcampusmysql.member.service.MemberWriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class MemberController {

    private final MemberWriteService memberWriteService;
    private final MemberReadService memberReadService;

    @PostMapping("/members")
    public MemberDto register(@RequestBody MemberRegisterDto dto) {
        return memberWriteService.create(dto);
    }

    @GetMapping("/members/{id}")
    public MemberDto getMember(@PathVariable Long id) {
        return memberReadService.getMember(id);
    }

    @PutMapping("/members/{id}")
    public MemberDto changeNickname(@PathVariable Long id, @RequestBody String nickname) {
        return memberWriteService.changeNickname(id, nickname);
    }

    @GetMapping("/members/{memberId}/nickname-histories")
    public List<MemberNicknameHistoryDto> getNicknameHistories(@PathVariable Long memberId) {

        return memberReadService.getNicknameHistories(memberId);

    }
}
