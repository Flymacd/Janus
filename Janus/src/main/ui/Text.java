package main.ui;


import main.ui.uitype.UIType;

public class Text extends UIObject {

	public Text(int x, int y, int width, int height, String string) {
		super(x, y, width, height, string, UIType.text, null);
	}

	@Override
	public void tick() {
		
	}

}
