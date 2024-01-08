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
	private String niveau;
	private JFrame frame;
	
	public VuePlateau(String ficNiveau, JFrame frame) {
		this.frame = frame;
		this.niveau = ficNiveau;
		this.plateau = new JPanel();
		this.setBackground(Color.black);
		
		this.setPreferredSize(new Dimension(1060, 900));
		JPanel barre = creerBarre();

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0;
		gbc.gridy = 0; // Changer gridy à 0 pour placer le label en haut
		this.add(barre, gbc);

		// Ajouter un espace vertical (par exemple, un espace de 10 pixels) entre le label et le plateau
		gbc.gridy = 1;
		//gbc.insets = new Insets(10, 0, 0, 0);
		JPanel espace = new JPanel();
		espace.setBackground(Color.black);
		espace.setPreferredSize(new Dimension(1060, (900-30-plateau.getHeight())/4));
		this.add(espace, gbc);

		// Ajouter le plateau au milieu
		gbc.gridy = 1;
		this.add(plateau, gbc);
		
		this.controleur = new Controleur(this, this.plateau, ficNiveau, true);
		
	}
	
	public JPanel creerBarre() {
		
		JPanel barre = new JPanel();
		
		barre.setPreferredSize(new Dimension(1060, 30));
		
		barre.setLayout(new GridLayout(1, 3));
		
		JButton menu = new JButton("Menu");
		barre.add(menu);
		
		ActionListener l2 = new ActionListener() {
			public void actionPerformed (ActionEvent e) {
				// code de la reponse a une action.
				System.out.println("Menu");
				menu();
			}
		};
		menu.addActionListener(l2);
		
		
		JButton recommencer = new JButton("Recommencer");
		barre.add(recommencer);
		
		ActionListener l1 = new ActionListener() {
			public void actionPerformed (ActionEvent e) {
				// code de la reponse a une action.
				System.out.println("recommencer");
				recommencer();
			}
		};
		recommencer.addActionListener(l1);
		
		JButton solution = new JButton("Solution");
		barre.add(solution);
		
		ActionListener l3 = new ActionListener() {
			public void actionPerformed (ActionEvent e) {
				// code de la reponse a une action.
				System.out.println("Solution");
				solution();
			}
		};
		solution.addActionListener(l3);
		
		return barre;
	}
	
public JPanel creerBarreGagne() {
		
		JPanel barre = new JPanel();
		
		barre.setPreferredSize(new Dimension(1060, 30));
		
		barre.setLayout(new GridLayout(1, 3));
		
		JButton menu = new JButton("Menu");
		barre.add(menu);
		
		ActionListener l2 = new ActionListener() {
			public void actionPerformed (ActionEvent e) {
				// code de la reponse a une action.
				System.out.println("Menu");
				menu();
			}
		};
		menu.addActionListener(l2);
		
		
		JButton recommencer = new JButton("Recommencer");
		barre.add(recommencer);
		
		ActionListener l1 = new ActionListener() {
			public void actionPerformed (ActionEvent e) {
				// code de la reponse a une action.
				System.out.println("recommencer");
				recommencer();
			}
		};
		recommencer.addActionListener(l1);
		
		JButton suivant = new JButton("Niveau suivant");
		barre.add(suivant);
		
		ActionListener l3 = new ActionListener() {
			public void actionPerformed (ActionEvent e) {
				// code de la reponse a une action.
				System.out.println("Niveau suivant");
			}
		};
		suivant.addActionListener(l3);
		
		return barre;
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
	
	public void solution() {
		this.removeAll();
		this.revalidate();
		this.repaint();
		this.plateau = new JPanel();
		
		this.setBackground(Color.black);
		
		this.setPreferredSize(new Dimension(1060, 900));
		JPanel barre = creerBarre();

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0;
		gbc.gridy = 0; // Changer gridy à 0 pour placer le label en haut
		this.add(barre, gbc);

		// Ajouter un espace vertical (par exemple, un espace de 10 pixels) entre le label et le plateau
		gbc.gridy = 1;
		//gbc.insets = new Insets(10, 0, 0, 0);
		JPanel espace = new JPanel();
		espace.setBackground(Color.black);
		espace.setPreferredSize(new Dimension(1060, (900-30-plateau.getHeight())/4));
		this.add(espace, gbc);

		// Ajouter le plateau au milieu
		gbc.gridy = 1;
		this.add(plateau, gbc);
		
		this.controleur = new Controleur(this, this.plateau, niveau, false);
	}
	
	public void recommencer() {
		this.removeAll();
		this.revalidate();
		this.repaint();
		this.plateau = new JPanel();
		
		this.setBackground(Color.black);
		
		this.setPreferredSize(new Dimension(1060, 900));
		JPanel barre = creerBarre();

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0;
		gbc.gridy = 0; // Changer gridy à 0 pour placer le label en haut
		this.add(barre, gbc);

		// Ajouter un espace vertical (par exemple, un espace de 10 pixels) entre le label et le plateau
		gbc.gridy = 1;
		//gbc.insets = new Insets(10, 0, 0, 0);
		JPanel espace = new JPanel();
		espace.setBackground(Color.black);
		espace.setPreferredSize(new Dimension(1060, (900-30-plateau.getHeight())/4));
		this.add(espace, gbc);

		// Ajouter le plateau au milieu
		gbc.gridy = 1;
		this.add(plateau, gbc);
		
		this.controleur = new Controleur(this, this.plateau, niveau, true);
	}
	
	
	public static void main(String[] argv) {
		//creation fenetre principale
		JFrame frame = new JFrame("Plumber");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Création et ajout du menu à la fenêtre
	    Menu menu = new Menu(frame);
	    frame.getContentPane().add(menu);
		
		//ajustement de la fenetre aux dimensions de son panneau interne
		frame.pack();
		frame.setVisible(true);
	}
}