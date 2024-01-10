package Modele;

public class Case {
	// ATRIBUTS 
	protected Tuyau tuyau;
	protected int orientation; // nombre de rotation réalisée sur la config initiale du tuyau.
	protected boolean estAllume = false;
	protected boolean estVide = false; //par defaut une case n'est pas vide, elle contient un tuyau
	
	// CONSTRUCTEUR
	Case(){
		this.estVide = true;
	}
	
	Case(Tuyau tuyau, int orientation){
		this.tuyau = tuyau;
		this.orientation = orientation ;
	}
	
	// ACCESSEURS
	public boolean getEstAllume() {
		return this.estAllume;
	}
	
	public boolean getEstVide() {
		return this.estVide;
	}
	
	public Tuyau getTuyau() {
		return this.tuyau;
	}
	
	public int getOrientation() {
		return this.orientation;
	}
		
	// FONCTIONS
	void rotation() {this.orientation= (this.orientation + 1) % 4;}
	
	void setAllumer(boolean allumer) {this.estAllume = allumer;}
	
	boolean estConnecte(Case voisine, Dir dir) {
		Dir orientationCaseCourante = Dir.values()[this.orientation];
		Dir orientationCaseVosine = Dir.values()[voisine.orientation];
		//On regarde si la case courante est ouverte dans la direction dir et que case voisine est ouverte dans la direction opposee de dir
		if(this.tuyau.estOuvert(dir, orientationCaseCourante) && voisine.tuyau.estOuvert(dir, orientationCaseVosine.oppose())) {
			return true; //Alors cases connectees
		}
		return false; //Sinon les cases ne sont pas connectees
	}
	
}
