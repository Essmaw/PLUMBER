package Modele;

public class Plateau {
	// ATTRIBUTS
	Case[][] plateau;
	int largeur;
	int hauteur;
	
	// CONSTRUCTEUR
	Plateau(int largeur, int hauteur){
	}
	
	
	// FONCTIONS 
	boolean est_gagnant() {
		for(int i=0; i < largeur; i++) {
			for(int j=0; j < hauteur; j++) { 
				if(this.plateau[i][j].est_allume == false) return false;
			}
		}
		return true;
	}
	
	
	void propagation() {
		// pas rempli parce que dunia...
	}

	void rotation() {
		// pareil
	}
	
	void allumer() {
		// pareil
	}
	
	boolean est_connecte(int pos1, int pos2) {return false;}
	
	
	
	
	
}
