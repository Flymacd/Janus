package main.ui.passfield;

import java.awt.image.BufferedImage;

import main.ui.UIObject;
import main.ui.uitype.UIType;

public class PassField extends UIObject {
	
	private String pstring = "";
	
	public PassField(int x, int y, int width, int height, String string, BufferedImage img) {
		super(x, y, width, height, string, UIType.password, img);
	}

	public void tick() {
		
	}

	public String getPstring() {
		return pstring;
	}

	public void setPstring(String pstring) {
		this.pstring = pstring;
	}
	
}
