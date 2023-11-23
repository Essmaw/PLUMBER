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
	
	
	void propagation() {
		// pas rempli parce que dunia...
	}

	void rotation(int x, int y) {
		this.plateau[x][y].orientation = (this.plateau[x][y].orientation + 1)%4;
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
		p.plateau = Parser.lire("/home/dounia/PLUMBER_Project/src/Fichier/pipe1.p");
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
