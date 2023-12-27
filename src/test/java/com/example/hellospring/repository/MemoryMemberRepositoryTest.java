package com.example.hellospring.repository;

import com.example.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class MemoryMemberRepositoryTest {

    // 메모리 리퍼지토리 구현체 생성
    MemoryMemberRepository repository = new MemoryMemberRepository();

    // 테스트가 끝날 때마다 실행(store(Map)을 clear → 메모리에 남아있는 결과가 있을 수 있기 때문)
    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }

    // 메모리에 member 가 저장이 제대로 되는지 검증하는 함수
    @Test
    public void save() {
        //given (name이 spring인 member가 저장)
        Member member = new Member();
        member.setName("spring");
        repository.save(member);

        //when (메모리에서 name이 member.getId()를 조회)
        Member member1 = repository.findById(member.getId()).get(); // findById가 Optional<>로 get으로 member 객체 반환

        //then (메모리에서 가져온 member값과 생성한 member1이 같은지 검증)
        Assertions.assertThat(member).isEqualTo(member1);
    }

    // 메모리에 member가 저장이 제대로 되는지 검증하는 함수
    @Test
    public void findByName() {
        //given (name이 spring1, spring2인 member1,2가 저장)
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        //when (메모리에서 name이 spring1인 멤버를 조회)
        Member member = repository.findByName("spring1").get();

        //then (메모리에서 가져온 member값과 생성한 member1이 같은지 검증)
        Assertions.assertThat(member1).isEqualTo(member);
    }

    // 메모리에 모든 member가 제대로 조회되는지 검증하는 함수
    @Test
    public void findAll() {
        //given(메모리에 member1,2 저장)
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        //when(메모리에 있는 모든 member 조회)
        List<Member> result = repository.findAll();

        //then(메모리에 있는 모든 member 수와 위에서 저장한 member 수가 같은지 검사)
        Assertions.assertThat(result.size()).isEqualTo(2);
    }
}
