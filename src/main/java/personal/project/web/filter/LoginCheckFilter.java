package personal.project.web.filter;


import lombok.extern.slf4j.Slf4j;
import org.springframework.util.PatternMatchUtils;
import personal.project.web.SessionConst;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@Slf4j
public class LoginCheckFilter implements Filter {

    private static final String[] exceptFilter = {"/", "/members/add", "/login", "/logout", "/css/*"};

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String requestURI = httpRequest.getRequestURI();

        HttpServletResponse httpResponse = (HttpServletResponse) response;

        try{


            if (isLoginCheckPath(requestURI)) {

                //true면 필터를 거쳐야 되는 URI이라는 뜻.
                HttpSession session = httpRequest.getSession(false);
                if (session == null || session.getAttribute(SessionConst.LOGIN_MEMBER) == null) {
                    httpResponse.sendRedirect("/login?redirectURL=" + requestURI);
                    //미인증 사용자면 처음에 login 페이지로 보낸다.

                    return;
                }
            }
            chain.doFilter(request, response);
        }catch(Exception e){
            throw e;
        }
    }

    private boolean isLoginCheckPath(String requestURI) {
        return !PatternMatchUtils.simpleMatch(exceptFilter, requestURI);
    }

}
