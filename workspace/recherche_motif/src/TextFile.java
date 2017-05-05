import java.io.BufferedReader;

class TextFile {
    static String fileToString(String fileName) {
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
