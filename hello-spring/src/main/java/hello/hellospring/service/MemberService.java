package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

// @Service -> @Component 가 들어가 있음 (컴포넌트 스캔 방식)
@Transactional // JPA 사용시 필수
public class MemberService {
    // Ctrl + Shift + T

    // 아래 방법보다는
    // private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final MemberRepository memberRepository;
    @Autowired
    public MemberService(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }

    /** 회원가입 */
    public Long join(Member member) {
        // 같은 이름이 있는 중복 회원X
//        Optional<Member> result = memberRepository.findByName(member.getName());
//        result.ifPresent(member1 -> {
//            throw new IllegalStateException("이미 존재하는 회원입니다.");
//        });

//        아래 메소드 드래그 -> Ctrl + Alt + M
//        memberRepository.findByName(member.getName())
//                .ifPresent(member1 -> {
//                    throw new IllegalStateException("이미 존재하는 회원입니다.");
//                });

        // 시간측정방법 - 유지보수 까다로움
        long start = System.currentTimeMillis();
        try {
            validateDuplicateMember(member);
            memberRepository.save(member);
            return member.getId();
        }finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("join "+timeMs+"ms");
        }
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
            .ifPresent(member1 -> {
                throw new IllegalStateException("이미 존재하는 회원입니다.");
            });
    }

    /** 전체 회원 조회 */
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }
}
