package algorithms;

import java.util.LinkedHashMap;

/**
 * Classe implémentant l'algorithme de recherche de motifs
 * selon Boyer-Moore.
 *
 * @author Raed Abdennadher
 * @author Steven Liatti
 */
public class BoyerMoore {
    private LinkedHashMap<String, Integer> tab1;
    private int[] tab2;
    private String T;
    private String M;

    BoyerMoore(String T, String M) {
        this.M = M;
        this.T = T;
        buildTab1();
        buildTab2();
    }

    void BoyerMooreAlgorithm() {
        int m = M.length();
        int t = T.length();
        int s = m;
        int occurences = 0;
        String positions = "";
        while (s <= t) {
            int j = m;
            while (j > 0 && T.charAt(s - m + j - 1) == M.charAt(j - 1)) {
                j--;
            }
            if (j == 0) {
                positions += String.valueOf(s - m + 1) + " ";
                occurences++;
            }
            if (j == m) {
                if (tab1.containsKey(String.valueOf(T.charAt(s - 1)))) {
                    s += tab1.get(String.valueOf(T.charAt(s - 1)));
                } else
                    s += tab1.get("autre");
            } else {
                s += tab2[m - j - 1];
            }
        }
        System.out.println(occurences);
        System.out.println(positions);
    }

    private void buildTab1() {
        int m = M.length();
        tab1 = new LinkedHashMap<>();
        char[] motifCharArray = M.toCharArray();
        for (int i = 0; i < m; i++) {
            String charString = String.valueOf(motifCharArray[i]);
            if (tab1.containsKey(charString)) {
                tab1.replace(charString, m - i - 1);
            } else {
                tab1.putIfAbsent(charString, m - i - 1);
            }
        }
        tab1.put("autre", M.length());
    }

    private void buildTab2() {
        int m = M.length();
        tab2 = new int[m];

        for (int i = m - 1; i >= 0; i--) {
            String partMotif = M.substring(i);
            int previous = M.charAt(0);
            if (i > 0) {
                previous = M.charAt(i - 1);
            }
            if (i == 0) {
                partMotif = partMotif.substring(1);
            }
            int pos = M.lastIndexOf(partMotif);
            while (pos > 0 && previous == M.charAt(pos - 1)) {
                pos = M.lastIndexOf(partMotif, pos - 1);
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
                } while (M.indexOf(partMotif) != 0);
                if (shift == -1) {
                    shift = m - partMotif.length();
                }
            } else {
                shift = m - pos - partMotif.length();
            }
            tab2[m - i - 1] = shift ;
        }
    }

    private void printTab(LinkedHashMap<String, Integer> tab) {
        String[] keys = tab.keySet().toArray(new String[tab.size()]);
        for (int i = 0; i < tab.size(); i++) {
            System.out.print(keys[i] + " ");
        }
        System.out.println();
        for (int i = 0; i < tab.size(); i++) {
            System.out.print(tab.get(keys[i]) + " ");
        }
        System.out.println();
    }

    void printTab1() {
        printTab(tab1);
    }

    void printTab2() {
        for (int i : tab2) {
            System.out.print(i + " ");
        }
        System.out.println();
    }
}
