package res;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Texture {
	
	public static Image loadTexture(String resource){
		
		try {
			return new ImageIcon(Texture.class.getResource("/res/images/" + resource)).getImage();
		} catch (Exception e) {
			try {
				return new ImageIcon(Texture.class.getResource("/res/images/unknown.png")).getImage();
			} catch (Exception e1) {
				return null;
			}
		}
	}
	
	
}
