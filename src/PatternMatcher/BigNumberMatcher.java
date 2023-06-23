package PatternMatcher;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BigNumberMatcher {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String stringWithNumbers = scanner.nextLine();

        Pattern pattern = Pattern.compile("\\d{10,}");
        Matcher matcher = pattern.matcher(stringWithNumbers);
        while (matcher.find()) {
            int size = matcher.end() - matcher.start();
            System.out.println(matcher.group() + ":" + size);
        }
    }
}
