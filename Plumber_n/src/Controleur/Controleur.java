package Controleur;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Modele.Parser;
import Modele.Plateau;
import Modele.Case;
import Modele.Tuyau;
import Vue.Graphismes;
import Vue.VueCase;
import Vue.VuePlateau;

public class Controleur {
	
	private VuePlateau vue;
	private JPanel vuePlateau;
	private Plateau modelePlateau;
	
	public Controleur(VuePlateau vue, JPanel vuePlateau, String ficNiveau) {
		this.vue = vue;
		this.vuePlateau = vuePlateau;
		this.modelePlateau = new Plateau(Parser.lire("Fichier/pipe1.p"));
		
		this.vuePlateau.setLayout(new GridLayout(this.modelePlateau.getHauteur(), this.modelePlateau.getLargeur()));
		for(int i = 0; i<this.modelePlateau.getHauteur(); i++) {
			for(int j =0; j<this.modelePlateau.getLargeur(); j++) {
				Case c = this.modelePlateau.getPlateau()[i][j];
				char tuyau = Tuyau.tuyauToString(c.getTuyau()).charAt(0);
				int rotation = c.getOrientation();
				int ligne = 0;
				if(c.getEstAllume()) ligne = 1;
				this.vuePlateau.add(new VueCase(80, 80, ligne, Graphismes.TuyauToColonne(tuyau), rotation, i, j, this));
			}
		}
	}
	
	public boolean estGagnant() {
		return this.modelePlateau.estGagnant();
	}
	
	//rotate une tuile du plateau du modele (appelee lorsqu'on clique sur une case de la Vue (classe VueCase))
	public void rotation(int i, int j) {
		this.modelePlateau.rotation(i, j);
		this.vue.setBackground(Color.black);
		if(this.estGagnant()) {
			this.vue.setBackground(Color.pink);
			JLabel titre = new JLabel("BRAVO ! VOUS AVEZ GAGNÉ !!!!!");
			titre.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			titre.setHorizontalAlignment(JLabel.CENTER);
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.fill = GridBagConstraints.HORIZONTAL;
	        gbc.gridx = 0;
	        gbc.gridy = 1;
	        this.vue.add(titre, gbc);
		}
	}
	
	//renvoie true si la case est allumee
	public boolean estAllume(int i, int j) {
		return this.modelePlateau.getPlateau()[i][j].getEstAllume();
	}
	
	public void propagation() {
		Component[] components = this.vuePlateau.getComponents();
        for (int i = 0; i < components.length; i++) {
            VueCase c = ((VueCase)components[i]);
            c.miseAJour();
        }
	}
}
