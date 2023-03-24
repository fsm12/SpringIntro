package hello.core.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component // 컴포넌트 스캔
public class MemberServiceImpl implements MemberService{

    // MemberServiceImpl은 MemberRepository(추상화), MemoryMemberRepository(구체화)에 의존함 (DIP 위반)
    // private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final MemberRepository memberRepository;

    @Autowired // 컴포넌트 스캔 - 자동 의존관계 주입 마치 [ac.getBean(MemberRepository.class)]처럼 동작
    public MemberServiceImpl(MemberRepository memberRepository) {

        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    //  싱글톤 유지되는지에 대한 테스트 용도
    public MemberRepository getMemberRepository(){
        return memberRepository;
    }
}
