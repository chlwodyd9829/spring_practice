package com.example.spring.practice.repository.member;


import com.example.spring.practice.domain.member.Member;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class JdbcMemberRepository implements MemberRepository {
    private final JdbcTemplate jdbcTemplate;
    public static List<String> ColNames = null;

    public JdbcMemberRepository(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    @Override
    public Member save(Member member) {
        String sql = "insert into member values(?,?,?,?)";
        jdbcTemplate.update(sql,member.getId(),member.getPassword(),member.getName(),member.getAddress());
        return member;
    }

    @Override
    public Optional<Member> findById(String loginId) {
        String sql = "select * from member where id = ?";
        List<Member> result = jdbcTemplate.query(sql, memberRowMapper(), loginId);
        return result.stream().findAny();
    }
    public List<String> getColNames(){
        return ColNames;
    }
    private RowMapper<Member> memberRowMapper() {
        return (rs,rowNum)->{
            Member member  = new Member();
            member.setId(rs.getString("id"));
            member.setPassword(rs.getString("password"));
            member.setName(rs.getString("name"));
            member.setAddress(rs.getString("address"));
            if(ColNames == null){
                ColNames = new ArrayList<>();
                ResultSetMetaData metaData = rs.getMetaData();
                for(int i=1; i<=metaData.getColumnCount();i++){
                    ColNames.add(metaData.getColumnName(i));
                }
            }
            return member;
        };
    }


    @Override
    public List<Member> findAll() {
        String sql = "select * from member";
        return jdbcTemplate.query(sql,memberRowMapper());
    }
}
