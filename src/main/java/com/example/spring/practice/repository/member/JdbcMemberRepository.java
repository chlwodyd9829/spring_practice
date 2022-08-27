package com.example.spring.practice.repository.member;


import com.example.spring.practice.domain.member.Member;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

@Repository
public class JdbcMemberRepository implements MemberRepository {
    private final JdbcTemplate jdbcTemplate;

    public JdbcMemberRepository(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    @Override
    public Member save(Member member) {
        return null;
    }

    @Override
    public Optional<Member> findById(String loginId) {
        String sql = "select * from member where id = ?";
        List<Member> result = jdbcTemplate.query(sql, memberRowMapper(), loginId);
        return result.stream().findAny();
    }

    private RowMapper<Member> memberRowMapper() {
        return (rs,rowNum)->{
            Member member  = new Member();
            member.setId(rs.getString("id"));
            member.setPassword(rs.getString("password"));
            return member;
        };
    }

    @Override
    public List<Member> findAll() {
        String sql = "select * from member";
        return jdbcTemplate.query(sql,memberRowMapper());
    }
}
