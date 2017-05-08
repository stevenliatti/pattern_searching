package algorithms;

import java.util.LinkedHashMap;

/**
 * Classe implémentant l'algorithme de recherche de motifs
 * selon Boyer-Moore.
 *
 * @author Raed Abdennadher
 * @author Steven Liatti
 */
public class BoyerMoore extends FindPattern{
    private LinkedHashMap<String, Integer> tab1;
    private int[] tab2;
    private String text;
    private String pattern;

    /**
     * Effectuer les calculs de prétraitement et initialisation des variables
     *
     * @param text le texte à explorer
     * @param pattern le motif recherché
     */
    public BoyerMoore(String text, String pattern) {
        super(text, pattern);
        this.pattern = pattern;
        this.text = text;
        buildTab1();
        buildTab2();
    }

    /**
     * Contruction du premier tableau de BoyerMoore
     */
    private void buildTab1() {
        int m = pattern.length();
        tab1 = new LinkedHashMap<>();
        char[] motifCharArray = pattern.toCharArray();
        for (int i = 0; i < m; i++) {
            String charString = String.valueOf(motifCharArray[i]);
            if (tab1.containsKey(charString)) {
                tab1.replace(charString, m - i - 1);
            } else {
                tab1.putIfAbsent(charString, m - i - 1);
            }
        }
        tab1.put("autre", pattern.length());
    }

    /**
     * Contruction du premier tableau de BoyerMoore
     */
    private void buildTab2() {
        int m = pattern.length();
        tab2 = new int[m];

        for (int i = m - 1; i >= 0; i--) {
            String partMotif = pattern.substring(i);
            int previous = pattern.charAt(0);
            if (i > 0) {
                previous = pattern.charAt(i - 1);
            }
            if (i == 0) {
                partMotif = partMotif.substring(1);
            }
            int pos = pattern.lastIndexOf(partMotif);
            while (pos > 0 && previous == pattern.charAt(pos - 1)) {
                pos = pattern.lastIndexOf(partMotif, pos - 1);
            }
            int shift = -1;
            if (pos == -1) {
                do {
                    if (partMotif.length() == m) {
                        shift = m;
                        break;
                    } else {
                        partMotif = partMotif.substring(1);
                    }
                } while (pattern.indexOf(partMotif) != 0);
                if (shift == -1) {
                    shift = m - partMotif.length();
                }
            } else {
                shift = m - pos - partMotif.length();
            }
            tab2[m - i - 1] = shift ;
        }
    }

    @Override
    public void findPattern() {
        int m = pattern.length();
        int t = text.length();
        int s = m;
        while (s <= t) {
            int j = m;
            while (j > 0 && text.charAt(s - m + j - 1) == pattern.charAt(j - 1)) {
                j--;
            }
            if (j == 0) {
                occurences.add(s - m + 1);
            }
            if (j == m) {
                if (tab1.containsKey(String.valueOf(text.charAt(s - 1)))) {
                    s += tab1.get(String.valueOf(text.charAt(s - 1)));
                } else
                    s += tab1.get("autre");
            } else {
                s += tab2[m - j - 1];
            }
        }
    }

    @Override
    public void output() {
        String[] keys = tab1.keySet().toArray(new String[tab1.size()]);
        for (int i = 0; i < tab1.size(); i++) {
            System.out.print(keys[i] + " ");
        }
        System.out.println();
        for (int i = 0; i < tab1.size(); i++) {
            System.out.print(tab1.get(keys[i]) + " ");
        }
        System.out.println();


        for (int i : tab2) {
            System.out.print(i + " ");
        }
        System.out.println();
    }
}
