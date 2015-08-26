package model.utils;

import org.junit.Test;


import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

public class MathTest {

    @Test
    public void testVoltageToAcceleration() throws Exception {

        
        FileUtils fileUtils = new FileUtils();
        model.utils.Math math = new Math();
        List<double[]> expectedDouble = new LinkedList<double[]>();
        List<double[]> actualDouble;
        DecimalFormat df = new DecimalFormat("0.0000000000");
        DecimalFormatSymbols decimalFormatSymbols = new DecimalFormatSymbols();
        decimalFormatSymbols.setDecimalSeparator('.');
        df.setRoundingMode(RoundingMode.HALF_UP);
        df.setDecimalFormatSymbols(decimalFormatSymbols);

        /*Test results obtained in LabView*/
        expectedDouble.add(new double[]{18.9129883704});
        expectedDouble.add(new double[]{15.8694175281});
        expectedDouble.add(new double[]{12.8929423261});
        expectedDouble.add(new double[]{19.0414990997});
        expectedDouble.add(new double[]{13.4546027945});



        List<double[]> data = fileUtils.readDataFile("src/test/resources/TestData.txt");
        List<double[]> coeff = fileUtils.readDataFile("src/test/resources/TestCoefficients.txt");
        actualDouble = math.voltageToAcceleration(data, coeff);

        StringBuilder expected = new StringBuilder();
        StringBuilder actual = new StringBuilder();


        for (double[] doubles : expectedDouble) {
            expected.append("[");
            for (int i = 0; i < doubles.length ; i++) {
                expected.append(df.format(doubles[i]));
                if ( i != doubles.length -1 ) {
                    expected.append(", ");
                }
            }
            expected.append("]");
            expected.append("\n");
        }

        for (double[] doubles : actualDouble) {
            actual.append("[");
            for (int i = 0; i < doubles.length ; i++) {
                actual.append(df.format(doubles[i]));

                if ( i != doubles.length - 1 ) {
                    actual.append(", ");

                }
            }
            actual.append("]");
            actual.append("\n");

        }

      assertEquals(expected.toString(), actual.toString());
    }
}