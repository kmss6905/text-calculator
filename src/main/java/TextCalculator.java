import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextCalculator {
    public static final String CUSTOM_SPLIT_REGEX = "//(.)\n(.*)";
    public static final String DEFAULT_SPLIT_REGEX = "[,:]";

    public int add(String text) {
        if (isBlank(text)) {
            return 0;
        }
        return sum2(toInts(split(text)));
    }

    // 구분하는 기능
    private String[] split(String text) {
        Matcher m = Pattern.compile(CUSTOM_SPLIT_REGEX).matcher(text);
        if (m.find()) {
            String customDelimeter = m.group(1);
            return m.group(2).split(customDelimeter);
        }
        return text.split(DEFAULT_SPLIT_REGEX);
    }

    // 구분된 문자열을 숫자로 바꾸는 기능
    private int[] toInts(String[] split) {
        int[] numbers = new int[split.length];
        for (int i = 0; i < split.length; i++) {
            numbers[i] = toPositive(split[i]); // 바꾸는 과정에서 체크하는 것이 낫다.
        }
        return numbers;
    }

    // 음수를 체크하는 기능
    private int toPositive(String split) {
        int number = Integer.parseInt(split);
        if (number < 0) {
            throw new RuntimeException("음수는 문자열 계산기에 들어갈 수 없습니다.");
        }
        return number;
    }

    // 바뀐 숫자를 더하는 기능
    private int sum2(int[] numbers) {
        boolean negativeNum = Arrays.stream(numbers).anyMatch(it -> it < 0);
        if (negativeNum) {
            throw new RuntimeException("음수는 문자열 계산기에 들어갈 수 없습니다.");
        }
        return Arrays.stream(numbers).sum();
    }

    // 하나의 메서드의 두가지 책임을 가지고 있다. (String -> int 변환, 합하는 부분)

    /**
     * (개선전)
     * private int sum(String[] split) {
     *         int sum = 0;
     *         for (String s : split) {
     *             if (!s.isBlank()) {
     *                 int parseInt = toPositive(s);
     *                 checkNative(parseInt);
     *                 sum += parseInt;
     *             }
     *         }
     *         return sum;
     *     }
     *
     */

    private boolean isBlank(String text) {
        return text == null || text.isEmpty();
    }
}
