package personal.project.domain.login;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import personal.project.domain.member.Member;
import personal.project.domain.member.MemberRepository;


public interface loginService {
    Member login(String loginId, String password);
}
