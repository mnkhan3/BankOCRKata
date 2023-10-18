package ocr;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OCRParserTest {

    private OCRService ocrParser;
    private final String resourcePath = "/Users/mohammedkhan/workspace/TestDoubleOCR/src/test/resources/";

    @BeforeEach
    void setUp() {
        ocrParser = new OCRService();
    }

    @Nested
    public class UseCase1 {

        @Test
        void IfFileIsNotFormattedThrowsIOException() {
            try {
                ocrParser.parseFile(
                        resourcePath + "case1/useCaseInvalidInput.txt",
                        "none");
            } catch (Exception e) {
                assertEquals(
                        "line 0 is not formatted correctly length must be 27, but current length is 25",
                        e.getMessage());
            }
        }

        @Test
        void givenAValidInputParsesAccountNumbers() throws Exception {

            String inputFilename = resourcePath + "case1/useCase1.txt";
            String outPutFileName = resourcePath + "case1/useCase1ActualOutput.txt";
            String expectedOutPutFile = resourcePath + "case1/useCase1ExpectedOutput.txt";
            
            ocrParser.parseFile(inputFilename, outPutFileName);

            assertFilesMatch(expectedOutPutFile, outPutFileName);
        }
    }

    @Nested
    public class UseCase2 {

        //Failing Tests
        @Test
        void givenAValidInputWriteParsedAccountNumbersToFileAndIfTheyAreValidAccNum() throws Exception {

            String inputFilename = resourcePath + "case2/useCase2Input.txt";
            String outPutFileName = resourcePath + "case2/useCase2ActualOutput.txt";
            String expectedOutPutFile = resourcePath + "case2/useCase2ExpectedOutput.txt";
            ocrParser.parseFile(
                    inputFilename,
                    outPutFileName
            );

            assertFilesMatch(expectedOutPutFile, outPutFileName);
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