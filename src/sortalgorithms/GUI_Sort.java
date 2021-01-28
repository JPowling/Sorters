package sortalgorithms;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.Hashtable;

class GUI_Sort extends JFrame {

    public static final int DEFAULT_ARRAY_SIZE = 100;
    private static final String DEFAULT_ANZSWAPLABEL_Text = "tausche: ";
    private static final String DEFAULT_ANZCOMPARELABEL_Text = "vergleiche: ";
    private static JTextField sizeArray;
    private static JButton btnStartStop;
    private static String btnStartStopLabel;
    private static JLabel anzSwapLabel;
    private static JLabel anzCompareLabel;
    private final int SCREEN_WIDTH = 1920 / 2;
    private final int SCREEN_HEIGHT = 1080 / 2;
    private final int SETTINGS_WIDTH = 300;
    private final int CONTROL_HEIGHT = 150;
    private final GridBagLayout gbl = new GridBagLayout();
    private final GridBagConstraints gbc = new GridBagConstraints();
    private GSort graphics;
    private JPanel settings;
    private JPanel control;
    private JPanel visualisation;
    private JButton btnFillArray;
    private JComboBox<String> algoBox;
    private boolean arrayFilled;
    private JTextArea console;
    private JSlider delaySlider;
    private JCheckBox noDelayBox;


    public GUI_Sort() {
        super("Screen");

        createScreen();
        setupScreen();
        makeVisuals();

        listener();
        this.pack();
    }

    public static int getNumFromTextField() {
        int a;

        try {
            a = Integer.parseInt(sizeArray.getText());
        } catch (NumberFormatException exc) {
            //exc.printStackTrace(); //nicht printen, weil der Fehler kommen darf, und nervt...

            a = DEFAULT_ARRAY_SIZE;
            sizeArray.setText(String.valueOf(DEFAULT_ARRAY_SIZE));
        }
        return a;
    }

    public static void setBtnStartStopLabel(String label) {
        GUI_Sort.btnStartStopLabel = label;
        GUI_Sort.btnStartStop.setText(GUI_Sort.btnStartStopLabel);
    }

    public static void setAnzSwapLabel(String label) {
        anzSwapLabel.setText(DEFAULT_ANZSWAPLABEL_Text + label);
    }

    public static void setAnzCompareLabel(String label) {
        anzCompareLabel.setText(DEFAULT_ANZCOMPARELABEL_Text + label);
    }

    public static void resetAnzLabels() {
        anzSwapLabel.setText(DEFAULT_ANZSWAPLABEL_Text);
        anzCompareLabel.setText(DEFAULT_ANZCOMPARELABEL_Text);
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

    private void setupScreen() {
        JPanel top = new JPanel(gbl);
        JPanel mid = new JPanel(gbl);
        JPanel bottom = new JPanel(gbl);
        sizeArray = new JTextField("enter arraysize", 1);
        sizeArray.setPreferredSize(new Dimension(100, 20));

        //Array eingabe Textfield
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(15, 10, 5, 5);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 4;
        gbc.gridheight = 2;
        gbc.weightx = 3;
        gbl.setConstraints(sizeArray, gbc);
        top.add(sizeArray);

        //Button Start/Stop
        GUI_Sort.btnStartStopLabel = "Start";
        btnStartStop = new JButton(btnStartStopLabel);
        gbc.insets = new Insets(15, 10, 5, 10);
        gbc.gridx = 6;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 0;
        gbl.setConstraints(btnStartStop, gbc);
        top.add(btnStartStop);

        //Button fillArray
        btnFillArray = new JButton("fill Array");
        gbc.insets = new Insets(15, 10, 5, 10);
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
        gbc.insets = new Insets(15, 10, 5, 5);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 4;
        gbc.gridheight = 1;
        gbc.weightx = 2;
        gbl.setConstraints(algoBox, gbc);
        top.add(algoBox);

        //Label anzSwapLabel
        anzSwapLabel = new JLabel(DEFAULT_ANZSWAPLABEL_Text);
        gbc.insets = new Insets(15, 10, 0, 5);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        gbc.gridheight = 1;
        gbc.weightx = 1;
        gbl.setConstraints(anzSwapLabel, gbc);
        mid.add(anzSwapLabel);

        //Label anzCompareLabel
        anzCompareLabel = new JLabel(DEFAULT_ANZCOMPARELABEL_Text);
        gbc.insets = new Insets(0, 10, 0, 5);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 3;
        gbc.gridheight = 1;
        gbc.weightx = 1;
        gbl.setConstraints(anzCompareLabel, gbc);
        mid.add(anzCompareLabel);

        //TextField Console
        console = new JTextArea("This is the Console", 10, 1);
        gbc.insets = new Insets(5, 10, 15, 10);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 6;
        gbc.gridheight = 15;
        gbc.weightx = 1;
        gbl.setConstraints(console, gbc);
        mid.add(console);

        //Slider delaySlide
        delaySlider = new JSlider();
        gbc.insets = new Insets(15, 10, 5, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        gbc.gridheight = 1;
        gbc.weightx = 1;
        gbl.setConstraints(delaySlider, gbc);

        delaySlider.setMaximum(1000);
        delaySlider.setMinimum(0);
        delaySlider.setPaintLabels(true);
        delaySlider.setPaintTicks(true);

        delaySlider.setMajorTickSpacing(100);
        delaySlider.setMinorTickSpacing(50);

        delaySlider.setValue(500);
        Algorithmus.delay = 1000 - delaySlider.getValue();

        Hashtable<Integer, JLabel> delaySliderLabels = new Hashtable<>();
        delaySliderLabels.put(1000, new JLabel("Fast"));
        delaySliderLabels.put(0, new JLabel("Slow"));
        delaySlider.setLabelTable(delaySliderLabels);

        delaySlider.addChangeListener(changeEvent -> {
            Algorithmus.delay = 1000 - delaySlider.getValue();
            if (noDelayBox.isSelected())
                noDelayBox.setSelected(false);
        });

        bottom.add(delaySlider);

        noDelayBox = new JCheckBox("No delay");
        gbc.insets = new Insets(0, 20, 0, 0);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 1;
        gbl.setConstraints(noDelayBox, gbc);

        noDelayBox.addChangeListener(changeEvent -> {
            if (noDelayBox.isSelected()) {
                Algorithmus.delay = -1;
            } else {
                Algorithmus.delay = 1000 - delaySlider.getValue();
            }
        });

        bottom.add(noDelayBox);


        settings.add(top, BorderLayout.NORTH);
        settings.add(mid, BorderLayout.CENTER);
        settings.add(bottom, BorderLayout.SOUTH);
        settings.setVisible(true);
    }

    private void listener() {
        //Button Start/Stop
        btnStartStop.addActionListener(e -> {
            if (Algorithmus.isRunning()) {
                Algorithmus.stopSortThread();
            } else {
                sort(algoBox.getSelectedIndex());
            }
        });

        //Button fill Array
        btnFillArray.addActionListener(e -> {
            Algorithmus.fillDaten(getNumFromTextField());
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
            Algorithmus.fillDaten(getNumFromTextField());
        }

        Algorithmus.getAlgorithmus(n).sort();
    }
}
