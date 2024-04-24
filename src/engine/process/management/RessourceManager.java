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
    private Map<String, BufferedImage> images;
    private Map<String, ImageIcon> gifs;

    private RessourceManager() {
        images = new HashMap<>();
        gifs = new HashMap<>();
    }

    public static RessourceManager getInstance() {
        if (instance == null) {
            instance = new RessourceManager();
        }
        return instance;
    }

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

    public ImageIcon getGif(String path) {
        if (!gifs.containsKey(path)) {
            gifs.put(path, new ImageIcon(path));
        }
        return gifs.get(path);
    }
    // FIXME : le prof il me semble ne voulait pas de ImageIcon mais c'est la seule manière que j'ai trouvé de lire des GIF
}