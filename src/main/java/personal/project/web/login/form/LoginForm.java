package personal.project.web.login.form;


import lombok.Data;

import javax.validation.constraints.NotBlank;


@Data
public class LoginForm {

    @NotBlank(message = "아이디를 입력해주세요.")
    private String userId;

    @NotBlank(message = "비밀번호를 입력해주세요.")
    private String password;

}
