package com.example.hellospring.domain;

import jakarta.persistence.*;

@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //특정 속성의 값을 자동 증가 → @Id
    private Long id;    //단순히 데이터 구분을 위해 사용(실제 회원 ID X)

    private String name;    //회원 이름

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
