package model.utils;

import java.io.*;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;

import java.util.LinkedList;
import java.util.List;


public class FileUtils {

    public List<double[]> readDataFile(String filePath, String delimiter, char decimalSeparator) throws IOException {
        LinkedList<double[]> dataVoltage = new LinkedList<double[]>();
        /*Configuration of parser of doubles */
        DecimalFormat df = new DecimalFormat();
        DecimalFormatSymbols decimalFormatSymbols = new DecimalFormatSymbols();
        decimalFormatSymbols.setDecimalSeparator(decimalSeparator);
        df.setDecimalFormatSymbols(decimalFormatSymbols);
        File file = new File(filePath);
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line;
        while ((line = br.readLine()) != null) {
            String[] voltagesString = line.split(delimiter);
            double[] voltagesDouble = new double[voltagesString.length];
            for (int i = 0; i < voltagesString.length; i++) {
                try {
                    voltagesDouble[i] = df.parse(voltagesString[i]).doubleValue();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            dataVoltage.add(voltagesDouble);
        }
        return dataVoltage;
    }

    public void writeDataFile( List<double[]> data, String filePath, String delimiter, char decimalSeparator) {
        StringBuilder result = new StringBuilder();

        File file;
        FileOutputStream fos;
        OutputStreamWriter ow;
        BufferedWriter bw;

        DecimalFormat df = new DecimalFormat("0.0000000000");
        DecimalFormatSymbols decimalFormatSymbols = new DecimalFormatSymbols();
        decimalFormatSymbols.setDecimalSeparator(decimalSeparator);
        df.setRoundingMode(RoundingMode.HALF_UP);
        df.setDecimalFormatSymbols(decimalFormatSymbols);

        for (double[] doubles : data) {
            for (int i = 0; i < doubles.length ; i++) {
                result.append(df.format(doubles[i]));
                if ( i != doubles.length - 1 ) {
                    result.append(delimiter);
                }
            }
            result.append("\n");
        }
        try {
            file = new File(filePath);
            if (file.exists()) {
                file.delete();
            }
            fos = new FileOutputStream(file);
            ow = new OutputStreamWriter(fos);
            bw = new BufferedWriter(ow);
            bw.write(result.toString());
            bw.flush();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
