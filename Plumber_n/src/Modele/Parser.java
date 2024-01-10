package Modele;

import java.io.File;
import java.util.Scanner;

public class Parser {
	
	public static Case[][] lire(String nom_fichier){
		File f = null; 
		try {
			f = new File(nom_fichier);
			Scanner sc = new Scanner(f);
			int hauteur = sc.nextInt();
			int largeur = sc.nextInt();
			Case[][] matrice = new Case[hauteur][largeur];
			int pos_ligne = 0;
			for(int i=0; i<hauteur; i++) {
				for(int j=0; j<largeur; j++) {
					if(! sc.hasNext()) {
						System.out.println("Le fichier n'a pas le bon format.");
						System.exit(1);
					}
					String entree = sc.next();
					Case nvl_case;
					if(entree.charAt(1) == '.') {
						//alors case vide, ne contient pas de tuyau
						nvl_case = new Case();
					}
					else {
						Tuyau  t = Tuyau.stringToTuyau(entree.charAt(0));
						int rotation = entree.charAt(1)-'0';
						if( rotation > 3 || rotation < 0) {
							System.out.println("Le fichier n'a pas le bon format.");
							System.exit(1);
						}
						nvl_case = new Case(t, rotation);
					}
					matrice[i][j] = nvl_case;
				}
			}
			return matrice;
		}
		catch(Exception e){
			System.out.println("Le fichier n'a pas été ouvert.");
			System.exit(1);		
		}
		return null;
	}
}
