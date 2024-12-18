package reservation.domain.v2;

import generic.Money;

import java.util.Arrays;
import java.util.List;

public class OverlappedDiscountPolicy extends DiscountPolicy {
    private List<DiscountPolicy> policies;

    public OverlappedDiscountPolicy(DiscountPolicy... policies) {
        super(screening -> true);
        this.policies = Arrays.asList(policies);
    }

    @Override
    protected Money getDiscountAmount(Screening screening) {
        Money result = Money.ZERO;

        for (DiscountPolicy each : policies) {
            Money discountAmount = each.getDiscountAmount(screening);
            result = result.plus(discountAmount);
        }

        return result;
    }

}
