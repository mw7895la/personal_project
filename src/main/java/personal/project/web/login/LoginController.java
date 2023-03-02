package personal.project.web.login;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import personal.project.domain.login.loginService;
import personal.project.domain.member.Member;
import personal.project.web.SessionConst;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Slf4j
@Controller
public class LoginController {
    private final loginService loginService;

    @Autowired
    public LoginController(personal.project.domain.login.loginService loginService) {
        this.loginService = loginService;
    }

    @GetMapping("/login")
    public String loginForm(@ModelAttribute("loginForm") LoginForm form) {

        return "login/loginForm";
    }

    @PostMapping("/login")
    public String login(@Validated @ModelAttribute LoginForm form, BindingResult bindingResult, HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            return "login/loginForm";
        }

        Member loginMember = loginService.login(form.getLoginId(), form.getPassword());
        if (loginMember == null) {
            bindingResult.reject("loginFail", "아이디 또는 비밀번호가 맞지 않습니다.");
            return "login/loginForm";
        }

        HttpSession session = request.getSession();

        session.setAttribute(SessionConst.LOGIN_MEMBER, loginMember);

        return "redirect:/";

    }

    @PostMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if(session == null){
            session.invalidate();
        }

        return "redirect:/";
    }
}
