package hello.hello_spring.service;
import org.junit.jupiter.api.Test;
import hello.hello_spring.domain.Member;
import hello.hello_spring.repository.MemoryMemberRepository;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    /* 생성자 주입을 통해 memberRepository를 하나만 사용할 수 있도록 함 */
    @BeforeEach
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach
    public void afterEach() {
        memberRepository.clearStore();
    }

    @Test
    void 회원가입() {
        // given (무언가 주어졌을 때)
        Member member = new Member();
        member.setName("spring");

        // when (실행 했을 때)
        Long saveId = memberService.join(member);

        // then (어떤 결과가 나와야한다)
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외() {
        // given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        // when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));

        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        
        // try {
        //     memberService.join(member2);
        //     fail();
        // } catch (IllegalStateException e) {
        //     assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        // }
        

        // then
    }

    @Test
    void testFindOne() {

    }
}
