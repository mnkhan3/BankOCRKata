package ocr;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Character.getNumericValue;
import static java.text.MessageFormat.format;

public class OCRService {

    public static final int NUMBER_OF_LINE_ENTRIES = 4;
    public static final int NUMBER_OF_CHARACTERS_PER_LINE = 27;


    public void parseFileVerifyAccountNumber(String inputFilename, String outPutFileName) throws Exception {
        BufferedReader reader = new BufferedReader(new FileReader((inputFilename)));
        List<String> accountNumbers = new ArrayList<>();

        String line;
        while ((line = reader.readLine()) != null) {
            if (verifyAccountNumber(line)) {
                accountNumbers.add(line);
            } else {
                accountNumbers.add(line + " ERR");
            }
        }

        writeAccountNumbersToFile(accountNumbers, outPutFileName);
    }

    public boolean verifyAccountNumber(String accountNumber) {
        int digit;
        int positionMultipliuer = accountNumber.length();
        int sum = 0;

        for (int i = 0; i < accountNumber.length(); i++) {
            digit = getNumericValue(accountNumber.charAt(i));
            sum += digit * positionMultipliuer;
            positionMultipliuer--;
        }

        return sum % 11 == 0;
    }


    public void parseFile(String inputFilename, String outPutFileName) throws Exception {

        BufferedReader reader = new BufferedReader(new FileReader(inputFilename));
        String line;
        List<String> entryLines = new ArrayList<>();
        List<String> accountNumbers = new ArrayList<>();

        int lineNumber = 0;
        while ((line = reader.readLine()) != null) {
            int lineSize = line.length();

            if (lineSize != NUMBER_OF_CHARACTERS_PER_LINE) {
                throw new Exception(
                        format(
                                "line {0} is not formatted correctly length must be {1}, but current length is {2}",
                                lineNumber + 1,
                                NUMBER_OF_CHARACTERS_PER_LINE,
                                lineSize)
                );
            }
            entryLines.add(line);

            if (entryLines.size() == NUMBER_OF_LINE_ENTRIES) {
                String accountNumber = parseAccountNumber(entryLines);
                accountNumbers.add(accountNumber);
                entryLines.clear();
            }

            lineNumber++;
        }

        reader.close();

        writeAccountNumbersToFile(accountNumbers, outPutFileName);
    }

    private String parseAccountNumber(List<String> entryLines) {
        StringBuilder accountNumber = new StringBuilder();

        for (int i = 0; i < NUMBER_OF_CHARACTERS_PER_LINE; i += 3) {
            String[] digit = new String[3];

            digit[0] = entryLines.get(0).substring(i, i + 3);
            digit[1] = entryLines.get(1).substring(i, i + 3);
            digit[2] = entryLines.get(2).substring(i, i + 3);

            accountNumber.append(DigitPattern.convertDigitArrayToNumber(digit));
        }

        return accountNumber.toString();
    }

    private void writeAccountNumbersToFile(List<String> accountNumbers, String outPutFileName) {
        try {
            FileWriter writer = new FileWriter(outPutFileName);
//            System.out.println(System.getProperty("user.dir"));
//            System.out.println(outPutFileName);
            for (String accountNumber : accountNumbers) {
                writer.write(accountNumber + "\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}