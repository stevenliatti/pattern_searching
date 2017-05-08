package algorithms;

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
    public RabinKarp(String text, String pattern) {
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
     *
     * @param n
     * @param min
     * @param max
     * @return
     */
    private BigInteger hash(String n, int min, int max) {
        char[] mCharArray = n.toCharArray();
        String s = "";
        for (int i = min; i < max; i++) {
            s += dictionnary.get(mCharArray[i]);
        }
        return new BigInteger(s);
    }

    /**
     *
     * @return
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
     *
     */
    private void buildDictionnary() {
        dictionnary = new HashMap<>();

        int length = (int) '9' - (int) ' ' + 1;
        length += (int) 'Z' - (int) 'A' + 1;
        length += (int) 'z' - (int) 'a' + 1;

        char[] charArray = new char[length];
        int i = 0;
        // [space..9]
        for (char c = ' '; c <= '9'; c++, i++) {
            dictionnary.put(c, i);
            charArray[i] = c;
        }
        // [A..Z]
        for (char c = 'A'; c <= 'Z'; c++, i++) {
            dictionnary.put(c, i);
            charArray[i] = c;
        }
        // [a..z]
        for (char c = 'a'; c <= 'z'; c++, i++) {
            dictionnary.put(c, i);
            charArray[i] = c;
        }
    }

    /**
     *
     * @param n
     * @return
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
