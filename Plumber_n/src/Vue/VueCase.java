package Vue;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;

import Controleur.Controleur;

public class VueCase extends JPanel implements MouseInputListener{
	private int posX;//position sur le plateau
	private int posY;//position sur le plateau
	private final int width;
	private final int height;
	private BufferedImage image;
	private int colonne;
	private int ligne;
	private int rotation;
	private Graphismes graph;
	private Graphics context_graph;
	private Controleur controleur;
	
	public VueCase(int w, int h, int ligne, int col, int rotation, int posX, int posY, Controleur c){
		this.controleur = c;
		this.posX = posX;
		this.posY = posY;
		this.width = w;
		this.height = h;
		this.graph = new Graphismes(w, h, "pipes.png", 3, 5, 120, 20); //3 lignes et 5 colonnes dans l'image pipes.png
		//reglage des dimensions du panneau
		this.setPreferredSize(new Dimension(w, h));
		
		this.setBackground(Color.black);
		
		this.ligne = ligne;
		this.colonne = col;
		this.rotation = rotation;
		
		addMouseListener(this);
		
		this.image = graph.getTuyau(ligne, col, rotation);
		
		this.context_graph = this.image.getGraphics();//obligatoire?
		
		//rafraichis du panneau. Invoque la fonction paintCompoétant invoquée au premier affchage de la fenêtre, une image entièrement noire devrait apparaître dans celle-ci.nent
		repaint();
	}
	
	public int getPosX() {
		return this.posX;
	}
	
	public Graphics getImageGraphics() {
		return this.context_graph;
	}
	
	protected void paintComponent(Graphics g) {
		g.clearRect(0, 0, this.width, this.height);
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, this.width, this.height);
		if(this.posX != -1) g.drawImage(this.graph.getTuyau(2, 0, 0), 0, 0, this.width, this.height, this);
		g.drawImage(this.image, 0, 0, this.width, this.height, this);
	}
	
	public void miseAJour() {
		if(this.controleur.estAllume(this.posX, this.posY)) this.ligne = 1;
		else this.ligne = 0;
		this.image = graph.getTuyau(ligne, colonne, rotation);
		repaint();
	}
	
	public void rendreNonCliquable() {
        // Désenregistrer le JPanel en tant que listener des événements de souris
        removeMouseListener(getMouseListeners()[0]);
    }

	@Override
	public void mouseClicked(MouseEvent e) {
		if(this.posX != -1) {
			this.context_graph.setColor(Color.BLACK);
			this.rotation = (this.rotation + 1)%4;
			this.controleur.rotation(this.posX, this.posY);
			if(this.controleur.estAllume(this.posX, this.posY)) this.ligne = 1;
			else this.ligne = 0;
			this.image = graph.getTuyau(ligne, colonne, rotation);
			this.controleur.propagation();
			repaint();
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
