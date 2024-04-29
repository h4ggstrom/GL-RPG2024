package engine.process.management;

import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Le singleton RessourceManager nous permet de traiter l'importation de chaque asset du jeu une seule fois pour éviter une consommation abusive à chaque traitement graphique
 * Chaque image utilisée dans le jeu sera importée et ajoutée au RessourceManager ou bien récupérée dans le RessourceManager si déjà présente
 */
public class RessourceManager {
    private static RessourceManager instance = null;
    // On utilise deux Map pour stocker les images et les gifs
    private Map<String, BufferedImage> images;
    private Map<String, ImageIcon> gifs;

    private RessourceManager() {
        images = new HashMap<>();
        gifs = new HashMap<>();
    }

    /**
     * Méthode permettant de récupérer l'instance du RessourceManager
     * 
     * @return l'instance du RessourceManager
     */
    public static RessourceManager getInstance() {
        if (instance == null) {
            instance = new RessourceManager();
        }
        return instance;
    }

    /**
     * Méthode permettant de récupérer une image à partir de son chemin
     * 
     * @param path le chemin de l'image
     * @return l'image
     */
    public BufferedImage getImage(String path) {
        if (!images.containsKey(path)) {
            try {
                images.put(path, ImageIO.read(new File(path)));
            } catch (IOException e) {
                System.err.println("L'image " + path + " ne peut être ouverte.");
            }
        }
        return images.get(path);
    }
    
    /**
     * Méthode permettant de récupérer un gif à partir de son chemin
     * 
     * @param path le chemin du gif
     * @return le gif
     */
    public ImageIcon getGif(String path) {
        if (!gifs.containsKey(path)) {
            gifs.put(path, new ImageIcon(path));
        }
        return gifs.get(path);
    }
}