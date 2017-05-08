

public class KMP {
	private String text;
	private String pattern;
	private int arrayPrefix[];

	public KMP(String pattern) {
		this.pattern = pattern;
		buildArrayPrefix();
	}

	public KMP(String text, String pattern) {
		this.text = text;
		this.pattern = pattern;
		buildArrayPrefix();
	}

	public void printArray() {
		for (int i = 1; i < arrayPrefix.length; i++) {
			System.out.print(arrayPrefix[i] + " ");
		}
		System.out.println();
	}

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
