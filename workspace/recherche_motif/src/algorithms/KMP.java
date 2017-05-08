package algorithms;

/**
 * Classe implémentant l'algorithme de recherche de motifs
 * selon Knut-Morris-Pratt.
 *
 * @author Raed Abdennadher
 * @author Steven Liatti
 */
public class KMP extends FindPattern {
	private int arrayPrefix[];

	/**
	 * Construit un KMP en fonction d'un texte et d'un motif.
	 *
	 * @param text Le texte dans lequel le motif sera cherché.
	 * @param pattern Le motif.
	 */
	public KMP(String text, String pattern) {
		super(text, pattern);
		buildArrayPrefix();
	}

	@Override
	public void output() {
		for (int i = 1; i < arrayPrefix.length; i++) {
			System.out.print(arrayPrefix[i] + " ");
		}
		System.out.println();
	}

	@Override
	public void findPattern() {
		int q = 0;
		for (int i = 0; i < text.length(); i++) {
			while (q > 0 && pattern.charAt(q) != text.charAt(i)) {
				q = arrayPrefix[q];
			}
			if (pattern.charAt(q) == text.charAt(i)) {
				q++;
			}
			if (q == pattern.length()) {
				occurences.add(i - pattern.length() + 2);
				q = arrayPrefix[q];
			}
		}
	}

	/**
	 * Cette fonction construit le tableau des préfixes.
	 */
	private void buildArrayPrefix() {
		arrayPrefix = new int[pattern.length() + 1];
		int j = 1;
		int k = 0;
		arrayPrefix[0] = 0;
		while(j < pattern.length()) {
			if(k == 0 || pattern.charAt(j) == pattern.charAt(k)) {
				j++;
				k++;
				arrayPrefix[j] = k;
			}
			else {
				k = arrayPrefix[k];
			}
		}
	}
}
