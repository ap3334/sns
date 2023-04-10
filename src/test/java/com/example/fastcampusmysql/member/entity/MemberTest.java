package com.example.fastcampusmysql.member.entity;

import com.example.fastcampusmysql.util.MemberFixtureFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MemberTest {

    @DisplayName("회원은 닉네임을 변경할 수 있다.")
    @Test
    void changeNicknameTest() {

        Member member = MemberFixtureFactory.create();
        String changeName = "change";

        member.changeNickname(changeName);

        Assertions.assertEquals(changeName, member.getNickname());

    }

    @DisplayName("회원의 닉네임은 10자를 초과할 수 없다.")
    @Test
    void changeNicknameMaxLength() {

        Member member = MemberFixtureFactory.create();
        String changeName = "changechangechange";


        Assertions.assertThrows(IllegalArgumentException.class,() -> member.changeNickname(changeName));
    }

}