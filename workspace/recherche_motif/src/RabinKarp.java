import java.math.BigInteger;
import java.util.HashMap;
import java.util.Random;

class RabinKarp {
    private HashMap<Character, Integer> dictionnary;
    private char[] charArray;
    private int q;
    private int B;
    private BigInteger p;
    private String T;
    private String M;
    private int m;

    public RabinKarp(String T, String M) {
        buildDictionnary();
        this.T = T;
        this.M = M;
        q = chooseQ();
        B = dictionnary.size();
        m = M.length();
        p = hash(M, 0, m);
    }

    public void RabinKarpNoFile() {
        System.out.println(B + " " + q + " " + p);
    }

    public void RabinKarpAlgorithm() {
        int t = T.length();
        int occurences = 0;
        String positions = "";
        p = p.mod(BigInteger.valueOf(q));
        for (int s = 0; s < t - m + 1; s++) {
            BigInteger t_s = hash(T, s, s + m);
            if (p.intValue() == t_s.mod(BigInteger.valueOf(q)).intValue()) {
                if (T.substring(s, s + m).equals(M)) {
                    positions += String.valueOf(s + 1) + " ";
                    occurences++;
                }
            }
        }
        System.out.println(occurences);
        System.out.println(positions);
    }

    private BigInteger hash(String n, int min, int max) {
        char[] mCharArray = n.toCharArray();
        String s = "";
        for (int i = min; i < max; i++) {
            s += dictionnary.get(mCharArray[i]);
        }
        return new BigInteger(s);
    }

    private static int chooseQ() {
        Random random = new Random();
        int q = random.nextInt(100);
        while (!isPrimeNumber(q)) {
            q++;
        }
        return q;
    }

    private void buildDictionnary() {
        dictionnary = new HashMap<>();

        int length = (int) '9' - (int) ' ' + 1;
        length += (int) 'Z' - (int) 'A' + 1;
        length += (int) 'z' - (int) 'a' + 1;

        charArray = new char[length];
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

    private static boolean isPrimeNumber(int n) {
        for (int i = 2; i < Math.sqrt(n); i++) {
            if (n % i == 0)
                return false;
        }
        return true;
    }
}
