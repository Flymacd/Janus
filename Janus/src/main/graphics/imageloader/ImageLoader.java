package main.graphics.imageloader;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import main.debug.Debugger;

public class ImageLoader {
	
	public static BufferedImage loadImage(String path) {
		try {
			return ImageIO.read(ImageLoader.class.getResource(path));
		} catch (Exception e) {
			Debugger.printWarning("COULD NOT LOAD IMAGE \"" + path + "\"");
			Debugger.printWarning(e.toString());
		}
		return null;
	}
	
}