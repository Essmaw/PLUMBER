package Vue;

import java.awt.GridLayout;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Plateau extends JPanel{
	
	public Plateau() {
		this.setLayout(new GridLayout(3,3));
		for(int i = 0; i<3; i++) {
			for(int j =0; j<3; j++) {
				this.add(new Case(60, 60, 0, 0, 0));
			}
		}
	}
	
	
	public static void main(String[] argv) {
		//creation fenetre principale
		JFrame frame = new JFrame("Plumber");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//ajout de la fenetre d'une instance de Jimp
		Plateau plateau = new Plateau();
		frame.getContentPane().add(plateau);
		
		
		//ajustement de la fenetre aux dimensions de son panneau interne
		frame.pack();
		frame.setVisible(true);
	}
}
