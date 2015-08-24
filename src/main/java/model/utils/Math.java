package model.utils;

import java.util.LinkedList;
import java.util.List;

public class Math {
    public List<double[]> voltageToAcceleration(List<double[]> voltage, List<double[]> coefficients) {
        List<double[]> accelerations = new LinkedList<double[]>();
        for (int i = 0; i < coefficients.size(); i++) {
            double[] currentVoltage = voltage.get(i);
            double[] currentCoeff = coefficients.get(i);

            double[] result = new double[currentVoltage.length];
            for (int j = 0; j < currentVoltage.length ; j++) {
                result[j] = currentVoltage[j] * currentCoeff[0];
            }
            accelerations.add(result);
        }
    return accelerations;
    }
}
