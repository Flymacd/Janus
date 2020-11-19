package main.ui.numfield;

import java.awt.image.BufferedImage;

import main.ui.UIObject;
import main.ui.uitype.UIType;

public class NumField extends UIObject {

	public NumField(int x, int y, int width, int height, String string, BufferedImage img) {
		super(x, y, width, height, string, UIType.numfield, img);
	}

	public void tick() {
		
	}

}
