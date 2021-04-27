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

// 구성영역
public class AppConfig {

    // 역할
    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }

    // 역할
    private MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    // 역할
    public OrderService orderService() {
        // 구현
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    // 역할
    public DiscountPolicy discountPolicy() {
        // 구현
//        return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }
}
