package Controleur;

import java.awt.Color;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Menu;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
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
		remplissagePlateau();
	}
	
	public void remplissagePlateau() {
		this.vuePlateau.setLayout(new GridLayout(this.modelePlateau.getHauteur()+2, this.modelePlateau.getLargeur()+2));
		
		this.vuePlateau.add(new VueCase(80, 80, 2, 1, 0, -1, -1, this)); //-1 valeur par defaut
		for(int i=0; i<this.modelePlateau.getLargeur(); i++) {
			this.vuePlateau.add(new VueCase(80, 80, 2, 2, 0, -1, -1, this)); //-1 valeur par defaut
		}
		this.vuePlateau.add(new VueCase(80, 80, 2, 1, 1, -1, -1, this));
		
		for(int i = 0; i<this.modelePlateau.getHauteur(); i++) {
			this.vuePlateau.add(new VueCase(80, 80, 2, 2, 3, -1, -1, this)); //-1 valeur par defaut
			for(int j =0; j<this.modelePlateau.getLargeur(); j++) {
				Case c = this.modelePlateau.getPlateau()[i][j];
				char tuyau = Tuyau.tuyauToString(c.getTuyau()).charAt(0);
				int rotation = c.getOrientation();
				int ligne = 0;
				if(c.getEstAllume()) ligne = 1;
				this.vuePlateau.add(new VueCase(80, 80, ligne, Graphismes.TuyauToColonne(tuyau), rotation, i, j, this));
			}
			this.vuePlateau.add(new VueCase(80, 80, 2, 2, 1, -1, -1, this));
		}
		
		this.vuePlateau.add(new VueCase(80, 80, 2, 1, 3, -1, -1, this)); //-1 valeur par defaut
		for(int i=0; i<this.modelePlateau.getLargeur(); i++) {
			this.vuePlateau.add(new VueCase(80, 80, 2, 2, 2, -1, -1, this)); //-1 valeur par defaut
		}
		this.vuePlateau.add(new VueCase(80, 80, 2, 1, 2, -1, -1, this));
	}
	
	public boolean estGagnant() {
		return this.modelePlateau.estGagnant();
	}
	
	//rotate une tuile du plateau du modele (appelee lorsqu'on clique sur une case de la Vue (classe VueCase))
	public void rotation(int i, int j) {
	    this.modelePlateau.rotation(i, j);
	    this.vue.setBackground(Color.black);
	    int niveau = vue.getNiveau();

	    if (this.estGagnant() && niveau != 10) {
	        afficherNiveauGagnant(niveau);
	    } else if (this.estGagnant() && niveau == 10) {
	        afficherTrophee();
	    }
	}
	
	
	
	public void afficherNiveauGagnant(int niveau) {
	    nettoyerVue();
	    
	    JPanel barre = this.vue.creerBarreGagne();
	    GridBagConstraints gbc = new GridBagConstraints();
	    gbc.fill = GridBagConstraints.HORIZONTAL;
	    gbc.gridx = 0;
	    gbc.gridy = 0; // Placer la barre en haut
	    this.vue.add(barre, gbc);

	    ajouterEspace(60, 1);

	    afficherNiveau(niveau);

	    // Ajouter un espace vertical entre le niveau et le plateau
	    ajouterEspace((900 - (30 + 60) - this.vuePlateau.getHeight()) / 8, 3);

	    ajouterPlateau();

	    // Ajouter un espace vertical entre le plateau et l'image
	    ajouterEspace(10, 5);

	    JLabel titre = creerLabel("VOUS AVEZ GAGNÉ !!!", 19, Color.WHITE, JLabel.CENTER);
	    ajouterComposant(titre, 6);

	    ajouterImage("medaille.png", 80, 80, 7);

	    mettreAJourVue();
	}

	
	public void afficherTrophee() {
	    nettoyerVue();

	    JPanel barre = this.vue.creerBarreGagne();
	    GridBagConstraints gbc = new GridBagConstraints();
	    gbc.fill = GridBagConstraints.HORIZONTAL;
	    gbc.gridx = 0;
	    gbc.gridy = 0; // Placer la barre en haut
	    this.vue.add(barre, gbc);

	    // Ajouter un espace vertical entre la barre et le trophée
	    ajouterEspace(60, 1);
	    
	    JLabel bravo = creerLabel("BRAVO VOUS AVEZ FINI LE JEU !", 25, Color.WHITE, JLabel.CENTER);
	    ajouterComposant(bravo, 2);
	    
	    ajouterEspace(80, 3);

	    ajouterImage("trophee.png", 350, 350, 4);
	    
	    ajouterEspace(80, 5);
	    

	    JLabel champion = creerLabel("VOUS ÊTES UN CHAMPION DU JEU PLUMBER !!!", 25, Color.WHITE, JLabel.CENTER);
	    ajouterComposant(champion, 6);

	    // Mettre à jour la vue
	    mettreAJourVue();
	}	
	
	
	private void nettoyerVue() {
	    this.vue.removeAll();
	    this.vue.revalidate();
	    this.vue.repaint();
	}

	private void ajouterEspace(int hauteur, int gridY) {
	    JPanel espace = creerEspaceVertical(hauteur);
	    GridBagConstraints gbc = new GridBagConstraints();
	    gbc.fill = GridBagConstraints.HORIZONTAL;
	    gbc.gridy = gridY;
	    this.vue.add(espace, gbc);
	}

	private JPanel creerEspaceVertical(int hauteur) {
	    JPanel espace = new JPanel();
	    espace.setBackground(Color.black);
	    espace.setPreferredSize(new Dimension(1060, hauteur));
	    return espace;
	}

	private void afficherNiveau(int niveau) {
	    JLabel niveauLabel = creerLabel("Niveau " + niveau, 30, Color.WHITE, JLabel.CENTER);
	    ajouterComposant(niveauLabel, 2);
	}

	private void ajouterPlateau() {
	    ajouterComposant(this.vuePlateau, 4);
	}

	private JLabel creerLabel(String texte, int taillePolice, Color couleur, int alignement) {
	    JLabel label = new JLabel(texte);
	    label.setFont(new Font("Helvetica", Font.BOLD, taillePolice));
	    label.setForeground(couleur);
	    label.setHorizontalAlignment(alignement);
	    return label;
	}

	private void ajouterComposant(Component composant, int gridY) {
	    GridBagConstraints gbc = new GridBagConstraints();
	    gbc.fill = GridBagConstraints.HORIZONTAL;
	    gbc.gridy = gridY;
	    this.vue.add(composant, gbc);
	}

	private void ajouterImage(String cheminImage, int largeur, int hauteur, int gridY) {
	    ImageIcon imageIcon = createImageIcon(cheminImage);
	    if (imageIcon != null) {
	        imageIcon = new ImageIcon(imageIcon.getImage().getScaledInstance(largeur, hauteur, Image.SCALE_DEFAULT));
	        JLabel imageLabel = new JLabel(imageIcon);
	        ajouterComposant(imageLabel, gridY);
	    }
	}

	private void mettreAJourVue() {
	    this.vue.revalidate();
	    this.vue.repaint();
	}
	
	//renvoie true si la case est allumee
	public boolean estAllume(int i, int j) {
		return this.modelePlateau.getPlateau()[i][j].getEstAllume();
	}
	
	public void propagation() {
		Component[] components = this.vuePlateau.getComponents();
        for (int i = 0; i < components.length; i++) {
            VueCase c = ((VueCase)components[i]);
            if(c.getPosX() !=-1) c.miseAJour();
        }
	}
	
	public ImageIcon createImageIcon(String path) {
        try {
            return new ImageIcon(ImageIO.read(new File(path)));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
