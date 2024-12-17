package reservation.domain.v2;

import generic.Money;

public class NoneDiscountPolicy extends DiscountPolicy {

    /**
     * 할인정책을 가지지 않은 영화를 나타내기 위해 null값이 아닌 특정 객체를 전달하도록 사용
     * 일관된 구조를 가져갈 수 있도록
     */

    @Override
    protected Money getDiscountAmount(Screening screening) {
        return Money.ZERO;
    }

}
