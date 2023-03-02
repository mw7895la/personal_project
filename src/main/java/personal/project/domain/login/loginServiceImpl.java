package personal.project.domain.login;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import personal.project.domain.member.Member;
import personal.project.domain.member.MemberRepository;

import java.util.Optional;

@Service
@Slf4j
public class loginServiceImpl implements loginService {

    private final MemberRepository memberRepository;

    @Autowired
    public loginServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public Member login(String loginId, String password) {
        Optional<Member> byLoginId = memberRepository.findByLoginId(loginId);

        return byLoginId.filter(m -> m.getPassword().equals(password)).orElse(null);
    }
}
