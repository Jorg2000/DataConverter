package swing;

import controller.Controller;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class MainWindow extends JFrame {

    private JPanel rootPanel;
    private JButton openDataFileButton;
    private JButton performConversionButton;
    private JLabel loadedRowsData;
    private JLabel loadedColumnsData;
    private JLabel loadedMeasurements;
    private JLabel loadedRowsCoefs;
    private JButton openCoefButton;
    private final Controller controller;
    private String loadFilePath;
    private String coefFilePath;
    private String saveFilePath;


    public MainWindow(Controller c){
        super("Voltage to Acceleration Converter");
        controller = c;
        setContentPane(rootPanel);
        pack();

        performConversionButton.setEnabled(false);
        loadedRowsData.setVisible(false);
        loadedColumnsData.setVisible(false);
        loadedRowsCoefs.setVisible(false);
        loadedMeasurements.setVisible(false);
        openCoefButton.setEnabled(false);
        performConversionButton.setEnabled(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        openDataFileButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser loadFile = new JFileChooser("src/test/resources/");
                loadFile.showOpenDialog(null);
                loadFilePath = loadFile.getSelectedFile().getAbsolutePath();
                openCoefButton.setEnabled(true);
            }
        });

        openCoefButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser loadFile = new JFileChooser("src/test/resources/");
                loadFile.showOpenDialog(null);
                coefFilePath = loadFile.getSelectedFile().getAbsolutePath();
                performConversionButton.setEnabled(true);
            }
        });

        performConversionButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser saveFile = new JFileChooser("src/test/resources/");
                saveFile.showSaveDialog(null);
                saveFilePath = saveFile.getSelectedFile().getAbsolutePath();
                if (saveFilePath != null) {
                    controller.convertLineByLine(loadFilePath,coefFilePath,saveFilePath);
                }
            }
        });

        setVisible(true);
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }


}
