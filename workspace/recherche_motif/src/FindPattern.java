import java.util.ArrayList;
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
     * Construit un FindPattern à partir d'un texte et d'un motif.
     *
     * @param text
     * @param pattern
     */
    public FindPattern(String text, String pattern) {
        this.text = text;
        this.pattern = pattern;
        this.occurences = new ArrayList<>();
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
}
