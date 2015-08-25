package model.utils;

import java.sql.Array;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Math {
    public List<double[]> voltageToAcceleration(List<double[]> voltage, List<double[]> coefficients) {
        List<double[]> accelerations = new LinkedList<double[]>();
        for (int i = 0; i < coefficients.size(); i++) {
            double[] currentVoltage = voltage.get(i);
            double currentCoeff = coefficients.get(i)[0];
            accelerations.add(voltageToAccelerationLine(currentVoltage,currentCoeff));
        }
    return accelerations;
    }

    public double[] voltageToAccelerationLine(double[] voltage, double coef) {
        double[] result = new double[voltage.length];

        Arrays.sort(result);
        double amp = result[result.length - 1] - result[0];
        double div = amp / (double) 2;
        double coef700 = coef / (double) 700;
        double recip = (double) 1 / coef700;
        double fin = div * recip;

        for (int i = 0; i < voltage.length ; i++) {
            //result[i] = voltage[i] * coef;
            result[i] = voltage[i] * fin;
        }
        return result;
    }


}
