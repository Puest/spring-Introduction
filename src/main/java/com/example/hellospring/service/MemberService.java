package com.example.hellospring.service;

import com.example.hellospring.domain.Member;
import com.example.hellospring.repository.MemberRepository;
import com.example.hellospring.repository.MemoryMemberRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public class MemberService {
    //메모리 회원 리퍼지토리 직접 생성(OCP, DIP위반)
//    private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /*
    회원 가입
    */
    public Long join(Member member) {

//        long start = System.currentTimeMillis();
//        try {
        //같은 이름(중복) 회원 X
        vaildateDuplicateMember(member);    //중복 회원 검증
        memberRepository.save(member);      //중복 회원이 아니면 저장
        return member.getId();
//        } finally {
//            long finish = System.currentTimeMillis();
//            long timeMs = finish - start;
//            System.out.println("join = " + timeMs + "ms");
//        }
    }

    private void vaildateDuplicateMember(Member member) {
        //ifPresent는 Optional 객체가 감싸고 있는 값이 존재할 경우 실행(즉, 값이 있다면 중복이므로 예외 발생)
        memberRepository.findByName(member.getName()).ifPresent(m -> {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });
    }

    /*
    전체 회원 조회
    */
    public List<Member> findMembers() {
//        long start = System.currentTimeMillis();
//        try {
        return memberRepository.findAll();
//        } finally {
//            long finish = System.currentTimeMillis();
//            long timeMs = finish - start;
//            System.out.println("findMembers = " + timeMs + "ms");
//        }
    }

    // memberId에 해당하는 회원(id) 조회
    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
