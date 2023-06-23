package PatternMatcher;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Key {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String stringWithNumbers = scanner.nextLine();
        Pattern pattern = Pattern.compile("the\\s+key\\s+is\\s+([^aeoui?!#\\s]+|[aeoui?!#]+)\\s|$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(stringWithNumbers);
        boolean isFound = false;
        while (matcher.find()) {
            String key = matcher.group();
            String[] listOfKey = key.split("\\s+");
            System.out.println(listOfKey[listOfKey.length - 1]);
            isFound = true;
        }
        if (!isFound) {
            System.out.println("No keys found");
        }
    }
}
