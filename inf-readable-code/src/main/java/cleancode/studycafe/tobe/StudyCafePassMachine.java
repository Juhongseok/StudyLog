package cleancode.studycafe.tobe;

import cleancode.studycafe.tobe.exception.AppException;
import cleancode.studycafe.tobe.io.InputHandler;
import cleancode.studycafe.tobe.io.OutputHandler;
import cleancode.studycafe.tobe.io.StudyCafeFileHandler;
import cleancode.studycafe.tobe.model.StudyCafeLockerPass;
import cleancode.studycafe.tobe.model.StudyCafePass;
import cleancode.studycafe.tobe.model.StudyCafePassType;

import java.util.List;

public class StudyCafePassMachine {

    private final InputHandler inputHandler = new InputHandler();
    private final OutputHandler outputHandler = new OutputHandler();
    private final StudyCafeFileHandler studyCafeFileHandler = new StudyCafeFileHandler();

    public void run() {
        try {
            outputHandler.showWelcomeMessage();
            outputHandler.showAnnouncement();

            outputHandler.askPassTypeSelection();
            StudyCafePassType studyCafePassType = inputHandler.getPassTypeSelectingUserAction();

            doAction(studyCafePassType);
        } catch (AppException e) {
            outputHandler.showSimpleMessage(e.getMessage());
        } catch (Exception e) {
            outputHandler.showSimpleMessage("알 수 없는 오류가 발생했습니다.");
        }
    }

    private void doAction(StudyCafePassType studyCafePassType) {
        List<StudyCafePass> studyCafePasses = studyCafeFileHandler.readStudyCafePasses();
        List<StudyCafePass> candidates = studyCafePasses.stream()
                .filter(studyCafePass -> studyCafePass.isEqualType(studyCafePassType))
                .toList();
        outputHandler.showPassListForSelection(candidates);
        StudyCafePass selectedPass = inputHandler.getSelectPass(candidates);

        StudyCafeLockerPass studyCafeLockerPass = readLockerPassesWithStudyCafePass(selectedPass);
        outputHandler.showPassOrderSummary(selectedPass, studyCafeLockerPass);
    }

    private StudyCafeLockerPass readLockerPassesWithStudyCafePass(StudyCafePass selectedPass) {
        if (selectedPass.isNotEqualType(StudyCafePassType.FIXED)) {
            return null;
        }

        StudyCafeLockerPass studyCafeLockerPass = studyCafeFileHandler.readLockerPasses().stream()
                .filter(selectedPass::isEqual)
                .findFirst()
                .orElse(null);

        if (studyCafeLockerPass != null) {
            outputHandler.askLockerPass(studyCafeLockerPass);
            boolean lockerSelection = inputHandler.getLockerSelection();

            if (lockerSelection) {
                return studyCafeLockerPass;
            }
        }

        return null;
    }

}
