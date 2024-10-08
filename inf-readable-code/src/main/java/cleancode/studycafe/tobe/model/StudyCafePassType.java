package cleancode.studycafe.tobe.model;

import cleancode.studycafe.tobe.exception.AppException;

import java.util.Arrays;

public enum StudyCafePassType {

    HOURLY(1, "시간 단위 이용권", "%s시간권 - %d원"),
    WEEKLY(2, "주 단위 이용권", "%s주권 - %d원"),
    FIXED(3, "1인 고정석", "%s주권 - %d원");

    private int typeNumber;
    private final String description;
    private final String displayFormat;

    StudyCafePassType(int typeNumber, String description, String displayFormat) {
        this.typeNumber = typeNumber;
        this.description = description;
        this.displayFormat = displayFormat;
    }

    public static StudyCafePassType parse(String typeNumber) {
        int inputTypeNumber = Integer.parseInt(typeNumber);
        return Arrays.stream(values())
                .filter(studyCafePassType -> studyCafePassType.typeNumber == inputTypeNumber)
                .findFirst()
                .orElseThrow(() -> new AppException("잘못된 입력입니다."));
    }

    public String display(int duration, int price) {
        return this.displayFormat.formatted(duration, price);
    }
}
