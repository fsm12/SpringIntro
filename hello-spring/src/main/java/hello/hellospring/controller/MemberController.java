package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

/** 오류) 'hello.hellospring.service.MemberService' that could not be found. : MemberService에 @Service 추가, MemoryMemberRepository에 @Repository 추가 */

@Controller // : 스프링이 뜰때 MemberController객체를 생성해서 Controller에 넣어주고 관리
            // @Component가 들어가 있음 (컴포넌트 스캔 방식)
public class MemberController {

    // private final MemberService memberService = new MemberService();

    // 1. 필드 주입 -> 바꿀 수 있는 방법이 없음 (스프링이 뜰때만 되니까)
    // @Autowired private MemberService memberService;

    // 2. setter 주입 -> public으로 setter가 열려있어야 하므로 문제가 생길 수 있음
    // private MemberService memberService;
    // @Autowired
    // public void setMemberService(MemberService memberService) {
    //     this.memberService = memberService;
    // }

    // 3. 생성자 주입 -> 아래 생성자를 통해서 memberService가 주입됨 (생성하는 시점에만 변경하고, 더이상 변경하지 못하도록 막을 수 있어서 주로 쓰임)
    private final MemberService memberService;
    @Autowired // : 스프링이 연관된 객체를 스프링 컨테이너에서 찾아서 넣어준다
    public MemberController(MemberService memberService) {
        this.memberService = memberService;

        // AOP : 프록시를 확인하는 방법
        /* 출력 결과물
        => memberService = class hello.hellospring.service.MemberService$$EnhancedBySpringCGLIB~~
        [ 기본적으로 스프링 컨테이너는 <aop: scoped-proxy> 요소로 마크업된 빈에 대한 프록시를 생성하면 cglib 기반의 프록시를 생성 ]
        */
        System.out.println("memberService = "+ memberService.getClass());
    }

    @GetMapping("/members/new")
    public String createForm(){
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form){
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model){
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }

}
