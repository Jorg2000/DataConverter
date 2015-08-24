package model;

public class Model implements IModel {
    private FileUtils fileUtils;
    private model.Math math;

    public Model() {
        fileUtils = new FileUtils();
        math = new model.Math();
    }

    public FileUtils getFileUtils() {
        return fileUtils;
    }

    public Math getMath() {
        return math;
    }
}
