package com.example.spring.practice;

import com.example.spring.practice.repository.Item.ItemRepository;
import com.example.spring.practice.repository.Item.JdbcItemRepository;
import com.example.spring.practice.repository.member.JdbcMemberRepository;
import com.example.spring.practice.repository.member.MemberRepository;
import com.example.spring.practice.service.item.ItemService;
import com.example.spring.practice.service.item.ItemServiceImpl;
import com.example.spring.practice.service.member.MemberService;
import com.example.spring.practice.service.member.MemberServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

import static com.example.spring.practice.repository.connection.ConnectionConst.*;

@Configuration
public class AppConfig {
    @Bean
    public DataSource dataSource(){
        return new DriverManagerDataSource(URL,USERNAME,PASSWORD);
    }
    @Bean
    public MemberService memberService(){
        return new MemberServiceImpl(memberRepository());
    }
    @Bean
    public MemberRepository memberRepository(){
        return new JdbcMemberRepository(dataSource());
    }

    @Bean
    public ItemRepository itemRepository(){
        return new JdbcItemRepository(dataSource());
    }
    @Bean
    public ItemService itemService(){
        return new ItemServiceImpl(itemRepository());
    }
}
