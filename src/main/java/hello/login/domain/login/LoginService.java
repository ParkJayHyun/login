package hello.login.domain.login;

import hello.login.domain.member.Member;
import hello.login.domain.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final MemberRepository memberRepository;


    /**
     * return null 이면 로그인 실패
     * @param loginID
     * @param password
     * @return
     */
    public Member login(String loginID, String password) {
        return memberRepository.findByLoginId(loginID)
                .filter(m -> m.getPassword().equals(password))
                .orElse(null);
    }

}
