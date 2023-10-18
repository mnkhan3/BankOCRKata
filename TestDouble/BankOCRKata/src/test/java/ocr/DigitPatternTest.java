package ocr;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static ocr.DigitPattern.convertDigitArrayToNumber;
import static org.junit.jupiter.api.Assertions.assertEquals;

class DigitPatternTest {

    @Test
    public void convertsPatternDigitToNumber() {
        Map<String[], String> digitToString = new HashMap<>();
        
        digitToString.put(new String[]{
                " _ " ,
                "| |" ,
                "|_|"}, "0");
        digitToString.put(new String[]{
                "   " ,
                "  |" ,
                "  |"}, "1");
        digitToString.put(new String[]{
                " _ " ,
                " _|" ,
                "|_ "}, "2");
        digitToString.put(new String[]{
                " _ " ,
                " _|" ,
                " _|"}, "3");
        digitToString.put(new String[]{
                "   " ,
                "|_|" ,
                "  |"}, "4");
        digitToString.put(new String[]{
                " _ " ,
                "|_ " ,
                " _|"}, "5");
        digitToString.put(new String[]{
                " _ " ,
                "|_ " ,
                "|_|"}, "6");
        digitToString.put(new String[]{
                " _ " ,
                "  |" ,
                "  |"}, "7");
        digitToString.put(new String[]{
                " _ " ,
                "|_|" ,
                "|_|"}, "8");
        digitToString.put(new String[]{
                " _ " ,
                "|_|" ,
                " _|"}, "9");
        digitToString.put(new String[]{
                " _ " ,
                "| |" ,
                " _|"}, "?");
        digitToString.put(new String[]{
                "  " ,
                "  " ,
                "  "}, "?");

        digitToString.forEach((pattern, expectedDigit) -> 
                assertEquals(expectedDigit, convertDigitArrayToNumber(pattern)));
        
    }
}