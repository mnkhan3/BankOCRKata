package ocr;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class DigitPattern {
    final static Map<String, String> patternToNumber = new HashMap<>();

    static {
        patternToNumber.put(Arrays.toString(new String[]{
                " _ ",
                "| |",
                "|_|"
        }), "0");
        patternToNumber.put(Arrays.toString(new String[]{
                "   ",
                "  |",
                "  |"
        }), "1");
        patternToNumber.put(Arrays.toString(new String[]{
                " _ ",
                " _|",
                "|_ "
        }), "2");
        patternToNumber.put(Arrays.toString(new String[]{
                " _ ",
                " _|",
                " _|"
        }), "3");
        patternToNumber.put(Arrays.toString(new String[]{
                "   ",
                "|_|",
                "  |"
        }), "4");
        patternToNumber.put(Arrays.toString(new String[]{
                " _ ",
                "|_ ",
                " _|"
        }), "5");
        patternToNumber.put(Arrays.toString(new String[]{
                " _ ",
                "|_ ",
                "|_|"
        }), "6");
        patternToNumber.put(Arrays.toString(new String[]{
                " _ ",
                "  |",
                "  |"
        }), "7");
        patternToNumber.put(Arrays.toString(new String[]{
                " _ ",
                "|_|",
                "|_|"
        }), "8");
        patternToNumber.put(Arrays.toString(new String[]{
                " _ ",
                "|_|",
                " _|"
        }), "9");
    }

    public static String convertDigitArrayToNumber(String[] digit) {
        String pattern = Arrays.toString(digit);
        return patternToNumber.getOrDefault(pattern, "?");
    }
}