package hello.hello_spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import hello.hello_spring.domain.Member;
import hello.hello_spring.service.MemberService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class MemberController {
    
    /* 스프링 컨테이너에 등록하고 사용하도록 해야함. 하나 이상 만들 필요 없음. */
    private final MemberService memberService;

    /* 스프링이 스프링 컨테이너에 있는 memberService 가져와서 연결시켜 줌. */
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }

    @PostMapping("members/new")
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());

        System.out.println("member: " + member.getName());

        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping("members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }
    
    
}
