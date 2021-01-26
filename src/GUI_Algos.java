import java.util.Arrays;

public class GUI_Algos {
    public GUI_Algos() {
        System.out.println("Liste der Algorithmen:");
        for (int i = 0; i < Algorithmus.getAlgoSize(); i++) {
            Algorithmus tempAlgo = Algorithmus.getAlgorithmus(i);
            System.out.println(i + ". " + tempAlgo.getName());
        }
        Algorithmus.fillDaten(100);
        System.out.println(Arrays.toString(Algorithmus.daten));
        Algorithmus sortAlgo = Algorithmus.getAlgorithmus(1);
        sortAlgo.sort();
        System.out.println(Arrays.toString(Algorithmus.daten));
    }
}
