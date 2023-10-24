package baseball.util;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/*
 * 플레이어와 컴퓨터의 숫자를 비교하여 스트라이크, 볼로 변환하는 클래스
 * */
public class MessageConverter {

    private static final String STRIKE = "스트라이크";
    private static final String BALL = "볼";
    private static final String NOTHING = "낫싱";
    private static final int NUMBER_SIZE = 3;
    private static final int DEFAULT_NUMBER = 0;

    private Map<String, Integer> resultMessageMap;

    /*
     * 플레이어와 컴퓨터의 숫자를 비교하는 전체 메서드
     * @Param userInput 플레이어의 숫자
     * @Param computerNumber 컴퓨터의 숫자
     * */
    public Map<String, Integer> getResultMessage(List<Integer> userInput, List<Integer> computerNumber) {
        initializeResultMessageMap();

        for (int index = 0; index < NUMBER_SIZE; index++) {
            getStrikeOrBall(userInput, computerNumber, index);
        }

        return resultMessageMap;
    }

    /*
     * ConcurrentHashMap 초기화 메서드
     * */
    private void initializeResultMessageMap() {
        resultMessageMap = new ConcurrentHashMap<>() {
            {
                put(STRIKE, DEFAULT_NUMBER);
                put(BALL, DEFAULT_NUMBER);
            }
        };
    }

    /*
     * 플레이어와 컴퓨터의 숫자를 비교하는 메서드
     * @Param userInput 플레이어의 숫자
     * @Param computerNumber 컴퓨터의 숫자
     * @Param index 리스트의 인덱스
     * */
    private void getStrikeOrBall(List<Integer> userInput, List<Integer> computerNumber, Integer index) {
        if (isStrike(userInput, computerNumber, index)) {
            resultMessageMap.put(STRIKE, resultMessageMap.get(STRIKE) + 1);
        }

        if (isBall(userInput, computerNumber, index)) {
            resultMessageMap.put(BALL, resultMessageMap.get(BALL) + 1);
        }
    }

    private static boolean isBall(List<Integer> userInput, List<Integer> computerNumber, Integer index) {
        return userInput.contains(computerNumber.get(index)) && !userInput.get(index).equals(computerNumber.get(index));
    }

    private static boolean isStrike(List<Integer> userInput, List<Integer> computerNumber, Integer index) {
        return userInput.get(index).equals(computerNumber.get(index));
    }

    /*
     * 스트라이크 볼 개수를 map에서 String으로 변환하는 메서드
     * @Param resultMessageMap 플레이어와 컴퓨터의 숫자를 비교한 map 결과값
     * */
    public String getResultMessageToString(Map<String, Integer> resultMessageMap) {
        Integer ballInt = resultMessageMap.get(BALL);
        Integer strikeInt = resultMessageMap.get(STRIKE);
        StringBuilder resultMessageBuilder = new StringBuilder();

        if (ballInt == DEFAULT_NUMBER && strikeInt == DEFAULT_NUMBER) {
            return NOTHING;
        }

        if (ballInt > DEFAULT_NUMBER) {
            resultMessageBuilder.append(ballInt).append(BALL).append(" ");
        }

        if (strikeInt > DEFAULT_NUMBER) {
            resultMessageBuilder.append(strikeInt).append(STRIKE);
        }

        return resultMessageBuilder.toString().trim();
    }
}
