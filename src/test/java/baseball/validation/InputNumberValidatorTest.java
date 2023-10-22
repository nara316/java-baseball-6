package baseball.validation;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InputNumberValidatorTest {

    @Test
    void 전체검증_메서드() {
        String example = "316";
        assertDoesNotThrow(() -> InputNumberValidator.validateInputSize(example));
        assertDoesNotThrow(() -> InputNumberValidator.validateInputType(example));
        assertDoesNotThrow(() -> InputNumberValidator.validateInputDuplication(example));

        assertTrue(InputNumberValidator.convertStrToList(example) instanceof List<Integer>);
    }

    @Test
    void String을_List로_변환() {
        String example = "316";
        assertTrue(InputNumberValidator.convertStrToList(example) instanceof List<Integer>);
    }

    @Test
    void 숫자가_세자리가_아니면_예외발생() {
        assertThrows(IllegalArgumentException.class, () -> InputNumberValidator.validateInputSize("12"));
    }

    @Test
    void 숫자가_아니면_예외발생() {
        assertThrows(IllegalArgumentException.class, () -> InputNumberValidator.validateInputType("1과3"));
    }

    @Test
    void 숫자가_중복이면_예외발생() {
        assertThrows(IllegalArgumentException.class, () -> InputNumberValidator.validateInputDuplication("22"));
    }

}