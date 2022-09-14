package com.example.spring.practice.repository.member;


import com.example.spring.practice.domain.member.Classification;
import com.example.spring.practice.domain.member.Member;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Slf4j
@Repository
public class JdbcMemberRepository implements MemberRepository {
    private final JdbcTemplate jdbcTemplate;

    public JdbcMemberRepository(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    @Override
    public Member save(Member member) {
        String sql = "insert into member values(?,?,?,?,?)";
        log.info("classification = {}",member.getClassification().getClass());
        jdbcTemplate.update(sql,member.getId(),member.getPassword(),member.getName(),member.getAddress(),member.getClassification().toString());
        return member;
    }

    @Override
    public Optional<Member> findById(String loginId) {
        String sql = "select * from member where id = ?";
        List<Member> result = jdbcTemplate.query(sql, memberRowMapper(), loginId);
        return result.stream().findAny();
    }

    @Override
    public void update(Member member) {
        String sql = "update member set password=?, name=?, address=?, classification=? where id=?";
        jdbcTemplate.update(sql, member.getPassword(), member.getName(), member.getAddress(), member.getClassification().toString(), member.getId());
    }

    private RowMapper<Member> memberRowMapper() {
        return (rs,rowNum)->{
            Member member  = new Member();
            member.setId(rs.getString("id"));
            member.setPassword(rs.getString("password"));
            member.setName(rs.getString("name"));
            member.setAddress(rs.getString("address"));
            member.setClassification(Classification.valueOf(rs.getString("classification")));
            return member;
        };
    }


    @Override
    public List<Member> findAll() {
        String sql = "select * from member";
        return jdbcTemplate.query(sql,memberRowMapper());
    }
}
