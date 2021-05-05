package hello.core.discount;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
//@Primary
//@Qualifier("mainDiscountPolicy")  // 문자열이 오류가 날 경우에도 컴파일 에러를 잡을 수 없음
@MainDiscountPolicy
public class RateDiscountPolicy implements DiscountPolicy {

    private int discountPercent = 10;

    @Override
    public int discount(Member member, int price) {

        if(member.getGrade() == Grade.VIP) {
            return price / 100 * discountPercent;
        } else {
            return 0;
        }

    }
}
