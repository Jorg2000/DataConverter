package model;

import model.utils.*;
import model.utils.Math;

public class Model implements IModel {
    private final FileUtils fileUtils;
    private final model.utils.Math math;
    private final model.utils.LineByLineUtils lineByLineUtils;

    public Model() {
        fileUtils = new FileUtils();
        math = new Math();
        lineByLineUtils = new LineByLineUtils();
    }

    public FileUtils getFileUtils() {
        return fileUtils;
    }

    public Math getMath() {
        return math;
    }

    public LineByLineUtils getLineByLineUtils() { return lineByLineUtils; }
}
