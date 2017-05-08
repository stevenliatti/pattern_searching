import java.io.BufferedReader;

/**
 * Classe permettant de remplir un String avec le contenu d'un fichier.
 *
 * @author Raed Abdennadher
 * @author Steven Liatti
 */
public class TextFile {
    /**
     * Convertit un fichier texte en String.
     *
     * @param fileName Le nom du fichier à lire.
     * @return Un String avec le contenu du fichier.
     */
    public static String fileToString(String fileName) {
        String string = "";
        try {
            BufferedReader buffer = new BufferedReader(new java.io.FileReader(fileName));
            String line;
            while ((line = buffer.readLine()) != null) {
                string += line;
            }
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
        return string;
    }
}
