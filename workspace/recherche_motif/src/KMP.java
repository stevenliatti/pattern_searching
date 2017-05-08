/**
 * Classe implémentant l'algorithme de recherche de motifs
 * selon Knut-Morris-Pratt.
 *
 * @author Raed Abdennadher
 * @author Steven Liatti
 */
public class KMP {
	private String text;
	private String pattern;
	private int arrayPrefix[];

	/**
	 * Construit un KMP en fonction d'un motif uniquement.
	 *
	 * @param pattern
	 */
	public KMP(String pattern) {
		this.pattern = pattern;
		buildArrayPrefix();
	}

	/**
	 * Construit un KMP en fonction d'un texte et d'un motif.
	 *
	 * @param text
	 * @param pattern
	 */
	public KMP(String text, String pattern) {
		this.text = text;
		this.pattern = pattern;
		buildArrayPrefix();
	}

	/**
	 * Affiche le tableau des préfixes.
	 */
	public void printArray() {
		for (int i = 1; i < arrayPrefix.length; i++) {
			System.out.print(arrayPrefix[i] + " ");
		}
		System.out.println();
	}

	/**
	 * Fonction exécutant l'algorithme des automates finis, tel
	 * que vu en cours. Imprime le nombre d'occurences puis leur
	 * position dans le texte (sur une nouvelle ligne).
	 */
	public void findPattern() {
		int occurences = 0;
		StringBuilder positionsBuilder = new StringBuilder();

		int q = 0;
		for (int i = 0; i < text.length(); i++) {
			while (q > 0 && pattern.charAt(q) != text.charAt(i)) {
				q = arrayPrefix[q];
			}
			if (pattern.charAt(q) == text.charAt(i)) {
				q++;
			}
			if (q == pattern.length()) {
				occurences++;
				positionsBuilder.append(i - pattern.length() + 2 + " ");
				q = arrayPrefix[q];
			}
		}
		System.out.println(occurences + "\n" + positionsBuilder.toString());
	}

	private void buildArrayPrefix() {
		arrayPrefix = new int[pattern.length() + 1];
		int j = 1;
		int k = 0;
		arrayPrefix[0] = -1;
		while(j < pattern.length()) {
			if(k == -1 || pattern.charAt(j) == pattern.charAt(k)) {
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
