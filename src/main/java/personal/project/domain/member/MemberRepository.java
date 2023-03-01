package personal.project.domain.member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {

    Member findById(Long id);

    void save(Member member);

    List<Member> findAll();

    Optional<Member> findByLoginId(Member member);
}
