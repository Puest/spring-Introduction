package com.example.hellospring;

import com.example.hellospring.app.TimeTraceAop;
import com.example.hellospring.domain.Member;
import com.example.hellospring.repository.JdbcMemberRepository;
import com.example.hellospring.repository.JpaMemberRepository;
import com.example.hellospring.repository.MemberRepository;
import com.example.hellospring.repository.MemoryMemberRepository;
import com.example.hellospring.service.MemberService;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {


    // @Autowired DataSource dataSource; //아래 두 값과 동일

/*  DI(Dependency Injection)
    private final DataSource dataSource;
    @Autowired
    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }
*/
//    private EntityManager em;

    private final MemberRepository memberRepository;

    // 스프링 데이터 JPA가 만든 인터페이스 구현체(memberRepository) 자동 주입
    @Autowired
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    // memberService 스프링 빈 등록
    @Bean
    public MemberService memberService() {
        // memberService가 memberRepository 의존
        return new MemberService(memberRepository);
    }

//    @Bean
//    public TimeTraceAop timeTraceAop() {
//        return new TimeTraceAop();
//    }
//    @Bean
//    public MemberRepository memberRepository() {
////        return new MemoryMemberRepository();
////        return new JdbcMemberRepository(dataSource);
//        return new JpaMemberRepository(em);
//    }
}
