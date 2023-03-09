package personal.project.web.member;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import personal.project.domain.member.Member;
import personal.project.domain.member.MemberRepository;

import java.util.Optional;

@Slf4j
@Controller
@RequestMapping("/members")
public class MemberController {

    private final MemberRepository memberRepository;

    @Autowired
    public MemberController(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @GetMapping("/add")
    public String joinForm(@ModelAttribute Member member) {
        return "members/addMemberForm";
    }

    @PostMapping("/add")
    public String join(@ModelAttribute Member member, BindingResult bindingResult) {

        String userId = member.getUserId();

        Optional<Member> byUserId = memberRepository.findByLoginId(userId);

        String findUserId = byUserId.get().getUserId();

        log.info("{}", findUserId);

        if (userId.equals(findUserId)) {
            bindingResult.rejectValue("userId", "duplication", null);
            return "members/addMemberForm";
        }

        memberRepository.save(member);

        return "redirect:/";
    }
}
