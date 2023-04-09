package com.example.fastcampusmysql.member.controller;

import com.example.fastcampusmysql.member.dto.MemberDto;
import com.example.fastcampusmysql.member.dto.MemberRegisterDto;
import com.example.fastcampusmysql.member.service.MemberReadService;
import com.example.fastcampusmysql.member.service.MemberWriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
}
