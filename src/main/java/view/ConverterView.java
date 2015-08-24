package view;

import controller.Controller;
import model.Model;
import swing.MainWindow;


public class ConverterView implements IConverterView {

    private Controller controller;

    public ConverterView(Controller c) {
        controller = c;
    }

    public void startView() {
        MainWindow mainForm = new MainWindow(controller);

    }
}
