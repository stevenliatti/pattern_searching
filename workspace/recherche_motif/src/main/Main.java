package main;

import algorithms.*;
import readers.TextFile;

import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException, NumberFormatException {
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
                System.err.println("usage: java main.Main <motif> <findPattern> (<fichier_texte>)");
                System.exit(1);
        }

        // Rien d'autre ne doit etre affiche que ce qui est indique ci-dessous
        String exploredText = null;
        if (fileName != null) {
            exploredText = TextFile.fileToString(fileName);
        }
        FindPattern findPattern;
        switch (algo) {
            case 1: //Rabin-Karp
                findPattern = new RabinKarp(exploredText, motif);
                if (fileName == null) {
                    // Afficher la base, le nombre 1er pour le modulo, le hash du motif
                    findPattern.output();
                } else {
                    // Afficher le nombre d'occurences du motif
                    // suivi de la liste des positions de sa 1ere lettre dans le texte
                    findPattern.findPattern();
                    System.out.println(findPattern);
                }
                break;
            case 2: //Automate fini
                if (fileName == null) {
                    // Afficher le tableau de la fonction de transition
                    // P. ex. pour le motif M = "ababaca"
                    //                  a b c
                    findPattern = new Automata(motif, motif);
                    findPattern.output();
                } else {
                    // Afficher le nombre d'occurences du motif
                    // suivi de la liste des positions de sa 1ere lettre dans le texte
	                findPattern = new Automata(exploredText, motif);
	                findPattern.findPattern();
                    System.out.println(findPattern);
                }
                break;
            case 3: //Knut-Morris-Pratt
                if (fileName == null) {
                    //Afficher le tableau des prefixes
                    // P. ex. pour le motif M = "ababaca"
                    //                  0 1 2 3 4 5 6           q
                    //                  a b a b a c a         M[q]
	                findPattern = new KMP(motif, motif);
	                findPattern.output();
                } else {
                    // Afficher le nombre d'occurences du motif
                    // suivi de la liste des positions de sa 1ere lettre dans le texte
	                findPattern = new KMP(exploredText, motif);
	                findPattern.findPattern();
                    System.out.println(findPattern);
                }
                break;
            case 4: //Boyer-Moore
                findPattern = new BoyerMoore(exploredText, motif);
                // Format de sortie -> à générer avec votre code
                if (fileName == null) {
                    //Afficher les deux tableaux des decalages
                    // P. ex. pour le motif M = "anpanman"
                    // 1er tableau
                    //                  a n p m *       lettre (selon ordre dans le motif)
                    // 2eme tableau
                    // partie du motif bonne (depuis la droite):
                    //            n an man nman anman panman npanman anpanman
                    // decalage:  8  3  6    6    6      6      6       6
                    findPattern.output();
                } else {
                    // Afficher le nombre d'occurences du motif
                    // suivi de la liste des positions de sa 1ere lettre dans le texte
                    findPattern.findPattern();
                    System.out.println(findPattern);
                }
                break;
            default:
                System.err.println("Algorithm not implemented");
                System.exit(2);
        }
    }
}
