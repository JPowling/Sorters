package oldSource;

import sortalgorithms.GSort;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Matthias Zimmer und Uwe Seckinger (verändert Hanna Wüst)
 * @version 2018.03.05
 * Oberfläche zur Wiedergabe von Sortierergebnissen
 */
public class GUI_Sortierverfahren extends JPanel {

    private final JPanel contentPane;
    private final JTextField textFieldAnzahlDatensaetze;
    private final JTextArea textAreaDatensaetze;
    private final Datenverwaltung datenverwaltung;
    private final GSort graphics;
    private final JScrollPane scrollPane;
    private final JLabel lblSortierdauer;
    private JTextField textFieldZuSuchenderDatensatz;
    private JLabel lblAusgabeDatensatzSuche0;
    private JLabel lblAusgabeDatensatzSuche1;
    private final JLabel lblAnzahlVergleiche;
    private final int bt_tfHoehe = 23;
    private final int btAbst = 11;
    private final int anzBt = 8;

    /**
     * Create the frame.
     */
    public GUI_Sortierverfahren() {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(100, 100, 469, 510 + anzBt * 34);
        contentPane = new JPanel();
        frame.setResizable(false);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        frame.setContentPane(contentPane);
        contentPane.setLayout(null);


        textFieldAnzahlDatensaetze = new JTextField();
        textFieldAnzahlDatensaetze.setText("5");
        textFieldAnzahlDatensaetze.setBounds(10, 11, 131, bt_tfHoehe);
        contentPane.add(textFieldAnzahlDatensaetze);
        textFieldAnzahlDatensaetze.setColumns(10);

        JButton btnDatensaetzeErzeugen = new JButton("zuf\u00E4llige Datens\u00E4tze erzeugen");
        btnDatensaetzeErzeugen.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                datenverwaltung.zufaelligeDatensaetzeErzeugen(Integer.parseInt(textFieldAnzahlDatensaetze.getText()));
                datensaetzeAusgeben();
            }
        });
        btnDatensaetzeErzeugen.setBounds(151, 10, 290, bt_tfHoehe);
        contentPane.add(btnDatensaetzeErzeugen);

        JButton btnDatensaetzeMitWiederholung = new JButton("Datens\u00E4tze mit Wiederholungen erzeugen");
        btnDatensaetzeMitWiederholung.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                datenverwaltung
                        .datensaetzeMitWiederholungErzeugen(Integer.parseInt(textFieldAnzahlDatensaetze.getText()));
                datensaetzeAusgeben();
            }
        });
        btnDatensaetzeMitWiederholung.setBounds(151, 44, 290, bt_tfHoehe);
        contentPane.add(btnDatensaetzeMitWiederholung);

        JButton btnSelectionsort = new JButton("Selectionsort durchf\u00FChren");
        btnSelectionsort.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                datenverwaltung.selectionSort();
                datensaetzeAusgeben();
            }
        });
        btnSelectionsort.setBounds(10, 100, 431, bt_tfHoehe);
        contentPane.add(btnSelectionsort);

        JButton btnBubbleSort = new JButton("Bubblesort durchf\u00FChren");
        btnBubbleSort.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                datenverwaltung.bubbleSort();
                datensaetzeAusgeben();
            }
        });
        btnBubbleSort.setBounds(10, 100+bt_tfHoehe+btAbst, 431, bt_tfHoehe);
        contentPane.add(btnBubbleSort);

        JButton btnInsertionSort = new JButton("Inerstionsort durchf\u00FChren");
        btnInsertionSort.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                datenverwaltung.insertionSort();
                datensaetzeAusgeben();
            }
        });
        btnInsertionSort.setBounds(10, 100+2*(+bt_tfHoehe+btAbst), 431, bt_tfHoehe);
        contentPane.add(btnInsertionSort);

        JButton btnQuickSort = new JButton("Quicksort durchf\u00FChren");
        btnQuickSort.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                datenverwaltung.quickSort(0, datenverwaltung.daten.length-1);
                datensaetzeAusgeben();
            }
        });
        btnQuickSort.setBounds(10, 110+3*(+bt_tfHoehe+btAbst), 431, bt_tfHoehe);
        contentPane.add(btnQuickSort);

        JButton btnMergeSort = new JButton("Mergesort durchf\u00FChren");
        btnMergeSort.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                datenverwaltung.mergeSort(0, datenverwaltung.daten.length-1);
                datensaetzeAusgeben();
            }
        });
        btnMergeSort.setBounds(10, 110+4*(+bt_tfHoehe+btAbst), 431, bt_tfHoehe);
        contentPane.add(btnMergeSort);


        //edit Jens
        JButton btnBogosort = new JButton("Bogosort durchf\u00FChren");
        btnBogosort.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                datenverwaltung.bogosort();
                datensaetzeAusgeben();
            }
        });
        btnBogosort.setBounds(10, 120+5*(+bt_tfHoehe+btAbst), 431, bt_tfHoehe);
        contentPane.add(btnBogosort);
        //end edit Jens


        lblSortierdauer = new JLabel("Sortierdauer: 0 s ");
        lblSortierdauer.setBounds(10, 128+(anzBt-1)*(+bt_tfHoehe+btAbst), 431, 14);
        contentPane.add(lblSortierdauer);

        lblAnzahlVergleiche = new JLabel("Anzahl Vergleiche:  0");
        lblAnzahlVergleiche.setBounds(10, 145+(anzBt-1)*(+bt_tfHoehe+btAbst), 431, 14);
        contentPane.add(lblAnzahlVergleiche);

        scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 166 + (anzBt - 1) * (+bt_tfHoehe + btAbst), 431, 337);
        contentPane.add(scrollPane);

        textAreaDatensaetze = new JTextArea();
        textAreaDatensaetze.setEditable(false);
        scrollPane.setViewportView(textAreaDatensaetze);

        // Instanz der Datenverwaltung erzeugen
        datenverwaltung = new Datenverwaltung();
        graphics = new GSort(datenverwaltung);

        frame.setVisible(true);
        new Thread(() -> {
            while (true) {
                graphics.render();
            }
        }).start();
    }

    // Gibt die Datensätze aus dem Objekt datenverwaltung aus
    private void datensaetzeAusgeben() {
        // Ausgabefeld leeren
        textAreaDatensaetze.setText("");
        // Datensätze ausgeben
        for (int i = 0; i < datenverwaltung.getDaten().length; i++) {
            textAreaDatensaetze.append(datenverwaltung.getDaten()[i] + "\n");
        }
        // Zeitdauer setzen
        lblSortierdauer.setText("Sortierdauer: " + datenverwaltung.getLaufzeit() + " ms");

        // Anzahl Vergleiche setzen
        lblAnzahlVergleiche.setText("Anzahl Vergleiche: " + datenverwaltung.getAnzahlVergleiche());
    }

    public Datenverwaltung getDV(){
        return datenverwaltung;
    }


}
