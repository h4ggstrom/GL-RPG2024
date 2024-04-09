package engine.process;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.log4j.Logger;

import log.Gamelog;

/**
 * Génie Logiciel - Projet RPG.
 * 
 * Cette classe gère divers aspects utiles au jeu.
 * 
 * @author thibault.terrie@etu.cyu.fr
 * @author robin.de-angelis@etu.cyu.fr
 * @author hayder.ur-rehman@etu.cyu.fr
 * 
 */
public class Utility {
      
	// déclaration des variables
	private static Logger logger = Gamelog.getLogger(); // récupération du logger
	/**
	 * Cette méthode récupère et renvoie un fichier image à partir d'un chemin spécifié
	 * 
	 * @param filePath le chemin du fichier à lire. (prioriser les chemins relatifs, partant de la racine du repertoire.)
	 * @return l'image en tant qu'objet File
	 */
	public static Image readImage(String filePath) {
		try {
			return ImageIO.read(new File(filePath));
		} catch (IOException e) {
			logger.error("Image cannot be read");
			return null;
		}
	}
    
}
