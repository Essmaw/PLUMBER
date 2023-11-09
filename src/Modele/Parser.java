package Modele;

import java.io.File;
import java.util.Scanner;

public class Parser {
	static Case[][] lire(String nom_fichier){
		File f = null; 
		try {
			f = new File(nom_fichier);
			Scanner sc = new Scanner(f);
			int hauteur = sc.nextInt();
			int largeur = sc.nextInt();
			
			Case[][] matrice = new Case[hauteur][largeur];
			for(int i=0; i<hauteur; i++) {
				for(int j =0; j<largeur; j++) {
					
				}
				
			}
			
		}
		catch(Exception e){
			System.exit(1);		
		}
		return null;
	}
}
