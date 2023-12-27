package com.example.hellospring.repository;

import com.example.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;


public interface MemberRepository {
    Member save(Member member); //회원 객체(id, name) 저장
    Optional<Member> findById(Long id); //회원 id 조회, Optional<>을 사용하면 회원이 없을경우 null 값을 반환 O
    Optional<Member> findByName(String name);   //회원 이름 조회, Optional<>을 사용하면 회원이 없을경우 null 값을 반환 O
    List<Member> findAll(); //저장된 모든 회원 리스트 조회
}
