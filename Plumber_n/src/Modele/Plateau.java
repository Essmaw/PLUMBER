package Modele;

import java.util.Random;

public class Plateau {
	// ATTRIBUTS
	Case[][] plateau;
	int largeur;
	int hauteur;
		
	// FONCTIONS 
	//Attention verifier que pas de fuite aussi
	boolean est_gagnant() {
		for(int i=0; i < largeur; i++) {
			for(int j=0; j < hauteur; j++) { 
				if(this.plateau[i][j].est_allume == false) return false;
			}
		}
		return true;
	}
	
	//Allumage intial à partir de la source
	void initSource() {
		Case source = plateau[hauteur/2][largeur/2]; //on recupere la case contenant le tuyau source
		source.setAllumer(true);
		for(Dir dir : Dir.values()) {
			if(source.tuyau.estOuvert(dir, Dir.values()[source.orientation])){
				int xVoisin = hauteur/2 + Dir.di[dir.ordinal()];
				int yVoisin = largeur/2 + Dir.dj[dir.ordinal()];
				//regarder si case dans le plateau
				if(xVoisin > 0 && xVoisin < hauteur && yVoisin > 0 && yVoisin < largeur) {
					Case voisine = plateau[xVoisin][yVoisin]; //recupere case voisine
					//regarder si case pas allumee et source connecte avec case voisine dans direction dir
					if(! voisine.est_allume && source.estConnecte(voisine, dir)) {
						propagation(xVoisin, yVoisin, dir); //propager le signal
					}
				}
			}
		}
	}
	
	//enlever la direction Dir dirSortie et appeler directement sur les coordonnees voisine pour eviter repetition de code
	void propagation(int x, int y, Dir dirSortie) { 
		Case courante = plateau[x][y]; //recupere case voisine
		courante.setAllumer(true); // on allume la case courante car appel a propagation que si pas allume
		for(Dir dir : Dir.values()) {
			if(courante.tuyau.estOuvert(dir, Dir.values()[courante.orientation])){
				int xVoisin = x + Dir.di[dir.ordinal()];
				int yVoisin = y + Dir.dj[dir.ordinal()];
				//regarder si case dans le plateau
				if(xVoisin > 0 && xVoisin < hauteur && yVoisin > 0 && yVoisin < largeur) {
					Case voisine = plateau[xVoisin][yVoisin]; //recupere case voisine
					//regarder si case pas allumee et source connecte avec case voisine dans direction dir
					if(! voisine.est_allume && courante.estConnecte(voisine, dir)) {
						propagation(xVoisin, yVoisin, dir); //propager le signal
					}
				}
			}
		}
	}

	void rotation(int x, int y) {
		this.plateau[x][y].orientation = (this.plateau[x][y].orientation + 1)%4;
		//eteindre toutes les cases 
		//et on recalcul avec propagation
	}
	
	//il faut aussi éteindre si plus connecte
	void allumer() {
		// pareil
	}
	
	boolean est_connecte(int x1, int y1, int x2, int y2) {return false;}
	
	void rotation_aleatoire() {
		Random r = new Random();
		for(Case[] ligne : this.plateau) {
			for(Case c : ligne) {
				int rot = r.nextInt(4);
				//on tire aléatoirement la nouvelle rotation tant qu'elle
				//égale à la rotation initale
				while(rot == c.orientation) { 
					rot = r.nextInt(4);
				}
				c.orientation = rot;
			}
		}
	}
	
	//affiche le plateau
	public String toString() {
		String result = this.hauteur+" "+this.largeur+"\n";
		for(int i=0; i<this.hauteur; i++) {
			for(int j=0; j<this.largeur; j++) {
				result += Tuyau.tuyauToString((this.plateau[i][j]).tuyau);
				result += (this.plateau[i][j]).orientation;
				if(this.plateau[i][j].est_allume) {
					result += "* ";
				}
				else result += "- ";
			}
			result += "\n";
		}
		return result;
	}
	
	public static void main(String[] arg) {
		//initialisation du plateau
		Plateau p = new Plateau();
		
		//!\ bien choisir le workspace pour rendre le chemin vers les fic de niveau universel
		p.plateau = Parser.lire("Fichier/pipe1.p");
		p.hauteur = p.plateau.length;
		p.largeur = p.plateau[0].length;
		System.out.println(p);
		
		//rotation aléatoire 
		p.rotation_aleatoire();
		System.out.println(p);
		
		//rotation d'un tuyau
		p.rotation(0, 0);
		p.rotation(3, 1);
		p.rotation(2, 2);
		System.out.println(p);
		
	}
	
}
