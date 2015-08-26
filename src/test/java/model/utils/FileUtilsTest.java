package model.utils;

import org.junit.Test;

import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

public class FileUtilsTest {


    @Test
    public void testReadDataFile_Data() throws Exception {

        List<double[]> expectedDouble = new LinkedList<double[]>();
        List<double[]> actualDoubles;
        /*Random numbers for testing*/
        expectedDouble.add(new double[]{0.401521757, 0.084166757, 0.484383832, 0.835657467, 0.881536118, 0.006134942, 0.630740058, 0.748387294, 0.61416046});
        expectedDouble.add(new double[]{0.595068622, 0.792708763, 0.153636902, 0.239746913, 0.879095989, 0.344478638, 0.284134986, 0.662909417, 0.576648101});
        expectedDouble.add(new double[]{0.879373729, 0.839653047, 0.316575718, 0.349263069, 0.646078831, 0.411409065, 0.286298382, 0.414009314, 0.834499554});
        expectedDouble.add(new double[]{0.591128629, 0.137947714, 0.330859186, 0.552267334, 0.633742535, 0.91460738, 0.014216494, 0.049194592, 0.144463628});
        expectedDouble.add(new double[]{0.529551931, 0.60261757, 0.00076855, 0.402035611, 0.153216253, 0.387539596, 0.174313877, 0.627368623, 0.046919931});

        FileUtils fileUtils = new FileUtils();
        actualDoubles = fileUtils.readDataFile("src/test/resources/TestData.txt");
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


    @Test
    public void testReadDataFile_Coeff() throws Exception {

        List<double[]> expectedDouble = new LinkedList<double[]>();
        List<double[]> actualDoubles;
        /*Random numbers for testing*/
        expectedDouble.add(new double[]{16.2});
        expectedDouble.add(new double[]{16.0});
        expectedDouble.add(new double[]{16.1});
        expectedDouble.add(new double[]{16.55});
        expectedDouble.add(new double[]{16.3});

        FileUtils fileUtils = new FileUtils();
        actualDoubles = fileUtils.readDataFile("src/test/resources/TestCoefficients.txt");
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


    @Test
    public void testWriteDataFile() throws Exception {
        String dataLine;
        FileInputStream expectedFile =  new FileInputStream("src/test/resources/TestResult.txt");
        BufferedReader expectedReader = new BufferedReader(new InputStreamReader(expectedFile));
        StringBuilder expected = new StringBuilder();
        LinkedList<double[]> testData = new LinkedList<double[]>();
        FileUtils fileUtils = new FileUtils();

        while ((dataLine = expectedReader.readLine()) != null) {
            expected.append(dataLine);
        }

        testData.add(new double[]{18.9129883704});
        testData.add(new double[]{15.8694175281});
        testData.add(new double[]{12.8929423261});
        testData.add(new double[]{19.0414990997});
        testData.add(new double[]{13.4546027945});
        fileUtils.writeDataFile(testData, "src/test/resources/tempResult.txt");

        FileInputStream actualFile =  new FileInputStream("src/test/resources/tempResult.txt");
        BufferedReader actualReader = new BufferedReader(new InputStreamReader(actualFile));
        StringBuilder actual = new StringBuilder();

        while ((dataLine = actualReader.readLine()) != null) {
            actual.append(dataLine);
        }
        assertEquals(expected.toString(), actual.toString());
    }
}