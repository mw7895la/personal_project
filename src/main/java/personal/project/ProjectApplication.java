package personal.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import personal.project.domain.member.MemberRepository;
import personal.project.domain.product.ProductRepository;

@SpringBootApplication
public class ProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectApplication.class, args);
	}

	@Bean
	@Profile("local")
	public TestDatainit testDatainit(MemberRepository memberRepository, ProductRepository productRepository) {
		return new TestDatainit(memberRepository, productRepository);
	}
}
