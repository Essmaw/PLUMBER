package Vue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.imageio.ImageIO;

import java.io.File;

public class Menu extends JPanel {

    private Jeu frame;

    public Menu(Jeu frame) {
    	this.frame = frame;
        this.setPreferredSize(new Dimension(1060, 900));
        initUI();
    }

    private void initUI() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        setBackground(Color.BLACK);

        JLabel title = new JLabel("Bienvenue au jeu Plumber !");
        title.setFont(new Font("Arial", Font.BOLD, 48));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setForeground(Color.WHITE);

        // Charger l'image du plombier
        ImageIcon plumberIcon = createImageIcon("plumber.png");
        plumberIcon = new ImageIcon(plumberIcon.getImage().getScaledInstance(400, 400, Image.SCALE_DEFAULT));
        JLabel plumberLabel = new JLabel(plumberIcon);

        // Centre l'image
        JPanel centerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        centerPanel.setBackground(Color.BLACK);
        centerPanel.add(plumberLabel);

        JLabel description = new JLabel("<html><center>Plumber est un jeu de puzzle dans lequel vous devez connecter les tuyaux pour former un chemin continu. Résolvez les niveaux en unissant tous les tuyaux pour gagner !<br>Bonne chance :) </center></html>");
        description.setFont(new Font("Times New Roman", Font.ITALIC, 20)); 
        description.setAlignmentX(Component.CENTER_ALIGNMENT);
        description.setForeground(Color.GRAY);
        
        add(Box.createRigidArea(new Dimension(0, 20)));
        add(title);
        add(Box.createRigidArea(new Dimension(0, 60))); // Add some space between title and image
        add(centerPanel);
        add(Box.createRigidArea(new Dimension(0, 10)));
        add(description);

        JButton startButton = new JButton("Commencer le jeu");
        startButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        startButton.setFont(new Font("Arial", Font.PLAIN, 23));
        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Lorsque le bouton "Commencer le jeu" est cliqué, appeler la méthode pour mettre à jour le contenu dans VuePlateau
            	showNiveauxPanel();}
        });

        JButton quitButton = new JButton("Quitter");
        quitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        quitButton.setFont(new Font("Arial", Font.PLAIN, 20));
        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        Box buttonBox = Box.createVerticalBox();
        buttonBox.add(Box.createVerticalGlue()); 
        buttonBox.add(startButton);
        buttonBox.add(Box.createRigidArea(new Dimension(0, 15)));
        buttonBox.add(quitButton);
        buttonBox.add(Box.createRigidArea(new Dimension(0, 20)));

        add(buttonBox);
    }

    public ImageIcon createImageIcon(String path) {
        try {
            return new ImageIcon(ImageIO.read(new File(path)));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    private void showNiveauxPanel() {
        removeAll();  // Efface le contenu actuel du Menu
        revalidate();
        repaint();

        // Créez le panneau des niveaux
        VueNiveaux niveauxPanel = new VueNiveaux(this.frame);

        // Ajoutez le panneau des niveaux au Menu
        setLayout(new BorderLayout());
        add(niveauxPanel, BorderLayout.CENTER);
        niveauxPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        revalidate();
        repaint();
    }
}