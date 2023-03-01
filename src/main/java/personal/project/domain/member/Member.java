package personal.project.domain.member;

import lombok.Data;

@Data
public class Member {

    private Long id;            //DB에 저장될 번호?

    private String userId;      //실제 웹상에서 쓰는 아이디
    private String password;

    private String username;
    private String local;       //사는지역 + 주소
    private String ssn;         //주민 번호

    public Member(String userId, String password, String username, String local, String ssn) {
        this.userId = userId;
        this.password = password;
        this.username = username;
        this.local = local;
        this.ssn = ssn;
    }
}
