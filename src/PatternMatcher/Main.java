package PatternMatcher;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void IsMatcher(String part, String line) {
        Pattern pattern = Pattern.compile("(?<!^)(?<!\\s)" + part + "(?!$)(?!\\s)", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(line);
        if (matcher.find()) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String part = scanner.nextLine();
        String line = scanner.nextLine();
        IsMatcher(part, line);
    }
}