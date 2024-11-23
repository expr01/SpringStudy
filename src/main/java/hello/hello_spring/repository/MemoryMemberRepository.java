package hello.hello_spring.repository;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import hello.hello_spring.domain.Member;

public class MemoryMemberRepository implements MemberRepository {

    // static을 붙이면 이 클래스 변수가 되고, 이 클래스의 모든 인스턴스가 값을 공유하게 됨.
    private static Map<Long, Member> store = new HashMap<>(); // 멤버를 저장하는 곳
    private static long sequence = 0L; // 0, 1, 2 ... 순서를 생성

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        // store의 value들을 돌면서 stream을 통해 연속적인 흐름으로 처리
        // filter에서 같은 문자열 찾아서 true면 stream에 남기고 아니면 버림.
        // findAny()에서 아무 객체 하나를 반환
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }
}
