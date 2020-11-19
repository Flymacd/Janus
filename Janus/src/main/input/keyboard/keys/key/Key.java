package main.input.keyboard.keys.key;

import java.util.ArrayList;

public class Key {
	public int presses, absorbs, keyCode;
	public boolean down, clicked;
	public String keyName;
	
	public Key(int keyCode, String k, ArrayList<Key> keys) {
		this.keyCode = keyCode;
		this.keyName = k;
		keys.add(this);
	}
	
	public void toggle(boolean pressed) {
		if (pressed != down) {
			down = pressed;
		}
		
		if (pressed) {
			presses++;
		}
	}
	
	public void tick() {
		if (absorbs < presses) {
			absorbs++;
			clicked = true;
		} else {
			clicked = false;
		}
	}
	
}
