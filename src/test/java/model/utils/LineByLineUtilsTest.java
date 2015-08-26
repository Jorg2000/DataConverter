package model.utils;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;


public class LineByLineUtilsTest {

    @Test
    public void testConvertLineByLine() throws Exception {

        String dataURI = "src/test/resources/TestData.txt";
        String coefURI = "src/test/resources/TestCoefficients.txt";
        String expectedResultURI = "src/test/resources/TestResult.txt";
        String actualResultURI = "src/test/resources/realResult.txt";

        List<double[]> actualDoubles;
        List<double[]> expectedDouble;

        LineByLineUtils lineByLineUtils = new LineByLineUtils();
        lineByLineUtils.convertLineByLine(dataURI,coefURI,actualResultURI,"\t",',',new Math());

        FileUtils fileUtils = new FileUtils();
        actualDoubles = fileUtils.readDataFile(actualResultURI);
        expectedDouble = fileUtils.readDataFile(expectedResultURI);
        StringBuilder expected = new StringBuilder();
        StringBuilder actual = new StringBuilder();

        for (double[] doubles : expectedDouble) {
            expected.append(Arrays.toString(doubles));
        }

        for (double[] doubles : actualDoubles) {
            actual.append(Arrays.toString(doubles));
        }
        assertEquals(expected.toString(), actual.toString());
    }
}