package hello.hello_spring.repository;
import hello.hello_spring.domain.Member;
import java.util.List;
import java.util.Optional;


public interface MemberRepository {
    Member save(Member member); // 회원 저장
    Optional<Member> findById(Long id); // 회원 조회 (id)
    Optional<Member> findByName(String Name); // 회원 조회 (name)
    List<Member> findAll(); // 모두 조회
}
