package Vue;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Controleur.Controleur;

public class VuePlateau extends JPanel{
	
	private JPanel plateau;
	private Controleur controleur;
	
	public VuePlateau(String ficNiveau) {
		
		this.plateau = new JPanel();
		this.setBackground(Color.black);
		this.setLayout(new GridBagLayout());
		
		GridBagConstraints gbc1 = new GridBagConstraints();
        gbc1.gridx = 1;
        gbc1.gridy = 1;
        this.add(plateau, gbc1);
        //this.add(plateau);
		this.setPreferredSize(new Dimension(1060, 800));
		this.controleur = new Controleur(this, this.plateau, ficNiveau);
		
	}
	
	
	public static void main(String[] argv) {
		//creation fenetre principale
		JFrame frame = new JFrame("Plumber");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//ajout de la fenetre d'une instance de Jimp
		VuePlateau plateau = new VuePlateau("Fichier/pipe1.p");
		frame.getContentPane().add(plateau);
		
		
		//ajustement de la fenetre aux dimensions de son panneau interne
		frame.pack();
		frame.setVisible(true);
	}
}
