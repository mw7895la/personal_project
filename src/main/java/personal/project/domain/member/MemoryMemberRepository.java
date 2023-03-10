package personal.project.domain.member;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class MemoryMemberRepository implements MemberRepository {

    private static Map<Long, Member> memberStore = new ConcurrentHashMap<>();
    private static long sequence=0L;

    @Override
    public Member findById(Long id) {
        Member findMember = memberStore.get(id);
        return findMember;
    }

    @Override
    public void save(Member member) {
        member.setId(++sequence);
        memberStore.put(member.getId(), member);


    }

    //아직 구현 X
    @Override
    public Optional<Member> findByLoginId(String loginId) {
        List<Member> members = findAll();

        for (Member member : members) {
            if(member.getUserId().equals(loginId)){
                return Optional.of(member);     //null이 아닌 객체를 담고있는 Optional 객체를 생성하여 리턴.
            }
        }
        return Optional.empty();                //비어있는 Optional 객체 생성하여 리턴.
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<Member>(memberStore.values());
    }

    public void clear(){
        memberStore.clear();
    }
}
