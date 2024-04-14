package ressources;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;

public class ResourceManager {
    public static BufferedImage loadSprite(String file) {
        BufferedImage sprite = null;
        try {
            sprite = ImageIO.read(ResourceManager.class.getResourceAsStream("/ressources/assets/entity/infected_rat/InfectedMouse.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sprite;
    }
    
}
