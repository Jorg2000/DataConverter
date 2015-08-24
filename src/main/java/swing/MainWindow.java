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
    private Controller controller;
    private List<double[]> voltageData;
    private List<double[]> coefsData;
    private List<double[]> accelerationsData;


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

                String filePathData = loadFile.getSelectedFile().getAbsolutePath();

                voltageData = controller.loadData(filePathData);
                int rows;
                int cols;
                loadedRowsData.setText(String.valueOf(rows = voltageData.size()) + " rows");
                loadedColumnsData.setText(String.valueOf(cols = voltageData.get(0).length) + " columns");
                loadedMeasurements.setText(String.valueOf(rows * cols) + " measurements");

                loadedRowsData.setVisible(true);
                loadedColumnsData.setVisible(true);
                loadedMeasurements.setVisible(true);
                openCoefButton.setEnabled(true);
            }
        });

        openCoefButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser loadFile = new JFileChooser("src/test/resources/");
                loadFile.showOpenDialog(null);
                String filePathData = loadFile.getSelectedFile().getAbsolutePath();
                coefsData = controller.loadData(filePathData);

                loadedRowsCoefs.setText(String.valueOf(coefsData.size()) + " rows");
                loadedRowsCoefs.setVisible(true);
                performConversionButton.setEnabled(true);
            }
        });

        performConversionButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                accelerationsData = controller.convertData(voltageData, coefsData);
                JFileChooser saveFile = new JFileChooser("src/test/resources/");
                saveFile.showSaveDialog(null);
                String filePathData = saveFile.getSelectedFile().getAbsolutePath();
                controller.writeData(filePathData, accelerationsData);

            }
        });

        setVisible(true);
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }

    /*
    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
*/
}
