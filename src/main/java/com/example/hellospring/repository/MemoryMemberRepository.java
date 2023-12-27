package com.example.hellospring.repository;

import com.example.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

/*
 ** 동시성 문제가 고려되어 있지 않음, 실무에서는 ConcurrentHashMap, AtomicLong 사용 고려
 */
public class MemoryMemberRepository implements MemberRepository {

    //Map<Key:Member 의 id, value: Member>
    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L; // 시스템에서 Member의 id를 자동으로 넣어주기 위함

    @Override
    public Member save(Member member) {
        member.setId(++sequence);   //id 값을 자동으로 1씩 증가
        store.put(member.getId(), member);  //store(Map)의 member 의 id, member 를 넣음
        return member;
    }

    //Key가 id인 memeber를 반환하는 함수
    @Override
    public Optional<Member> findById(Long id) {
        //Optional.ofNullable 로 값을 리턴하는 이유는 store 에 id가 없을 경우 null 반환 → NPE 발생
        return Optional.ofNullable(store.get(id));
    }

    // 메모리에 저장된 회원들 중 파라미터(name)과 같은 member의 name을 가진 member를 반환하는 함수
    @Override
    public Optional<Member> findByName(String name) {
        // 데이터소스 객체 집합.steam생성.중개연산.최종연산
        return store.values()
                .stream()
                // stream에서 filter(조건)을 만족하는 어떤 요소 반환
                .filter(member -> member.getName().equals(name))
                .findAny();
    }

    // 메모리에 저장된 모든 member(value)들을 반환하는 함수
    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    // 메모리에 저장된 모든 회원 정보(id, member)를 삭제하는 함수
    public void clearStore(){
        store.clear();
    }

}
