package Controleur;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

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
	
	public Controleur(VuePlateau vue, JPanel vuePlateau, String ficNiveau, boolean aleatoire) {
		this.vue = vue;
		this.vuePlateau = vuePlateau;
		this.modelePlateau = new Plateau(Parser.lire(ficNiveau), aleatoire);
		
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
			this.vue.removeAll();
			this.vue.revalidate();
			this.vue.repaint();
			
			JPanel barre = this.vue.creerBarreGagne();

			GridBagConstraints gbc = new GridBagConstraints();
			gbc.fill = GridBagConstraints.HORIZONTAL;
			gbc.gridx = 0;
			gbc.gridy = 0; // Changer gridy à 0 pour placer le label en haut
			this.vue.add(barre, gbc);
			
			JLabel titre = new JLabel("VOUS AVEZ GAGNÉ !!!");
			Font font = new Font("Helvetica", Font.BOLD, 19); 
			titre.setForeground(Color.WHITE);
			titre.setFont(font);
			titre.setHorizontalAlignment(JLabel.CENTER);
			
			gbc.fill = GridBagConstraints.HORIZONTAL;
			gbc.gridy = 1;
			this.vue.add(titre, gbc);
			
			// Ajouter un espace vertical (par exemple, un espace de 10 pixels) entre le label et le plateau
			gbc.gridy = 2;
			JPanel espace = new JPanel();
			espace.setBackground(Color.black);
			espace.setPreferredSize(new Dimension(1060, (900-30-titre.getHeight()-this.vuePlateau.getHeight())/4));
			this.vue.add(espace, gbc);

			// Ajouter le plateau au milieu
			gbc.gridy = 3;
			this.vue.add(this.vuePlateau, gbc);
			
			this.vue.revalidate();
			this.vue.repaint();
			
			//////////////////////////////////////////////
			
			SwingUtilities.invokeLater(() -> {
				for (int k = 0; k < this.modelePlateau.getHauteur()*this.modelePlateau.getLargeur(); k++) {
	        	    ((VueCase) this.vuePlateau.getComponent(k)).rendreNonCliquable();
	        	}
				
				this.vue.revalidate();
				this.vue.repaint();
			});
			
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
