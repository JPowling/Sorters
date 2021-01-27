package sortalgorithms;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class GUI_Sort extends JFrame {

    private final int SCWIDTH = 1920 / 2;
    private final int SCHIGHT = 1080 / 2;
    private final int SETTINGSWIDTH = 300;
    private final int CONTROLHIGHT = 150;
    private final GridBagLayout gbl = new GridBagLayout();
    private final GridBagConstraints gbc = new GridBagConstraints();
    private GSort graphics;
    private JPanel settings;
    private JPanel control;
    private JPanel visualisation;
    private JTextField sizeArray;
    private JButton btnSort;
    private JComboBox algoBox;


    public GUI_Sort() {
        super("Screen");
        makeScreen();
        makeSettings();
        listener();
        makeVisuals();
        this.pack();
    }

    private void makeScreen() {
        setResizable(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(SCWIDTH, SCHIGHT);
        this.setPreferredSize(new Dimension(SCWIDTH, SCHIGHT));

        //set settings
        settings = new JPanel(new BorderLayout());
        settings.setBackground(Color.gray);
        settings.setPreferredSize(new Dimension(SETTINGSWIDTH, SCHIGHT));

        //set control
        control = new JPanel();
        control.setBackground(Color.LIGHT_GRAY);
        control.setPreferredSize(new Dimension(SCWIDTH - SETTINGSWIDTH, CONTROLHIGHT));

        //set visualisation
        visualisation = new JPanel();
        visualisation.setBackground(Color.BLACK);
        visualisation.setPreferredSize(new Dimension(SCWIDTH - SETTINGSWIDTH, SCHIGHT - CONTROLHIGHT));


        JPanel screen = new JPanel(new BorderLayout());
        JPanel rightScreen = new JPanel(new BorderLayout());
        rightScreen.add(visualisation, BorderLayout.CENTER);
        rightScreen.add(control, BorderLayout.SOUTH);

        screen.add(settings, BorderLayout.WEST);
        screen.add(rightScreen, BorderLayout.CENTER);
        this.add(screen);
        this.setVisible(true);
    }

    private void makeSettings() {
        JPanel top = new JPanel(gbl);
        sizeArray = new JTextField("enter arraysize", 1);
        sizeArray.setPreferredSize(new Dimension(100, 20));
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 4;
        gbc.gridheight = 2;
        gbc.weightx = 3;
        gbl.setConstraints(sizeArray, gbc);
        top.add(sizeArray);

        btnSort = new JButton("Start");
        gbc.gridx = 6;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 0;
        gbl.setConstraints(btnSort, gbc);
        top.add(btnSort);

        String[] list = new String[Algorithmus.getAlgoSize()];
        for (int i = 0; i < Algorithmus.getAlgoSize(); i++) {
            list[i] = Algorithmus.getAlgorithmus(i).getName();
        }
        algoBox = new JComboBox(list);
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
        graphics = new GSort(SCWIDTH - SETTINGSWIDTH, SCHIGHT - CONTROLHIGHT);
        visualisation.add(graphics);
        new Thread(() -> {
            while (true) {
                graphics.render();
            }
        }).start();
    }

    private void listener() {
        btnSort.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sort(algoBox.getSelectedIndex());
            }
        });
    }

    private void sort(int n) {
        String a = sizeArray.getText();
        if (a.equals("enter_arraysize")) {
            a = "100";
        }
        Algorithmus.fillDaten(Integer.parseInt(a));
        Algorithmus.getAlgorithmus(n).sort();
    }
}
