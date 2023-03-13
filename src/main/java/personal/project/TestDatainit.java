package personal.project;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import personal.project.domain.board.Board;
import personal.project.domain.board.BoardRepository;
import personal.project.domain.member.Member;
import personal.project.domain.member.MemberRepository;
import personal.project.domain.product.Product;
import personal.project.domain.product.ProductRepository;

import java.util.Date;


@Profile("local")
public class TestDatainit {

    private final MemberRepository memberRepository;
    private final ProductRepository productRepository;
    private final BoardRepository boardRepository;

    public TestDatainit(MemberRepository memberRepository, ProductRepository productRepository,BoardRepository boardRepository) {
        this.memberRepository = memberRepository;
        this.productRepository = productRepository;
        this.boardRepository = boardRepository;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void initData() {
        Member member = new Member("qwe", "123", "우진", "서울시 광진구 구의동", "900306-1020017");
        memberRepository.save(member);

        productRepository.addProduct(new Product("iPad", 15000, 20, "Apple Korea"));
        productRepository.addProduct(new Product("M2 Max", 15000, 20, "Apple Korea"));

        Board board = new Board("첫글", "안녕하세요", member.getUserId(),new Date().toString());
        boardRepository.register(board);
    }
}
