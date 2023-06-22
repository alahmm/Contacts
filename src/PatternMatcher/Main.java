package PatternMatcher;

import java.util.regex.Pattern;

public class Main {
    public static void IsMatcher(String part, String line) {
        Pattern pattern = Pattern.compile("." + part + ".", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(line);

        System.out.println(matcher.matches()); // true
    }
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String part = scanner.nextLine();
        String line = scanner.nextLine();
        IsMatcher(part, line);
    }
}