package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// 구성영역
@Configuration
public class AppConfig {

    // 역할
    @Bean
    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }

    // 역할
    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    // 역할
    @Bean
    public OrderService orderService() {
        // 구현
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    // 역할
    @Bean
    public DiscountPolicy discountPolicy() {
        // 구현
//        return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }
}
