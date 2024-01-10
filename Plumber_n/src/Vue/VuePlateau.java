package Vue;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Controleur.Controleur;

public class VuePlateau extends JPanel{
	
	private JPanel plateau;
	private Controleur controleur;
	private String ficNiveau;
	private int niveau;
	private Jeu frame;
	
	public VuePlateau(int niveau, String ficNiveau, Jeu frame) {
		this.niveau = niveau;
		this.frame = frame;
		this.ficNiveau = ficNiveau;
		this.afficher();
		
		this.controleur = new Controleur(this, this.plateau, ficNiveau, true, this.frame);
		
	}
	
	public JPanel creerBarre() {
		
		JPanel barre = new JPanel();
		
		barre.setPreferredSize(new Dimension(1060, 30));
		
		barre.setLayout(new GridLayout(1, 3));
		
		this.creerBoutonMenu(barre);
		
		this.creerBoutonRetour(barre);
		
		this.creerBoutonRecommencer(barre);

		this.creerBoutonSolution(barre);
		
		return barre;
	}
	
	public JPanel creerBarreGagne() {
		int nbMaxNiveaux = 10;
		JPanel barre = new JPanel();
		
		barre.setPreferredSize(new Dimension(1060, 30));
		
		if(this.niveau == nbMaxNiveaux) { //nombre max de niveau
			barre.setLayout(new GridLayout(1, 2));
		}
		else barre.setLayout(new GridLayout(1, 3));
		
		this.creerBoutonMenu(barre);
		
		this.creerBoutonRecommencer(barre);
		
		if(this.niveau < nbMaxNiveaux) { // 9 = nombre max de niveau
			this.creerBoutonSuivant(barre);
		}
		
		return barre;
	}
	
	public void creerBoutonMenu(JPanel barre) {
		JButton menu = new JButton("Menu");
		menu.setFont(new Font("Arial", Font.PLAIN, 20));
		barre.add(menu);
		
		ActionListener l2 = new ActionListener() {
			public void actionPerformed (ActionEvent e) {
				// code de la reponse a une action.
				menu();
			}
		};
		menu.addActionListener(l2);
	}
	
	public void creerBoutonRecommencer(JPanel barre) {
		JButton recommencer = new JButton("Recommencer");
		recommencer.setFont(new Font("Arial", Font.PLAIN, 20));
		barre.add(recommencer);
		
		ActionListener l1 = new ActionListener() {
			public void actionPerformed (ActionEvent e) {
				// code de la reponse a une action.
				recommencer();
			}
		};
		recommencer.addActionListener(l1);
	}
	
	public void creerBoutonSuivant(JPanel barre) {
		JButton suivant = new JButton("Niveau suivant");
		suivant.setFont(new Font("Arial", Font.PLAIN, 20));
		barre.add(suivant);
		
		ActionListener l3 = new ActionListener() {
			public void actionPerformed (ActionEvent e) {
				// code de la reponse a une action.
				suivant();
			}
		};
		suivant.addActionListener(l3);
	}
	
	public void creerBoutonRetour(JPanel barre) {
		//bouton retour 
		JButton retour = new JButton("Retour");
		retour.setFont(new Font("Arial", Font.PLAIN, 20));
		barre.add(retour);
		
		ActionListener l4 = new ActionListener() {
			public void actionPerformed (ActionEvent e) {
				// code de la reponse a une action.
				retour();
			}
		};
		retour.addActionListener(l4);
	}
	
	public void creerBoutonSolution(JPanel barre) {
		JButton solution = new JButton("Solution");
		solution.setFont(new Font("Arial", Font.PLAIN, 20));
		barre.add(solution);
		
		ActionListener l3 = new ActionListener() {
			public void actionPerformed (ActionEvent e) {
				// code de la reponse a une action.
				solution();
			}
		};
		solution.addActionListener(l3);
	}
	
	public void menu() {
		this.removeAll();
		this.revalidate();
		this.repaint();
		
		this.frame.getContentPane().removeAll();
    	this.frame.getContentPane().revalidate();
    	this.frame.getContentPane().repaint();
		
    	Menu menu = new Menu(frame);
	    frame.getContentPane().add(menu);
	}
	
	public void afficher() {
		this.plateau = new JPanel();
		this.removeAll();
		this.revalidate();
		this.repaint();
		
		this.setBackground(Color.black);
		
		this.setPreferredSize(new Dimension(1060, 900));
		JPanel barre = creerBarre();

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0;
		gbc.gridy = 0; // Changer gridy à 0 pour placer le label en haut
		this.add(barre, gbc);
        
		gbc.gridy = 1;
	    JPanel espaceBarreNiveau = new JPanel();
	    espaceBarreNiveau.setBackground(Color.black);
	    espaceBarreNiveau.setPreferredSize(new Dimension(1060, 60));
	    this.add(espaceBarreNiveau, gbc);
	    
		gbc.gridy = 2;
        JLabel niveauLabel = new JLabel("Niveau " + niveau);
        Font font = new Font("Helvetica", Font.BOLD, 30);
        niveauLabel.setForeground(Color.WHITE);
        niveauLabel.setFont(font);
        niveauLabel.setHorizontalAlignment(JLabel.CENTER);
        this.add(niveauLabel, gbc);
        
        //inserer espace
		gbc.gridy = 1;
		JPanel espace = new JPanel();
		espace.setBackground(Color.black);
		espace.setPreferredSize(new Dimension(1060, (900-30-plateau.getHeight())/8));
		this.add(espace, gbc);

		// Ajouter le plateau au milieu
		gbc.gridy = 1;
		this.add(plateau, gbc);
	}
	
	public void retour() {
		int r = this.controleur.retour();
		
		if(r == 1) {
			this.afficher();
			this.controleur = new Controleur(this, this.plateau, ficNiveau, true, this.controleur, this.frame);
		}
	}
	
	public void solution() {
		this.afficher();
		this.controleur = new Controleur(this, this.plateau, ficNiveau, false, this.frame);
	}
	
	public void recommencer() {
		this.afficher();
		this.controleur = new Controleur(this, this.plateau, ficNiveau, true, this.frame);
	}
	
	public void suivant() {
		this.afficher();
		
		this.niveau+=1;
		this.ficNiveau = "Fichier/pipe" + niveau + ".p";
		this.controleur = new Controleur(this, this.plateau, this.ficNiveau, true, this.frame);
		
	}
	
	public int getNiveau() {
		return this.niveau;
	}
	
}
