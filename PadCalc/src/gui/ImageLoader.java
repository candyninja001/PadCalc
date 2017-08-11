package gui;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

public class ImageLoader {
	public static BufferedImage getImage(String location){
		try {
			File file = new File(location);
			if(location.startsWith("img/book/")){
				if(!file.exists()){
					URL url = new URL("http://puzzledragonx.com/en/"+location);
					BufferedImage image = ImageIO.read(url);
					File outputfile = new File(location);
					ImageIO.write(image, "png", outputfile);
				}
			}
			if(file.exists())
				return ImageIO.read(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
