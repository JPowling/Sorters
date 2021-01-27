package sortalgorithms;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

class GUI_Sort extends JFrame {

    private final int SCREEN_WIDTH = 1920 / 2;
    private final int SCREEN_HEIGHT = 1080 / 2;

    private final int SETTINGS_WIDTH = 300;
    private final int CONTROL_HEIGHT = 150;
    public static final int DEFAULT_ARRAY_SIZE = 100;

    private final GridBagLayout gbl = new GridBagLayout();
    private final GridBagConstraints gbc = new GridBagConstraints();

    private GSort graphics;

    private JPanel settings;
    private JPanel control;
    private JPanel visualisation;

    private static JTextField sizeArray;
    private JButton btnStartStop;
    private JButton btnFillArray;
    private JComboBox<String> algoBox;
    private boolean arrayFilled;


    public GUI_Sort() {
        super("Screen");

        createScreen();
        setupScreen();
        makeVisuals();

        listener();
        this.pack();
    }

    private void createScreen() {
        setResizable(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));

        //set settings
        settings = new JPanel(new BorderLayout());
        settings.setBackground(Color.gray);
        settings.setPreferredSize(new Dimension(SETTINGS_WIDTH, SCREEN_HEIGHT));

        //set control
        control = new JPanel();
        control.setBackground(Color.LIGHT_GRAY);
        control.setPreferredSize(new Dimension(SCREEN_WIDTH - SETTINGS_WIDTH, CONTROL_HEIGHT));

        //set visualisation
        visualisation = new JPanel();
        visualisation.setBackground(Color.BLACK);
        visualisation.setPreferredSize(new Dimension(SCREEN_WIDTH - SETTINGS_WIDTH, SCREEN_HEIGHT - CONTROL_HEIGHT));


        JPanel screen = new JPanel(new BorderLayout());
        JPanel rightScreen = new JPanel(new BorderLayout());
        rightScreen.add(visualisation, BorderLayout.CENTER);
        rightScreen.add(control, BorderLayout.SOUTH);

        screen.add(settings, BorderLayout.WEST);
        screen.add(rightScreen, BorderLayout.CENTER);
        this.add(screen);
        this.setVisible(true);
    }

    private void setupScreen() {
        JPanel top = new JPanel(gbl);
        sizeArray = new JTextField("enter arraysize", 1);
        sizeArray.setPreferredSize(new Dimension(100, 20));

        //Array eingabe Textfield
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 4;
        gbc.gridheight = 2;
        gbc.weightx = 3;
        gbl.setConstraints(sizeArray, gbc);
        top.add(sizeArray);

        //Button Start/Stop
        btnStartStop = new JButton("Start");
        gbc.gridx = 6;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 0;
        gbl.setConstraints(btnStartStop, gbc);
        top.add(btnStartStop);

        //Button fillArray
        btnFillArray = new JButton("fill Array");
        gbc.gridx = 6;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 0;
        gbl.setConstraints(btnFillArray, gbc);
        top.add(btnFillArray);
        arrayFilled = false;

        //Combobox
        String[] list = new String[Algorithmus.getAlgoSize()];
        for (int i = 0; i < Algorithmus.getAlgoSize(); i++) {
            list[i] = Algorithmus.getAlgorithmus(i).getName();
        }
        algoBox = new JComboBox<>(list);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 4;
        gbc.gridheight = 1;
        gbc.weightx = 2;
        gbl.setConstraints(algoBox, gbc);
        top.add(algoBox);

        settings.add(top, BorderLayout.NORTH);
        settings.setVisible(true);
    }

    private void makeVisuals() {
        Algorithmus.fillEmpty();

        graphics = new GSort(SCREEN_WIDTH - SETTINGS_WIDTH, SCREEN_HEIGHT - CONTROL_HEIGHT);
        visualisation.add(graphics);

        new Thread(() -> {
            while (true) {
                graphics.render();
            }
        }).start();
    }

    private void listener() {
        //Button Start/Stop
        btnStartStop.addActionListener(e -> {
            if (Algorithmus.isRunning()) {
                btnStartStop.setText("Start");
                Algorithmus.stopSort();
            } else {
                sort(algoBox.getSelectedIndex());
                btnStartStop.setText("Stop");
            }
        });

        //Button fill Array
        btnFillArray.addActionListener(e -> {
            Algorithmus.fillDaten(getSizeFromTextField());
            arrayFilled = true;
        });

        //TextField sizeArray
        sizeArray.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent focusEvent) {
                sizeArray.setText("");
            }

            @Override
            public void focusLost(FocusEvent focusEvent) {

            }
        });
    }

    private void sort(int n) {
        System.out.println(Algorithmus.checkSort());
        if (!arrayFilled || Algorithmus.checkSort()) {
            Algorithmus.fillDaten(getSizeFromTextField());
        }

        Algorithmus.getAlgorithmus(n).sort();
    }

    public static int getSizeFromTextField() {
        int a;

        try {
            a = Integer.parseInt(sizeArray.getText());
        } catch (NumberFormatException exc) {
            exc.printStackTrace();

            a = DEFAULT_ARRAY_SIZE;
            sizeArray.setText(String.valueOf(DEFAULT_ARRAY_SIZE));
        }
        return a;
    }
}
