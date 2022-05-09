package hello.login.domain.member;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.*;

/**
 * 동시성 문제가 고려되어 있지 않음, 실무에서는 ConcurrentHashMap, AtomicLong 사용 고려
 */

@Slf4j
@Repository
public class MemberRepository {

    private static Map<Long, Member> store = new HashMap<>(); //static 사용
    private static Long sequence = 0L; // static 사용

    //회원가입 폼
    public Member save(Member member) {
        member.setId(++sequence);
        log.info("save : member = {}", member);
        store.put(member.getId(), member);
        return member;
    }

    //회원 id로 회원 찾기
    public Member findById(Long id) {
        return store.get(id);
    }

    //회원 loginId로 회원 찾기
    public Optional<Member> findByLoginId(String loginId) {
        return findAll().stream()
                .filter(m -> m.getLoginId().equals(loginId))
                .findFirst();
    }

    //전체 회원 목록
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    //값 초기화
    public void clearStore() {
        store.clear();
    }


    

}
