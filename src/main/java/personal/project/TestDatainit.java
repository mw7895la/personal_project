package personal.project;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import personal.project.domain.member.Member;
import personal.project.domain.member.MemberRepository;
import personal.project.domain.product.Product;
import personal.project.domain.product.ProductRepository;


@Profile("local")
public class TestDatainit {

    private final MemberRepository memberRepository;
    private final ProductRepository productRepository;

    public TestDatainit(MemberRepository memberRepository, ProductRepository productRepository) {
        this.memberRepository = memberRepository;
        this.productRepository = productRepository;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void initData() {
        Member member = new Member("qwe", "123", "우진", "서울시 광진구 구의동", "900306-1020017");
        memberRepository.save(member);

        productRepository.addProduct(new Product("iPad", 15000, 20, "Apple Korea"));
        productRepository.addProduct(new Product("M2 Max", 15000, 20, "Apple Korea"));
    }
}
