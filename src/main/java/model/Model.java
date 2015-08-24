package model;

import model.utils.*;
import model.utils.Math;

public class Model implements IModel {
    private FileUtils fileUtils;
    private model.utils.Math math;

    public Model() {
        fileUtils = new FileUtils();
        math = new Math();
    }

    public FileUtils getFileUtils() {
        return fileUtils;
    }

    public Math getMath() {
        return math;
    }
}
