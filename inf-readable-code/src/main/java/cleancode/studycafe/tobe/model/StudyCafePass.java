package cleancode.studycafe.tobe.model;

public class StudyCafePass {

    private final StudyCafePassType passType;
    private final int duration;
    private final int price;
    private final double discountRate;

    private StudyCafePass(StudyCafePassType passType, int duration, int price, double discountRate) {
        this.passType = passType;
        this.duration = duration;
        this.price = price;
        this.discountRate = discountRate;
    }

    public static StudyCafePass of(StudyCafePassType passType, int duration, int price, double discountRate) {
        return new StudyCafePass(passType, duration, price, discountRate);
    }

    public boolean isEqualType(StudyCafePassType type) {
        return this.passType == type;
    }

    public boolean isNotEqualType(StudyCafePassType type) {
        return this.passType != type;
    }

    public boolean isEqual(StudyCafeLockerPass option) {
        return option.getPassType() == passType && option.getDuration() == duration;
    }

    public int getPrice() {
        return price;
    }

    public double getDiscountRate() {
        return discountRate;
    }

    public String display() {
        return passType.display(duration, price);
    }

}
