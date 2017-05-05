import java.util.Set;
import java.util.TreeSet;

public class AutomatesFinis {
	private String text;
	private String pattern;
	private String alphabet;
	private int array[][];

	public AutomatesFinis(String text, String pattern) {
		this.text = text;
		this.pattern = pattern;
		buildAlphabet();
		buildRobot();
	}

	public void printArray() {
		Character letter;
		System.out.print("\t");
		for (int i = 0; i < alphabet.length(); i++) {
			letter = alphabet.charAt(i);
			if (pattern.contains(letter.toString())) {
				System.out.print(alphabet.charAt(i) + "\t");
			}
		}
		System.out.println();
		for (int i = 0; i < array.length; i++) {
			System.out.print(i + ":\t");
			for (int j = 0; j < array[0].length; j++) {
				letter = alphabet.charAt(j);
				if (pattern.contains(letter.toString())) {
					System.out.print(array[i][j] + "\t");
				}
			}
			System.out.println();
		}
	}

	public void algo() {
		int occurences = 0;
		StringBuilder positionsBuilder = new StringBuilder();
		int q = 0;
		for (int i = 0; i < text.length(); i++) {
			int t = alphabet.indexOf(text.charAt(i));
			q = array[q][t];
			if (q == pattern.length()) {
				occurences++;
				positionsBuilder.append(i - pattern.length() + 2 + " ");
			}
		}
		System.out.println(occurences + "\n" + positionsBuilder.toString());
	}

	private void buildAlphabet() {
		Set<Character> alphabetSet = new TreeSet<>();
		for (int i = 0; i < text.length(); i++) {
			alphabetSet.add(text.charAt(i));
		}
		StringBuilder alphabetBuilder = new StringBuilder(alphabetSet.size());
		for (Character c : alphabetSet) { alphabetBuilder.append(c); }
		alphabet = alphabetBuilder.toString();
	}

	private void buildRobot() {
		// Le tableau avec les états en ligne et l'alphabet en colonne
		array = new int[pattern.length() + 1][alphabet.length()];
		// Pour l'état 0, on initialise tout l'alphabet à 0 (tout le monde pointe sur l'état 0)
		for (int i = 0; i < alphabet.length(); i++) {
			array[0][i] = 0;
		}
		for (int i = 1; i <= pattern.length(); i++) {
			int patternIndex = alphabet.indexOf(pattern.charAt(i - 1));
			int tempState = array[i - 1][patternIndex];
			array[i - 1][patternIndex] = i;
			for (int j = 0; j < alphabet.length(); j++) {
				array[i][j] = array[tempState][j];
			}
		}
	}
}
