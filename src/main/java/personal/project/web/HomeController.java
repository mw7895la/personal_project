package personal.project.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import personal.project.domain.member.Member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static personal.project.web.SessionConst.LOGIN_MEMBER;

@Slf4j
@Controller
public class HomeController {

    @GetMapping("/")
    public String home(HttpServletRequest request, Model model){

        HttpSession session = request.getSession(false);

        if(session == null){
            return "home";
        }

        Member loginMember = (Member)session.getAttribute(LOGIN_MEMBER);
        log.info("member ={} ", loginMember.toString());
        if(loginMember == null){
            return "home";
        }
        model.addAttribute("member", loginMember);

        return "loginHome";

    }
}
