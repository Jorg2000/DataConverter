package view;

import controller.Controller;
import swing.MainWindow;


public class ConverterView implements IConverterView {

    private final Controller controller;

    public ConverterView(Controller c) {
        controller = c;
    }

    public void startView() {
        MainWindow mainForm = new MainWindow(controller);

    }
}
