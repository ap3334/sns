package com.example.fastcampusmysql.member.repository;

import com.example.fastcampusmysql.member.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class MemberRepositoryJdbc implements MemberRepository {

    private final NamedParameterJdbcTemplate namedJdbcTemplate;

    @Override
    public Member save(Member member) {
        /*
            member id를 보고 갱신 또는 삭제를 한다.
            반환값은 id를 담아서 반환한다.
         */
        if (member.getId() == null) {
            return insert(member);
        }
        return member;
    }

    private Member insert(Member member) {

        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(namedJdbcTemplate.getJdbcTemplate())
                .withTableName("Member")
                .usingGeneratedKeyColumns("id");

        SqlParameterSource params = new BeanPropertySqlParameterSource(member);

        Number id = simpleJdbcInsert.executeAndReturnKey(params);

        return Member.builder()
                .id(id.longValue())
                .nickname(member.getNickname())
                .email(member.getEmail())
                .birthDay(member.getBirthDay())
                .createdAt(member.getCreatedAt())
                .build();
    }

    private Member update(Member member) {
        // TODO : implemented
        return member;
    }
}
