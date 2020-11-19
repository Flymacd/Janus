package main.ui.textfield;

import java.awt.image.BufferedImage;

import main.ui.UIObject;
import main.ui.uitype.UIType;

public class TextField extends UIObject {
	
	public TextField(int x, int y, int width, int height, String string, BufferedImage img) {
		super(x, y, width, height, string, UIType.textfield, img);
	}

	public void tick() {
		
	}

}
