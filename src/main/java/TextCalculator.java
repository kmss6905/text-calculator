
public class TextCalculator {
    private final String regex = "\\/\\/.*\\n|,|:";

    public int parse(String arg) {
        Integer x = returnZeroWhenNullOrEmpty(arg);
        if (x != null) return x;
        String[] split = arg.split(regex);
        int sum = 0;
        for (String s : split) {
            if (!s.isBlank()) {
                int parseInt = Integer.parseInt(s);
                checkNative(parseInt);
                sum += parseInt;
            }
        }
        return sum;
    }

    private Integer returnZeroWhenNullOrEmpty(String arg) {
        if (arg == null) {
            return 0;
        }
        if (arg.isEmpty()) {
            return 0;
        }
        return null;
    }

    public void checkNative(int arg) {
        if (arg < 0) {
            throw new RuntimeException("음수는 문자열 계산기에 들어갈 수 없습니다.");
        }
    }
}
