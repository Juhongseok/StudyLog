package reservation.domain.v2;

import generic.Money;

public class Movie {
    private Money fee;
    private DiscountPolicy discountPolicy;

    public Money calculateFee(Screening screening) {
        Money discountAmount = discountPolicy.calculateDiscount(screening);

        return fee.minus(discountAmount);
    }

    public Money getFee() {
        return fee;
    }
}
