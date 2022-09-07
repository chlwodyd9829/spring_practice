package com.example.spring.practice;

import com.example.spring.practice.interceptor.LoginInterceptor;
import com.example.spring.practice.repository.Item.ItemRepository;
import com.example.spring.practice.repository.Item.JdbcItemRepository;
import com.example.spring.practice.repository.member.JdbcMemberRepository;
import com.example.spring.practice.repository.member.MemberRepository;
import com.example.spring.practice.repository.order.JdbcOrderRepository;
import com.example.spring.practice.repository.order.OrderRepository;
import com.example.spring.practice.service.FileStore;
import com.example.spring.practice.service.item.ItemService;
import com.example.spring.practice.service.item.ItemServiceImpl;
import com.example.spring.practice.service.member.MemberService;
import com.example.spring.practice.service.member.MemberServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.sql.DataSource;


import static com.example.spring.practice.repository.connection.ConnectionConst.*;

@Configuration
public class AppConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())
                .order(1)
                .addPathPatterns("/**")
                .excludePathPatterns("/join","/login","/logout","/error","/css/**");
    }

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
        return new ItemServiceImpl(itemRepository(),fileStore());
    }
    @Bean
    public FileStore fileStore(){
        return new FileStore();
    }

    @Bean
    public OrderRepository orderRepository(){
        return new JdbcOrderRepository(dataSource());
    }
}
