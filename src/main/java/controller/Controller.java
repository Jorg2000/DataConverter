package controller;

import model.utils.FileUtils;
import model.utils.Math;
import model.Model;

import java.io.IOException;
import java.util.List;


public class Controller implements IController {
    private final FileUtils fileUtils;
    private final Math math;

    public Controller( Model model) {
        fileUtils  = model.getFileUtils();
        math = model.getMath();
    }

    public List<double[]> loadData(String uri) {
        try {
            return  fileUtils.readDataFile(uri,"\t",',');
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }

    public void writeData(String uri, List<double[]> data) {
        fileUtils.writeDataFile(data,uri,"\t",',');
    }

    public List<double[]> convertData(List<double[]> data, List<double[]> coefficients) {
        return math.voltageToAcceleration(data, coefficients);
    }
}
