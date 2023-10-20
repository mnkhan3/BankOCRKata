package ocr;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static ocr.OCRService.NumberStatus.*;
import static org.junit.jupiter.api.Assertions.*;

class OCRServiceTest {

    private OCRService ocrService;
    private final String resourcePath = "/Users/mohammedkhan/workspace/TestDouble/TestDouble/BankOCRKata/src/test/resources/";

    @BeforeEach
    void setUp() {
        ocrService = new OCRService();
    }

    @Nested
    public class UseCase1 {

        @Test
        void IfFileIsNotFormattedThrowsIOException() {
            try {
                ocrService.parseFile(
                        resourcePath + "case1/useCaseInvalidInput.txt",
                        "none");
            } catch (Exception e) {
                assertEquals(
                        "line 1 is not formatted correctly length must be 27, but current length is 25",
                        e.getMessage());
            }
        }

        @Test
        void givenAValidInputParsesAccountNumbers() throws Exception {

            String inputFilename = resourcePath + "case1/useCase1.txt";
            String outPutFileName = resourcePath + "case1/useCase1ActualOutput.txt";
            String expectedOutPutFile = resourcePath + "case1/useCase1ExpectedOutput.txt";
            
            ocrService.parseFile(inputFilename, outPutFileName);

            assertFilesMatch(expectedOutPutFile, outPutFileName);
        }
    }

    @Nested
    public class UseCase2 {
        @Test
        void givenAValidInputParsedAccountNumbersToFileAndIfTheyAreValidAccNum() throws Exception {

            String inputFilename = resourcePath + "case2/useCase2Input.txt";
            String outPutFileName = resourcePath + "case2/useCase2ActualOutput.txt";
            String expectedOutPutFile = resourcePath + "case2/useCase2ExpectedOutput.txt";
            
            ocrService.parseFileVerifyAccountNumber(inputFilename, outPutFileName);

            assertFilesMatch(expectedOutPutFile, outPutFileName);
        }

        @Test
        void givenAInvalidNumberVerifyAccountNumberShouldReturnStatusERR() {
            assertEquals(ERR, (ocrService.verifyAccountNumber("664371495")));
        }
        
        @Test
        void givenAValidNumberVerifyAccountNumberShouldReturnSuccess() {
            assertEquals(SUCCESS, (ocrService.verifyAccountNumber("457508000")));
        }
    }

    @Nested
    public class UseCase3 {
        @Test
        void givenAValidFileWithAccountNumbersShouldCreateFileWithStatusCode() throws Exception {

            String inputFilename = resourcePath + "case3/useCase3Input.txt";
            String outPutFileName = resourcePath + "case3/useCase3ActualOutput.txt";
            String expectedOutPutFile = resourcePath + "case3/useCase3ExpectedOutput.txt";

            ocrService.parseFileVerifyAccountNumber(inputFilename, outPutFileName);

            assertFilesMatch(expectedOutPutFile, outPutFileName);
        }
        
        @Test
        void givenAInValidNumberWithQuestionsVerifyAccountNumberShouldReturnStatusILL() {
            assertEquals(ILL, (ocrService.verifyAccountNumber("45750??00")));
        }
    }
    
    private void assertFilesMatch(String expectedFilePath, String actualFilePath) throws IOException {
        try (BufferedReader expectedReader = new BufferedReader(new FileReader(expectedFilePath));
             BufferedReader actualReader = new BufferedReader(new FileReader(actualFilePath))) {
            String expectedLine;
            String actualLine;
            while ((expectedLine = expectedReader.readLine()) != null && (actualLine = actualReader.readLine()) != null) {
                assertEquals(expectedLine, actualLine);
            }
        }
    }
}