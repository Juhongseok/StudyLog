package reservation.domain.v2;

import generic.Money;

public class PercentDiscountPolicy extends DiscountPolicy {

    private double percent;

    @Override
    protected Money getDiscountAmount(Screening screening) {
        return screening.getFixedFee().times(percent);
    }

}
