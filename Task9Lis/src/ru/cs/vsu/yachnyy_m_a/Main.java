package ru.cs.vsu.yachnyy_m_a;

import org.apache.commons.cli.*;
import ru.cs.vsu.yachnyy_m_a.util.ArrayUtils;
import ru.cs.vsu.yachnyy_m_a.util.SwingUtils;

import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class Main {
    public static final String PROGRAM_NAME_IN_HELP = "program (-h | -w  -i <in-file> [-o <out-file>])";

    public static void main(String[] args) {


        Options cmdLineOptions = new Options();
        cmdLineOptions.addOption("h", "help", false, "Show help");
        cmdLineOptions.addOption("w", "window", false, "Use window user interface");
        cmdLineOptions.addOption("i1", "input-file-1", true, "Input file 1");
        cmdLineOptions.addOption("i2", "input-file-2", true, "Input file 2");
        cmdLineOptions.addOption("o", "output-file", true, "Output file");

        CommandLineParser parser = new DefaultParser();
        CommandLine cmdLine = null;

        try {
            cmdLine = parser.parse(cmdLineOptions, args);
        } catch (Exception e) {
            new HelpFormatter().printHelp(PROGRAM_NAME_IN_HELP, cmdLineOptions);
            System.exit(1);
        }

        if (cmdLine.hasOption("h")) {
            new HelpFormatter().printHelp(PROGRAM_NAME_IN_HELP, cmdLineOptions);
            System.exit(1);
        }
        if (cmdLine.hasOption("w")) {
            winMain();
        } else {
            if (!cmdLine.hasOption("i1") || !cmdLine.hasOption("i2")) {
                new HelpFormatter().printHelp(PROGRAM_NAME_IN_HELP, cmdLineOptions);
                System.exit(1);
            }
            String inputFilename1 = cmdLine.getOptionValue("i1");
            String inputFilename2 = cmdLine.getOptionValue("i2");

            List<Integer> list1 = Task9.intArrayToIntegerList(ArrayUtils.readIntArrayFromFile(inputFilename1));
            List<Integer> list2 = Task9.intArrayToIntegerList(ArrayUtils.readIntArrayFromFile(inputFilename2));

            List<Integer> output = Task9.createNewList(list1, list2);
            try {
                PrintStream out = (cmdLine.hasOption("o")) ? new PrintStream(cmdLine.getOptionValue("o")) : System.out;
                out.println(ArrayUtils.toString(Task9.integerListToIntArray(output)));
                out.close();
            } catch (Exception exception) {
                SwingUtils.showErrorMessageBox(exception);
            }
        }
    }

    private static void winMain() {
        Locale.setDefault(Locale.ROOT);
        SwingUtils.setDefaultFont("Microsoft Sans Serif", 18);
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormMain().setVisible(true);
            }
        });
    }
}
