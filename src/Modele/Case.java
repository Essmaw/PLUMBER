package Modele;

public class Case {
	// ATRIBUTS 
	protected Tuyau tuyau;
	protected int orientation; // nombre de rotation réalisée sur la config initiale du tuyau.
	protected boolean est_allume = false;
	
	// CONSTRUCTEUR
	Case(Tuyau tuyau, int orientation){
		this.tuyau = tuyau;
		this.orientation = orientation ;
	}
		
	// FONCTIONS
	void rotation() {this.orientation= (this.orientation + 1) % 4;}
	
	void allumer() {this.est_allume = true;}
	
}
