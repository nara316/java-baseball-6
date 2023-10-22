package baseball.validation;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/*
* 플레이어가 입력한 숫자를 검증하는 클래스
* */
public class InputNumberValidator {

    private static final int NUMBER_SIZE = 3;
    private static final String REGEX = "[0-9]+";

    /*
    * 플레이어의 입력값(String)을 List로 변환하는 메서드
    * @Param input 플레이어의 숫자
    * */
    public static List<Integer> convertStrToList(String input) {
        return Arrays.stream(input.split(""))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    /*
     * 플레이어의 입력값이 3자리 수인지 확인
     * @Param input 플레이어의 숫자
     * */
    public static void validateInputSize(String input) {
        if (input.length() != NUMBER_SIZE) {
            throw new IllegalArgumentException();
        }
    }

    /*
     * 플레이어의 입력값이 숫자인지 확인
     * @Param input 플레이어의 숫자
     * */
    public static void validateInputType(String input) {
        if (!input.matches(REGEX)) {
            throw new IllegalArgumentException();
        }
    }

    /*
     * 플레이어의 입력값이 중복인지 확인
     * @Param input 플레이어의 숫자
     * */
    public static void validateInputDuplication(String input) {
        Set<Character> duplicateCheckSet = new HashSet<>();
        for (char c : input.toCharArray()) {
            duplicateCheckSet.add(c);
        }

        if (duplicateCheckSet.size() != NUMBER_SIZE) {
            throw new IllegalArgumentException();
        }
    }
}
