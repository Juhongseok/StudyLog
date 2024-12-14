package reservation.domain.v2;

public interface DiscountCondition {

    boolean isSatisfiedBy(Screening screening);

}
