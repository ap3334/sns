package com.example.fastcampusmysql.member.repository;

import com.example.fastcampusmysql.member.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class MemberRepositoryJdbc implements MemberRepository {

    private final NamedParameterJdbcTemplate namedJdbcTemplate;

    private static final String TABLE = "member";

    @Override
    public Optional<Member> findById(Long id) {
        /*
        select *
        from Member
        where id = :id
         */

        String sql = "select * from %s where id = :id".formatted(TABLE);
        MapSqlParameterSource param = new MapSqlParameterSource()
                .addValue("id", id);

        RowMapper<Member> rowMapper = (ResultSet resultSet, int rowNum) -> Member
                .builder()
                .id(resultSet.getLong("id"))
                .email(resultSet.getString("email"))
                .nickname(resultSet.getString("nickname"))
                .birthday(resultSet.getObject("birthday", LocalDateTime.class))
                .createdAt(resultSet.getObject("createdAt", LocalDateTime.class))
                .build();

        Member member = namedJdbcTemplate.queryForObject(sql, param, rowMapper);

        return Optional.ofNullable(member);
    }

    @Override
    public Member save(Member member) {
        /*
            member id를 보고 갱신 또는 삭제를 한다.
            반환값은 id를 담아서 반환한다.
         */
        if (member.getId() == null) {
            return insert(member);
        }
        return update(member);
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
                .birthday(member.getBirthday())
                .createdAt(member.getCreatedAt())
                .build();
    }

    private Member update(Member member) {

        String sql = "update %s set email = :email, nickname = :nickname, birthday = :birthday where id = :id".formatted(TABLE);

        SqlParameterSource params = new BeanPropertySqlParameterSource(member);
        namedJdbcTemplate.update(sql, params);
        return member;
    }
}
