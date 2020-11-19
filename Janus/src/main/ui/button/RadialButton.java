package main.ui.button;

import java.awt.image.BufferedImage;

import main.ui.UIObject;
import main.ui.uitype.UIType;

public class RadialButton extends UIObject {

	private int radius;
	
	public RadialButton(int x, int y, int radius, String string, BufferedImage img) {
		super(x, y, radius * 2, radius * 2, string, UIType.radial, img);
		this.radius = radius;
	}

	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}

	public void tick() {
		
	}
	
}
