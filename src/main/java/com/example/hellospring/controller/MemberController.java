package com.example.hellospring.controller;

import com.example.hellospring.domain.Member;
import com.example.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {
    //private final MemberService memberService = new MemberService();
    // 스프링 컨테이너에 스피링 빈으로 등록을 해두고 가져다 쓰는 방식(같은 MemberService를 공유)
    private final MemberService memberService;

    // 회원 컨트롤러 & 회원 서비스 연결
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    // /members/new에 대한 GET 요청(localhost8080/members/new)이 들어오면
    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm";  // 해당 html String으로 변환
    }

    // /members/new에 대한 POST 요청이 들어오면
    @PostMapping("/members/new")
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName()); //form에서 받은 name을 넣어줌

        memberService.join(member); //회원가입
        return "redirect:/";    // 홈 화면으로
    }

    // /members에 대한 GET 요청(localhost8080/members)이 들어오면
    @GetMapping("/members")
    public String list(Model model) {
        // 모든 회원 리스트로 반환
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members); // (key="members", value=members)
        return "members/memberlist";

    }
}
