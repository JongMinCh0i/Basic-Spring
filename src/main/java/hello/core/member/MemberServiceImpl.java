package hello.core.member;

public class MemberServiceImpl implements MemberService {

    /*
        DIP 위반한 사례
        new MemoryMemberRepository(); 실제 할당하는 곳이 구현체를 의존한다.
        즉  MemberServiceImpl 는 MemberRepository(추상화) , MemoryMemberRepository(구체화) 둘 다 의존 관계에 있다(bad)
     */

    private final MemberRepository memberRepository = new MemoryMemberRepository();


    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
}