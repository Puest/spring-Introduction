package com.example.hellospring.service;

import com.example.hellospring.domain.Member;
import com.example.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MemberServiceTest {

    MemberService memberService;    //회원 서비스
    MemoryMemberRepository memberRepository;    //회원 리퍼지토리

    //테스트가 실행되기 전에 회원 서비스, 리퍼지토리가 생성
    @BeforeEach
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    //테스트가 끝나면 메모리 초기화
    @AfterEach
    public void afterEach() {
        memberRepository.clearStore();
    }


    @Test
    void join() /*join대신 회원가입이라고 적어도 무방비*/ {
        //given
        Member member = new Member();
        member.setName("hello");

        //when
        Long saveId = memberService.join(member);

        //then(검증)
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    void 중복_회원_예의() {
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when
        memberService.join(member1);
        //assertThrows로 같은 값인 member2가 가입 시 IllegalStateException 예외 발생
        IllegalStateException e = assertThrows(IllegalStateException.class,
                () -> memberService.join(member2));
        //저장된 IllegalStateException 값과 메시지가 같은지 검증
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

       /*
       // when을 try~catch 구문으로 구성 시
       try {
            memberService.join(member2);
            fail();
        } catch (IllegalStateException e) {
            Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        }
        */
    }

    @Test
    void findOne() {
    }
}