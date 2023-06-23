package PatternMatcher;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Password {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String stringWithNumbers = scanner.nextLine();

        Pattern pattern = Pattern.compile("(password:\\s*[a-zA-Z0-9]*)|(password\\s+[a-zA-Z0-9]*)", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(stringWithNumbers);
        boolean isFound = false;
        while (matcher.find()) {
            String password = matcher.group();
            if (password.contains(" ")) {
                System.out.println(matcher.group().split("\\s+")[1]);
            } else {
                System.out.println(matcher.group().split(":")[1]);

            }
                isFound = true;
        }
        if (!isFound) {
            System.out.println("No passwords found");
        }
    }
}
