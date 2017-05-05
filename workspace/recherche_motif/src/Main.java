import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException, NumberFormatException {
        String text = "212121212131212 wwwwww bwwwawwww bonsbonsbonsbonsbonsabonsbonsbons " +
                "1111abt1111ab1111abt1111ab111 12121111abt1111ab1212 " +
                "12w wwwab12 w1111abww1111abwww1111abt1111abwwww bonswbonsbonswbonsbons" +
                "bons 12ww1212www121212wwww";
	    AutomatesFinis robot = new AutomatesFinis(text, "1111abt1111ab");
	    robot.printArray();
	    robot.algo();
//        new RabinKarp(text, "1111abt1111ab");

        // Ne pas modifier cette partie
        String fileName = null;
        String motif = null;
        int algo = 0;
        switch (args.length) {
            case 3:
                fileName = args[2];
            case 2:
                algo = Integer.parseInt(args[1]);
                motif = args[0];
                break;
            default:
                System.err.println("usage: java Main <motif> <algo> (<fichier_texte>)");
                System.exit(1);
        }

        // Rien d'autre ne doit etre affiche que ce qui est indique ci-dessous
        String exploredText = null;
        if (fileName != null) {
            exploredText = TextFile.fileToString(fileName);
        }
        switch (algo) {
            case 1: //Rabin-Karp
                RabinKarp rabinKarp = new RabinKarp(exploredText, motif);
                // Format de sortie -> à générer avec votre code
                if (fileName == null || exploredText == null) {
                    // Afficher la base, le nombre 1er pour le modulo, le hash du motif
                    rabinKarp.RabinKrapNoFile();
//                    System.out.println("26 37 18");
                } else {
                    // Afficher le nombre d'occurences du motif
                    // suivi de la liste des positions de sa 1ere lettre dans le texte
                    rabinKarp.RabinKarpAlgorithm();
//                    System.out.println("13"); // nombre d'occurences du motifs
//                    System.out.println("0 3 46 67 109"); //liste des positions du motif
                }
                break;
            case 2: //Automate fini
                // Format de sortie -> à générer avec votre code
	            AutomatesFinis automate;
                if (fileName == null) {
                	automate = new AutomatesFinis(motif, motif);
                	automate.printArray();
                    // Afficher le tableau de la fonction de transition
                    // P. ex. pour le motif M = "ababaca"
                    //                  a b c
//                    System.out.println("1 0 0"); // etat 0
//                    System.out.println("1 2 0"); // etat 1
//                    System.out.println("3 0 0"); // etat 2
//                    System.out.println("1 4 0"); // etat 3
//                    System.out.println("5 0 0"); // etat 4
//                    System.out.println("1 4 6"); // etat 5
//                    System.out.println("7 0 0"); // etat 6
//                    System.out.println("1 2 0"); // etat 7

                } else {
                    // Afficher le nombre d'occurences du motif
                    // suivi de la liste des positions de sa 1ere lettre dans le texte
	                automate = new AutomatesFinis(fileName, motif);
	                automate.algo();
//                    System.out.println("13"); // nombre d'occurences du motifs
//                    System.out.println("0 3 46 67 109"); //liste des positions du motif
                }
                break;
            case 3: //Knut-Morris-Pratt
                // Format de sortie -> à générer avec votre code
                if (fileName == null) {
                    //Afficher le tableau des prefixes
                    // P. ex. pour le motif M = "ababaca"
                    //                  0 1 2 3 4 5 6           q
                    //                  a b a b a c a         M[q]
                    System.out.println("0 0 1 2 3 0 1");  // pi[q]
                } else {
                    // Afficher le nombre d'occurences du motif
                    // suivi de la liste des positions de sa 1ere lettre dans le texte
                    System.out.println("13"); // nombre d'occurences du motifs
                    System.out.println("0 3 46 67 109"); //liste des positions du motif
                }
                break;
            case 4: //Boyer-Moore
                // Format de sortie -> à générer avec votre code
                if (fileName == null) {
                    //Afficher les deux tableaux des decalages
                    // P. ex. pour le motif M = "anpanman"
                    // 1er tableau
                    //                  a n p m *       lettre (selon ordre dans le motif)
                    System.out.println("1 0 5 2 8"); // decalage
                    // 2eme tableau
                    // partie du motif bonne (depuis la droite):
                    //            n an man nman anman panman npanman anpanman
                    // decalage:  8  3  6    6    6      6      6       6
                    System.out.println("8 3 6 6 6 6 6 6"); // decalage
                } else {
                    // Afficher le nombre d'occurences du motif
                    // suivi de la liste des positions de sa 1ere lettre dans le texte
                    System.out.println("13"); // nombre d'occurences du motifs
                    System.out.println("0 3 46 67 109"); //liste des positions du motif
                }
                break;
            default:
                System.err.println("Algorithm not implemented");
                System.exit(2);
        }
    }
}











