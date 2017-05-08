import java.math.BigInteger;
import java.util.HashMap;
import java.util.Random;
/**
 * Classe implémentant l'algorithme de recherche de motifs
 * selon Rabin-Karp.
 *
 * @author Raed Abdennadher
 * @author Steven Liatti
 */
public class RabinKarp extends FindPattern{
    private HashMap<Character, Integer> dictionnary;
    private int q;
    private int B;
    private BigInteger p;
    private int m;

    /**
     * Effectuer les calculs de prétraitement et initialisation des variables
     *
     * @param text le texte à explorer
     * @param pattern le motif recherché
     */
    RabinKarp(String text, String pattern) {
        super(text, pattern);
        buildDictionnary();
        this.text = text;
        this.pattern = pattern;
        q = chooseQ();
        B = dictionnary.size();
        m = pattern.length();
        p = hash(pattern, 0, m);
    }

    /**
     * Construire un grand entier qui représente le hash de la partie
     * entre indice min et max de la chaine s
     *
     * @param string la chaine de caractère depuis laquelle le hash est calculé
     * @param min l'indice min
     * @param max l'indice max
     * @return un entier grand qui représente le hash
     */
    private BigInteger hash(String string, int min, int max) {
        char[] mCharArray = string.toCharArray();
        String s = "";
        for (int i = min; i < max; i++) {
            s += dictionnary.get(mCharArray[i]);
        }
        return new BigInteger(s);
    }

    /**
     * Calculer q premier en choisissant un nombre aléatoire inférieur
     * à 100. Si ce dernier n'est pas premier, on ajoute 1 jusqu'à ce
     * qu'on tomber sur un nombre premier.
     *
     * @return retourne q
     */
    private static int chooseQ() {
        Random random = new Random();
        int q = random.nextInt(100);
        while (!isPrimeNumber(q)) {
            q++;
        }
        return q;
    }

    /**
     * Construire le dictionnaire de l'alphabet
     */
    private void buildDictionnary() {
        dictionnary = new HashMap<>();

        int i = 0;
        // [space..9]
        for (char c = ' '; c <= '9'; c++, i++) {
            dictionnary.put(c, i);
        }
        // [A..Z]
        for (char c = 'A'; c <= 'Z'; c++, i++) {
            dictionnary.put(c, i);
        }
        // [a..z]
        for (char c = 'a'; c <= 'z'; c++, i++) {
            dictionnary.put(c, i);
        }
    }

    /**
     * Vérifier si un nombre n est premier ou pas
     * @param n le nombre à vérifier
     * @return true si n est premier, false sinon
     */
    private static boolean isPrimeNumber(int n) {
        for (int i = 2; i < Math.sqrt(n); i++) {
            if (n % i == 0)
                return false;
        }
        return true;
    }

    @Override
    public void findPattern() {
        int t = text.length();
        p = p.mod(BigInteger.valueOf(q));
        for (int s = 0; s < t - m + 1; s++) {
            BigInteger t_s = hash(text, s, s + m);
            if (p.intValue() == t_s.mod(BigInteger.valueOf(q)).intValue()) {
                if (text.substring(s, s + m).equals(pattern)) {
                    this.occurences.add(s + 1);
                }
            }
        }
    }

    @Override
    public void output() {
        System.out.println(B + " " + q + " " + p);
    }
}
