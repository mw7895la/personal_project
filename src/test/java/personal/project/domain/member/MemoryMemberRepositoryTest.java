package personal.project.domain.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MemoryMemberRepositoryTest {

    /**
     *     private String userId;      //실제 웹상에서 쓰는 아이디
     *     private String password;
     *
     *     private String username;
     *     private String local;       //사는지역 + 주소
     *     private String ssn;         //주민 번호
     */
    private MemoryMemberRepository memberRepository = new MemoryMemberRepository();

    @BeforeEach
    void saveMember(){
        Member member_A = new Member("mw7895la", "kimwoo12", "김우진", "서울시 광진구 구의동", "900306-1020017");
        Member member_B = new Member("kwj0306", "kimwoo12", "신동훈", "서울시 광진구 구의동", "901006-1020015");
        memberRepository.save(member_A);
        memberRepository.save(member_B);
    }

    @AfterEach
    void deleteMember(){
        memberRepository.clear();
    }

    @Test
    void findById() {
        Member findMember = memberRepository.findById(1L);
        assertThat(findMember.getUserId()).isEqualTo("mw7895la");


    }
    @Test
    void save() {
        Member member_C = new Member("kwj0306", "kimwoo12", "신동훈", "서울시 광진구 구의동", "901006-1020015");
        memberRepository.save(member_C);
        assertThat(member_C.getId()).isEqualTo(3L);
    }

    @Test
    void findByLoginId() {
    }

    @Test
    void findAll() {
    }
}