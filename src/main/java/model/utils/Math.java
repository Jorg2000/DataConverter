package model.utils;


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
        List<Double> data = new LinkedList<Double>();
        double[] result = new double[1];

        for (double aVoltage : voltage) {
            data.add(Double.valueOf(aVoltage));
        }
        double amp = Collections.max(data) - Collections.min(data);
        double div = amp / (double) 2;
        double coef700 = coef / (double) 700;
        double recip = (double) 1 / coef700;
        double fin = div * recip;
        result[0] = fin;
        return result;
    }

}
