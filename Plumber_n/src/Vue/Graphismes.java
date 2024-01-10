package Vue;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Modele.Tuyau;

public class Graphismes extends JPanel {
    private final int width;
    private final int height;
    
    // couleur de fond d'une tuile
    static final Color tileBackground = Color.black;
	
    /* banque de sprites :
     *   pour chaque ligne de l'image de base,
     *   pour chaque colonne de cette image,
     *   4 versions de la sous-image à cette ligne/colonne :
     *   la sous-image, tournée de 0, 1, 2, 3 quarts de tour.
     */
    private final int size;
    private final BufferedImage[][][] sprites;

    public Graphismes(int width, int height, String fileName, int lines, int columns, int size, int offset) {
		this.width = width;
		this.height = height;
		this.size = size;
		
		// réglage des dimensions du panneau
		this.setPreferredSize(new Dimension(width, height));
		/* lecture de l'image de base des sprites. le fichier .png
		 * doit être a la racine du projet (import -> File system).
		 */ 
		BufferedImage base = null;
		try {
		    base = ImageIO.read(new File(fileName));
		} catch (IOException e) {
		    e.printStackTrace();
		    System.exit(1);
		}
		// répartition des sous-images dans la banque
		sprites = new BufferedImage[lines][columns][4];
		for (int i = 0; i < lines; i++) {
		    for (int j = 0; j < columns; j++) {
				// subImg : sous-image à recopier en quatre versions
				BufferedImage subImg = base.getSubimage((size + offset) * j, (size + offset) * i, size, size);
				for (int r = 0; r < 4; r++) {
				    // création d'une rotation de r quarts de tour autour du centre de la sous-image.
				    AffineTransform t = new AffineTransform();
				    t.setToQuadrantRotation(r, size/2, size/2);
				    // création de l'image cible
				    sprites[i][j][r] = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);
				    // g2 : point d'accès à ses pixels.
				    Graphics2D g2 = (Graphics2D) sprites[i][j][r].getGraphics();
				    // copie de la sous-image dans l'image cible, en lui
				    // faisant subir la rotation t.
				    g2.drawImage(subImg, t, null);		    
				}
		    }
		}
		/* demande de rafraîchissement du panneau, qui invoquera la
		 * paintComponent en lui passant comme argument un point d'accès
		 * aux pixels de la fenêtre.
		 */
		repaint();
    }
    
    public BufferedImage getTuyau(int ligne, int colonne, int rotation){
    	return this.sprites[ligne][colonne][rotation];
    }
    
    public static int TuyauToColonne(char tuyau) {
    	int col;
		switch(tuyau) {
			case 'E' : col = 0; break;
			case 'L' : col = 1; break;
			case 'T' : col = 2;	break;
			case 'F' : col = 3; break;
			case 'C' : col = 4; break;
			default : col = -1;
		}
		return col;
    }
}