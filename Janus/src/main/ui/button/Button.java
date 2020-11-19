package main.ui.button;

import java.awt.image.BufferedImage;

import main.ui.UIObject;
import main.ui.uitype.UIType;

public class Button extends UIObject {

	public Button(int x, int y, int width, int height, String string, BufferedImage img) {
		super(x, y, width, height, string, UIType.button, img);
	}

	public void tick() {
		
	}

}
