package cleancode.studycafe.tobe.io;

public enum LockerUseSelection {

    USE("사용", 1),
    NOT_USE("미 사용", 2);

    private final String description;
    private final int menuNumber;

    LockerUseSelection(String description, int menuNumber) {
        this.description = description;
        this.menuNumber = menuNumber;
    }

    public boolean isEqual(String userInput) {
        return this.menuNumber == Integer.parseInt(userInput);
    }
}
