package model;

import model.utils.*;
import model.utils.Math;
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
        DecimalFormat df = new DecimalFormat("#.##########");
        DecimalFormatSymbols decimalFormatSymbols = new DecimalFormatSymbols();
        decimalFormatSymbols.setDecimalSeparator('.');
        df.setRoundingMode(RoundingMode.HALF_UP);
        df.setDecimalFormatSymbols(decimalFormatSymbols);

        /*Test results obtained in Excel*/
        expectedDouble.add(new double[]{6.5046524634,1.3635014634,7.8470180784,13.5376509654,14.2808851116,0.0993860604,10.2179889396,12.1238741628,9.9493994520});
        expectedDouble.add(new double[]{9.5210979520,12.6833402080,2.4581904320,3.8359506080,14.0655358240,5.5116582080,4.5461597760,10.6065506720,9.2263696160});
        expectedDouble.add(new double[]{14.1579170369,13.5184140567,5.0968690598,5.6231354109,10.4018691791,6.6236859465,4.6094039502,6.6655499554,13.4354428194});
        expectedDouble.add(new double[]{9.7831788100,2.2830346667,5.4757195283,9.1400243777,10.4884389543,15.1367521390,0.2352829757,0.8141704976,2.3908730434});
        expectedDouble.add(new double[]{8.6316964753, 9.8226663910, 0.0125273650, 6.5531804593, 2.4974249239, 6.3168954148, 2.8413161951, 10.2261085549, 0.7647948753});



        List<double[]> data = fileUtils.readDataFile("src/test/resources/Data.txt", "\t", ',');
        List<double[]> coeff = fileUtils.readDataFile("src/test/resources/Coefficients.txt", "\t", ',');
        actualDouble = math.voltageToAcceleration(data, coeff);

        StringBuilder expected = new StringBuilder();
        StringBuilder actual = new StringBuilder();
        StringBuilder test = new StringBuilder();

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
                test.append(doubles[i]);
                if ( i != doubles.length - 1 ) {
                    actual.append(", ");
                    test.append(", ");
                }
            }
            actual.append("]");
            actual.append("\n");
            test.append("\n");
        }

      assertEquals(expected.toString(), actual.toString());
    }
}