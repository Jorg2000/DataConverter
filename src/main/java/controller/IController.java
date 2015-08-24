package controller;

import java.util.List;

public interface IController {
    List<double[]> loadData(String uri);
    void writeData(String uri, List<double[]> data);
    List<double[]> convertData(List<double[]> data, List<double[]> coefficients);
    void convertLineByLine(String uriData, String uriCoef);

}
