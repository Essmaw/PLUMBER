package Modele;

import java.util.ArrayList;
import java.util.Random;

public class Plateau {
	// ATTRIBUTS
	Case[][] plateau;
	int largeur;
	int hauteur;
	ArrayList<int[]> sauvegarde = new ArrayList<>(); //sauvegarde des positions des cases rotates
	
	//CONSTRUCTEUR
	public Plateau() {}
	
	public Plateau(Case[][] plateau, boolean aleatoire) {
		this.plateau = plateau;
		this.hauteur = plateau.length;
		this.largeur = plateau[0].length;
		if(aleatoire) {
			this.rotation_aleatoire();
		}
		this.initSource();
	}
		
	//ACCESSEURS
	public Case[][] getPlateau(){
		return this.plateau;
	}
	
	public int getLargeur() {
		return this.largeur;
	}
	
	public int getHauteur() {
		return this.hauteur;
	}
	
	// FONCTIONS 
	
	public boolean estGagnant() {
		for(int i=0; i < hauteur; i++) {
			for(int j=0; j < largeur; j++) { 
				Case courante = plateau[i][j]; //recupere case voisine
				if(!courante.est_allume) return false;
				for(Dir dir : Dir.values()) {
					if(courante.tuyau.estOuvert(dir, Dir.values()[courante.orientation])){
						int iVoisin = i + Dir.di[dir.ordinal()];
						int jVoisin = j + Dir.dj[dir.ordinal()];
						//regarder si case dans le plateau
						if(iVoisin >= 0 && iVoisin < hauteur && jVoisin >= 0 && jVoisin < largeur) {
							Case voisine = plateau[iVoisin][jVoisin]; //recupere case voisine
							//regarder si case pas allumee et source connecte avec case voisine dans direction dir
							if(! courante.estConnecte(voisine, dir)) {
								return false; //fuite
							}
						}//si pas dans plateau alors fuite en dehors plateau
						else return false;
					}
				}
			}
		}
		return true;
	}
	
	//Allumage intial à partir de la source
	public void initSource() { 
		//on commence la propagation sur le tuyau source
		propagation(hauteur/2, largeur/2);
	}
	
	public void propagation(int i, int j) { 
		Case courante = plateau[i][j]; //recupere case voisine
		courante.setAllumer(true); // on allume la case courante car appel a propagation que si pas allume
		for(Dir dir : Dir.values()) {
			if(courante.tuyau.estOuvert(dir, Dir.values()[courante.orientation])){
				int iVoisin = i + Dir.di[dir.ordinal()];
				int jVoisin = j + Dir.dj[dir.ordinal()];
				//regarder si case dans le plateau
				if(iVoisin >= 0 && iVoisin < hauteur && jVoisin >= 0 && jVoisin < largeur) {
					Case voisine = plateau[iVoisin][jVoisin]; //recupere case voisine
					//regarder si case pas allumee et source connecte avec case voisine dans direction dir
					if(! voisine.est_allume && courante.estConnecte(voisine, dir)) {
						propagation(iVoisin, jVoisin); //propager le signal
					}
				}
			}
		}
	}

	public void rotation(int i, int j) {
		this.plateau[i][j].orientation = (this.plateau[i][j].orientation + 1)%4;
		//eteindre toutes les cases 
		for(Case[] ligne : plateau) {
			for(Case casePlateau : ligne) {
				casePlateau.setAllumer(false);
			}
		}
		//et on recalcule la propagation a partir de la source
		this.initSource();
		//on ajoute les positions de la cases dans sauvegarde
		int[] pos = {i, j};
		this.sauvegarde.add(pos);
	}
	
	public int[] retour() {
		int taille = this.sauvegarde.size();
		if(taille > 0) {
			int[] pos_prec = this.sauvegarde.get(taille-1);
			this.sauvegarde.remove(taille-1);
			
			//rotation dans le sens inverse
			this.plateau[pos_prec[0]][pos_prec[1]].orientation = (this.plateau[pos_prec[0]][pos_prec[1]].orientation - 1 + 4)%4;
			//eteindre toutes les cases 
			for(Case[] ligne : plateau) {
				for(Case casePlateau : ligne) {
					casePlateau.setAllumer(false);
				}
			}
			//et on recalcule la propagation a partir de la source
			this.initSource();
			return pos_prec;
		}
		return null;
	}
	
	public void rotation_aleatoire() {
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
		//p.plateau = Parser.lire("Fichier/pipe2.p"); //test config ou tout allume mais fuite
		p.hauteur = p.plateau.length;
		p.largeur = p.plateau[0].length;
		System.out.println(p);
		
		//rotation aléatoire 
		//p.rotation_aleatoire();
		//System.out.println(p);
		
		//propagation a partir de la source pour initaliser
		p.initSource();
		System.out.println(p);
		System.out.println(p.estGagnant());
		
		
		//rotation d'un tuyau
		//p.rotation(3, 1);
		//System.out.println(p);
		//System.out.println(p.est_gagnant());
		
	}
	
}
