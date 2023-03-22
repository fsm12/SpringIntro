package hello.core.member;

public class MemberServiceImpl implements MemberService{

    // MemberServiceImpl은 MemberRepository(추상화), MemoryMemberRepository(구체화)에 의존함 (DIP 위반)
    // private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final MemberRepository memberRepository;

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
}
