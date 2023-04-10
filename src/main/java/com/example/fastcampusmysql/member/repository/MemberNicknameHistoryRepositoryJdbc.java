package com.example.fastcampusmysql.member.repository;

import com.example.fastcampusmysql.member.entity.MemberNickNameHistory;
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
import java.util.List;

@RequiredArgsConstructor
@Repository
public class MemberNicknameHistoryRepositoryJdbc implements MemberNicknameHistoryRepository {

    private final NamedParameterJdbcTemplate namedJdbcTemplate;

    private static final String TABLE = "MemberNicknameHistory";

    private static final RowMapper<MemberNickNameHistory> rowMapper = (ResultSet resultSet, int rowNum) ->
            MemberNickNameHistory
                .builder()
                .id(resultSet.getLong("id"))
                .memberId(resultSet.getLong("memberId"))
                .nickname(resultSet.getString("nickname"))
                .createdAt(resultSet.getObject("createdAt", LocalDateTime.class))
                .build();

    @Override
    public MemberNickNameHistory save(MemberNickNameHistory history) {
        /*
            member id를 보고 갱신 또는 삭제를 한다.
            반환값은 id를 담아서 반환한다.
         */
        if (history.getId() == null) {
            return insert(history);
        }
        throw new UnsupportedOperationException("MemberNickNameHistory는 갱신을 지원하지 않습니다.");
    }

    @Override
    public List<MemberNickNameHistory> findAllByMemberId(Long memberId) {

        String sql = "select * from %s where memberId = :memberId".formatted(TABLE);
        MapSqlParameterSource param = new MapSqlParameterSource().addValue("memberId", memberId);

        return namedJdbcTemplate.query(sql, param, rowMapper);

    }

    private MemberNickNameHistory insert(MemberNickNameHistory history) {

        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(namedJdbcTemplate.getJdbcTemplate())
                .withTableName("MemberNickNameHistory")
                .usingGeneratedKeyColumns("id");

        SqlParameterSource params = new BeanPropertySqlParameterSource(history);

        Number id = simpleJdbcInsert.executeAndReturnKey(params);

        return MemberNickNameHistory.builder()
                .id(id.longValue())
                .memberId(history.getMemberId())
                .nickname(history.getNickname())
                .createdAt(history.getCreatedAt())
                .build();
    }

}
