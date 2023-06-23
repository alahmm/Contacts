package PatternMatcher;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int size = Integer.parseInt(scanner.nextLine());
        String line = scanner.nextLine();
        String formatOfSize = String.format("\\b[a-zA-Z]{%d}\\b", size);
        Pattern pattern = Pattern.compile(formatOfSize);
        Matcher matcher = pattern.matcher(line);
        if (matcher.find()) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }
}
