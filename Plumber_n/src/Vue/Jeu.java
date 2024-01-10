package Vue;

import javax.swing.JFrame;

public class Jeu extends JFrame{
	
	private boolean[] niveauxGagnes = new boolean[10];
	
	public Jeu() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Création et ajout du menu à la fenêtre
	    Menu menu = new Menu(this);
	    this.getContentPane().add(menu);
		
		//ajustement de la fenetre aux dimensions de son panneau interne
		this.pack();
		this.setVisible(true);
	}
	
	public boolean[] getNiveauxGagnes() {
		return this.niveauxGagnes;
	}
	
	public static void main(String[] argv) {
		Jeu jeu = new Jeu();
	}

}
