package Vue;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;

public class Case extends JPanel implements MouseInputListener{
	private final int width;
	private final int height;
	private BufferedImage image;
	private int colonne;
	private int ligne;
	private int rotation;
	private Graphismes graph;
	private Graphics context_graph; //obligatoire?
	
	Case(int w, int h, int ligne, int col, int rotation){
		this.width = w;
		this.height = h;
		this.graph = new Graphismes(w, h, "pipes.png", 3, 5, 120, 20);
		//reglage des dimensions du panneau
		this.setPreferredSize(new Dimension(w, h));
		
		this.ligne = ligne;
		this.colonne = col;
		this.rotation = rotation;
		
		addMouseListener(this);
		
		this.image = graph.getTuyau(ligne, col, rotation);
		
		this.context_graph = this.image.getGraphics();//obligatoire?
		
		//rafraichis du panneau. Invoque la fonction paintCompoétant invoquée au premier affchage de la fenêtre, une image entièrement noire devrait apparaître dans celle-ci.nent
		repaint();
	}
	
	//necessaire?
	public Graphics getImageGraphics() {
		return this.context_graph;
	}
	
	public void effacer() {
		this.context_graph.clearRect(0, 0, this.width, this.height);
		repaint();
	}
	
	public void setImage(BufferedImage image) {
		this.image = image;
		repaint();
	}
	
	protected void paintComponent(Graphics g) {
		g.clearRect(0, 0, this.width, this.height);
		g.drawImage(this.image, 0, 0, this.width, this.height, this);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		this.context_graph.setColor(Color.BLACK);
		this.rotation = (this.rotation + 1)%4;
		this.image = graph.getTuyau(ligne, colonne, rotation);
		repaint();
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
