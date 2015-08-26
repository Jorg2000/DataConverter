package model.utils;

import java.io.*;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;

public class LineByLineUtils {

    public boolean convertLineByLine(String uriInput, String uriCoef, String uriOut,
                                     String delimiter, char decimalSeparator,
                                     Math m)  {
        try {

            File input = new File(uriInput);
            File coef = new File(uriCoef);
            File out = new File(uriOut);

            if (out.exists()) {
                out.delete();
            }

            BufferedReader brInput = new BufferedReader(new FileReader(input));
            BufferedReader brCoef = new BufferedReader(new FileReader(coef));
            BufferedWriter bwOut = new BufferedWriter(new FileWriter(out));

        /*Using number of lines in file with coefficient as reference to the number of lines of the data givens to conversion.
        * in case when lines  in data file are less then lines in coefficient file we break loop of reading */
            String coefLine;
            String dataLine;
            while ((coefLine = brCoef.readLine()) != null) {
                double currentCoef = parseDoubleString(coefLine, delimiter, decimalSeparator)[0];
                if ((dataLine = brInput.readLine()) != null) {
                    double[] data = parseDoubleString(dataLine, delimiter, decimalSeparator);
                    double[] result = m.voltageToAccelerationLine(data, currentCoef);
                    bwOut.write(doubleArrToString(result, delimiter, decimalSeparator));
                    bwOut.flush();
                }
            }
            return true;
        }
        catch (IOException e) {
            return false;
        }

    }

    /*Parsing string with double numbers*/
    private double[] parseDoubleString(String data, String delimiter, char decimalSeparator) {
        DecimalFormat df = new DecimalFormat();
        DecimalFormatSymbols decimalFormatSymbols = new DecimalFormatSymbols();
        decimalFormatSymbols.setDecimalSeparator(decimalSeparator);
        df.setDecimalFormatSymbols(decimalFormatSymbols);
        String[] dataString = data.split(delimiter);
        double[] dataDouble = new double[dataString.length];

        for (int i = 0; i < dataString.length; i++) {
            try {
                dataDouble[i] = df.parse(dataString[i]).doubleValue();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return dataDouble;
    }
    /* Converting double data to string with given precision*/
    private String doubleArrToString(double[] data, String delimiter, char decimalSeparator) {
        DecimalFormat df = new DecimalFormat("0.0000000000");
        DecimalFormatSymbols decimalFormatSymbols = new DecimalFormatSymbols();
        decimalFormatSymbols.setDecimalSeparator(decimalSeparator);
        df.setRoundingMode(RoundingMode.HALF_UP);
        df.setDecimalFormatSymbols(decimalFormatSymbols);
        StringBuilder result = new StringBuilder();
            for (int i = 0; i < data.length ; i++) {
                result.append(df.format(data[i]));
                if ( i != data.length - 1 ) {
                    result.append(delimiter);
                }
            }
            result.append("\n");
        return result.toString();
    }
}
