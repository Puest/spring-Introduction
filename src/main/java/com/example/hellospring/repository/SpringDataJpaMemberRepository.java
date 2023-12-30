package com.example.hellospring.repository;

import com.example.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

 /*JpaRepository를 extends 하는 인터페이스를 만들어 놓으면,
 스프링 데이터 JPA가 인터페이스 구현체를 자동 생성하고 스프링 빈에 자동 등록함*/
public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {

    // JpaRepository 에서 save(), findById(), findAll()에 대해서 제공하므로 제공하지 않는 findByName만 만들어줌
    @Override
    Optional<Member> findByName(String name);
}
