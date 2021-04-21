package hello.core.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;

    /*
    자, 처음에 만들때는 AppConfig 객체생성해서, appConfig.memberRepository()로 가져왔어.
    그러면 MemoryMemberRepository()객체를 생성해서 반환해주는 방식을 썼어.
    이 부분을 스프링은 bean으로 관리를 하게 돼. bean으로 Annotation~에서 bean꺼내서 할당해줬어.
    ac.getBean(MemberRepository.class)

     */

    @Autowired //ac.getBean(MemberRepository.class) // MemberRepository에 맞는 구체화 가지고 와서 설정해줌.
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
    //의존관계를 마치 외부에서 주입해주는 것 같다고 해서 DI(Dependency Injection) - 의존 관계 주입
    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    //테스트 용도
    public MemberRepository getMemberRepository(){
        return memberRepository;
    }
}
