package algorithms;

import java.util.List;

/**
 * Classe définissant une recherche de motifs.
 *
 * @author Raed Abdennadher
 * @author Steven Liatti
 */
public abstract class FindPattern {
    protected String text;
    protected String pattern;
    protected List<Integer> occurences;

    /**
     * Construit un algorithms.FindPattern à partir d'un texte et d'un motif.
     *
     * @param text
     * @param pattern
     */
    public FindPattern(String text, String pattern) {
        this.text = text;
        this.pattern = pattern;
    }

    /**
     * Applique un algorithme de recherche. Remplit la liste
     * d'occurences si le motif se trouve dans le texte avec
     * ses positions.
     */
    public abstract void findPattern();

    /**
     * Affiche des résultats intermédiaires demandés.
     */
    public abstract void output();

    /**
     * Retourne la liste des occurences.
     *
     * @return La liste des occurences.
     */
    public List<Integer> getOccurences() {
        return occurences;
    }

    @Override
    public String toString() {
        String str = occurences.size() + "\n";
        for (Integer i : occurences) {
            str += i + " ";
        }
        return str;
    }
}
