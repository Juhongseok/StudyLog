package reservation.domain.v1;

import generic.Money;

import java.util.ArrayList;
import java.util.List;

public class DiscountPolicy {

    public enum PolicyType { PERCENT_POLICY, AMOUNT_POLICY }

    private Long id;
    private Long movieId;
    private PolicyType policyType;
    private Money amount;
    private Double percent;
    private List<DiscountCondition> conditions;

    public DiscountPolicy() {
    }

    public DiscountPolicy(Long movieId, PolicyType policyType, Money amount, Double percent) {
        this(null, movieId, policyType, amount, percent, new ArrayList<>());
    }

    public DiscountPolicy(Long id, Long movieId, PolicyType policyType, Money amount, Double percent, List<DiscountCondition> conditions) {
        this.id = id;
        this.movieId = movieId;
        this.policyType = policyType;
        this.amount = amount;
        this.percent = percent;
        this.conditions = conditions;
    }

    public boolean findDiscountCondition(Screening screening) {
        for (DiscountCondition condition : conditions) {
            if (condition.isSatisfied(screening)) {
                return true;
            }
        }

        return false;
    }

    public Money calculateDiscount(Movie movie) {
        if (isAmountPolicy()) {
            return amount;
        } else if (isPercentPolicy()) {
            return movie.getFee().times(percent);
        }

        return Money.ZERO;
    }

    public Long getId() {
        return id;
    }

    public Long getMovieId() {
        return movieId;
    }

    private boolean isAmountPolicy() {
        return PolicyType.AMOUNT_POLICY.equals(policyType);
    }

    private boolean isPercentPolicy() {
        return PolicyType.PERCENT_POLICY.equals(policyType);
    }

}
