package Vue;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class VueNiveaux extends JPanel {
	
	private JFrame frame;
	
    public VueNiveaux(JFrame frame) {
        this.frame = frame;
        initUI();
    }

    private void initUI() {
        setLayout(new GridLayout(2, 5, 10, 10)); // 2 lignes, 5 colonnes avec un espacement de 10 pixels
        setBackground(Color.BLACK); // Fond noir

        for (int i = 0; i <= 9; i++) {
            JButton niveauButton = new JButton("Niveau " + i);
            niveauButton.setFont(new Font("Arial", Font.PLAIN, 14));
            niveauButton.setForeground(Color.WHITE);
            niveauButton.setBackground(Color.DARK_GRAY);
            niveauButton.addActionListener(new NiveauButtonListener(i, frame));
            add(niveauButton);
        }
    }

    // ActionListener pour les boutons de niveau
    private class NiveauButtonListener implements ActionListener {
        private int niveau;
        private JFrame frame;

        public NiveauButtonListener(int niveau, JFrame frame) {
        	this.frame = frame;
            this.niveau = niveau;
        }

        public void actionPerformed(ActionEvent e) {
        	//cration du plateau avec le fichier de niveau
        	
        	// référence au JPanel menu
        	JPanel menu = (JPanel) frame.getContentPane().getComponent(0);

        	// référence au JPanel niveaux
        	JPanel niveaux = (JPanel) menu.getComponent(0);

        	// Supprimer les boutons du JPanel niveaux
        	for (int i = 0; i <= 9; i++) {
        	    if (niveaux.getComponentCount() > 0) {
        	        niveaux.remove(0);
        	    }
        	}

        	// JPanel niveaux est mis à jour après la suppression
        	niveaux.revalidate();
        	niveaux.repaint();

        	// Supprimer tous les composants du JPanel niveaux
        	niveaux.removeAll();

        	// JPanel niveaux est mis à jour après la suppression
        	niveaux.revalidate();
        	niveaux.repaint();

        	// Supprimer tous les composants du JPanel menu
        	menu.removeAll();

        	// JPanel menu est mis à jour après la suppression
        	menu.revalidate();
        	menu.repaint();

        	// Supprimer tous les composants du contentPane du JFrame
        	frame.getContentPane().removeAll();

        	// le contentPane du JFrame est mis à jour après la suppression
        	frame.getContentPane().revalidate();
        	frame.getContentPane().repaint();

        	// Ajouter un nouveau composant VuePlateau au contentPane
        	System.out.println("Fichier/pipe" + niveau + ".p");
        	VuePlateau plateau = new VuePlateau("Fichier/pipe" + niveau + ".p", this.frame);
        	frame.getContentPane().add(plateau);

        	//le contentPane du JFrame est mis à jour après l'ajout
        	frame.getContentPane().revalidate();
        	frame.getContentPane().repaint();
        }
    }
}
