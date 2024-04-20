package ru.cs.vsu.yachnyy_m_a;

import ru.cs.vsu.yachnyy_m_a.util.ArrayUtils;
import ru.cs.vsu.yachnyy_m_a.util.JTableUtils;
import ru.cs.vsu.yachnyy_m_a.util.SwingUtils;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FilenameFilter;
import java.util.List;

public class FormMain extends JFrame {
    private JMenuBar menuBarMain;
    private JMenu menuLookAndFeel;

    private JPanel PanelMain;
    private JTable TableInput1;
    private JTable TableInput2;
    private JButton ButtonLoadInput1FromFile;
    private JButton ButtonProcess;
    private JButton ButtonFillWithRandomNumbers;
    private JButton ButtonLoadInput2FromFile;
    private JTable TableOutput;
    private JButton ButtonSaveOutputIntoFile;

    private JFileChooser InputFileChooser;
    private JFileChooser OutputFileChooser;

    public FormMain() {
        this.setTitle("Task9");
        this.setContentPane(PanelMain);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();

        TableInput1.setRowHeight(25);
        TableInput2.setRowHeight(25);
        TableOutput.setRowHeight(25);

        JTableUtils.initJTableForArray(TableInput1, 60, false, true, false, true);
        JTableUtils.initJTableForArray(TableInput2, 60, false, true, false, true);
        JTableUtils.initJTableForArray(TableOutput, 60, false, true, false, false);

        InputFileChooser = new JFileChooser();
        OutputFileChooser = new JFileChooser();
        InputFileChooser.setCurrentDirectory(new File("."));
        OutputFileChooser.setCurrentDirectory(new File("."));
        FileFilter filter = new FileNameExtensionFilter("Text files", "txt");
        InputFileChooser.addChoosableFileFilter(filter);
        OutputFileChooser.addChoosableFileFilter(filter);

        OutputFileChooser.setAcceptAllFileFilterUsed(false);
        OutputFileChooser.setDialogType(JFileChooser.SAVE_DIALOG);
        OutputFileChooser.setApproveButtonText("Save");

        menuBarMain = new JMenuBar();
        setJMenuBar(menuBarMain);
        menuLookAndFeel = new JMenu();
        menuLookAndFeel.setText("Вид");
        menuBarMain.add(menuLookAndFeel);
        SwingUtils.initLookAndFeelMenu(menuLookAndFeel);

        ButtonLoadInput1FromFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (InputFileChooser.showOpenDialog(PanelMain) == JFileChooser.APPROVE_OPTION) {
                        int[] array = ArrayUtils.readIntArrayFromFile(InputFileChooser.getSelectedFile().getPath());
                        JTableUtils.writeArrayToJTable(TableInput1, array);
                    }
                } catch (Exception exception) {
                    SwingUtils.showErrorMessageBox(exception);
                }
            }
        });

        ButtonLoadInput2FromFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (InputFileChooser.showOpenDialog(PanelMain) == JFileChooser.APPROVE_OPTION) {
                        int[] array = ArrayUtils.readIntArrayFromFile(InputFileChooser.getSelectedFile().getPath());
                        JTableUtils.writeArrayToJTable(TableInput2, array);
                    }
                } catch (Exception exception) {
                    SwingUtils.showErrorMessageBox(exception);
                }
            }
        });

        ButtonProcess.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    List<Integer> list1 = Task9.intArrayToIntegerList(JTableUtils.readIntArrayFromJTable(TableInput1));
                    List<Integer> list2 = Task9.intArrayToIntegerList(JTableUtils.readIntArrayFromJTable(TableInput2));
                    List<Integer> output = Task9.createNewList(list1, list2);
                    JTableUtils.writeArrayToJTable(TableOutput, Task9.integerListToIntArray(output));
                } catch (Exception exception) {
                    SwingUtils.showErrorMessageBox(exception);
                }
            }
        });

        ButtonFillWithRandomNumbers.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int[] arr1 = ArrayUtils.createRandomIntArray(TableInput1.getColumnCount(), -500, 500);
                int[] arr2 = ArrayUtils.createRandomIntArray(TableInput2.getColumnCount(), -500, 500);
                JTableUtils.writeArrayToJTable(TableInput1, arr1);
                JTableUtils.writeArrayToJTable(TableInput2, arr2);
            }
        });

        ButtonSaveOutputIntoFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int[] output = JTableUtils.readIntArrayFromJTable(TableOutput);
                    if (OutputFileChooser.showSaveDialog(PanelMain) == JFileChooser.APPROVE_OPTION) {
                        ArrayUtils.writeArrayToFile(OutputFileChooser.getSelectedFile().getPath(), output);
                    }
                } catch (Exception exception) {
                    SwingUtils.showErrorMessageBox(exception);
                }
            }
        });

        this.pack();
    }
}
