package main.input.keyboard;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

import main.Janus;
import main.input.keyboard.keys.Keys;
import main.input.keyboard.keys.key.Key;
import main.menus.Menu;
import main.ui.UIObject;
import main.ui.passfield.PassField;
import main.ui.uitype.UIType;

public class Keyboard implements KeyListener{
	
	private Janus j;
	public long tick = 0;
	
	public static List<Key> keys = new ArrayList<Key>();
	
	public boolean spacebar = false;
	
	public Keyboard(Janus j) {
		Keys k = new Keys();
		this.j = j;
		j.w.addKeyListener(this);
		j.w.setFocusTraversalKeysEnabled(false);
		tick = System.currentTimeMillis();
	}
	
	public void releaseAll() {
		for (Key k : keys) {
			if (k.down) {
				k.down = false;
			}
		}
	}
	
	public void tick() {
		for (Key k : keys) {
			k.tick();
			for (int i = 0; i < j.h.getMenus().size(); i++) {
				Menu m = j.h.getMenu(i);
				for (int l = 0; l < m.getUiObjects().size(); l++) {
					UIObject uio = m.getUiObjects().get(l);
					if (uio.isSelected() && (uio.getType() == UIType.textfield || uio.getType() == UIType.numfield || uio.getType() == UIType.password)) {
						if (k.clicked && (!k.keyName.equalsIgnoreCase("LEFT_ARROW") || !k.keyName.equalsIgnoreCase("RIGHT_ARROW") || !k.keyName.equalsIgnoreCase("DOWN_ARRROW") || !k.keyName.equalsIgnoreCase("UP_ARROW") || !k.keyName.equalsIgnoreCase("ESCAPE"))) {
							String typeString = "";
							if (uio.getType() == UIType.password) {
								PassField p = (PassField) uio;
								typeString = p.getPstring();
							} else {
								typeString = uio.getString();
							}
							if (k.keyName.equalsIgnoreCase("SHIFT")) {
								
							} else if (k.keyName.equalsIgnoreCase("ENTER")) {
								uio.setSelected(false);
							} else {
								if (typeString.length() >= 14 && !k.keyName.equalsIgnoreCase("BACK_SPACE")) {
									return;
								}
								
								if (uio.getType() == UIType.numfield) {
									if (k.keyName.equals("0") || k.keyName.equals("1") || k.keyName.equals("2") || k.keyName.equals("3") || k.keyName.equals("4") || k.keyName.equals("5") || k.keyName.equals("6") || k.keyName.equals("7") || k.keyName.equals("8") || k.keyName.equals("9")) {
										typeString = typeString + k.keyName;
									}
								} else if (!k.keyName.equalsIgnoreCase("BACK_SPACE")){
									typeString = typeString + k.keyName;
								}
								
								
							}
							if (k.keyName.equalsIgnoreCase("BACK_SPACE")) {
								typeString = backSpace(typeString);
							}
							if (uio.getType() == UIType.password) {
								PassField p = (PassField) uio;
								p.setPstring(typeString);
								String pass = "";
								for (int n = 0; n < typeString.length(); n++) {
									pass = pass + "*";
								}
								uio.setString(pass);
							} else if (uio.getType() != UIType.password) {
								uio.setString(typeString);
							}
						}
					}
				}
			}
		}
	}
	
	private String backSpace(String typeString) { 
		String newString = typeString;
		if (newString != null && newString.length() > 0) {
			newString = newString.substring(0, newString.length() - 1);
		}
		return newString;
	}
	
	public void toggle(KeyEvent e, boolean pressed) {
		for (Key k : keys) {
			if (e.getKeyCode() == k.keyCode) {
				k.toggle(pressed);
			}
		}
	}
	

	public void keyPressed(KeyEvent e) {
		toggle(e, true);
	}

	public void keyReleased(KeyEvent e) {
		toggle(e, false);
	}

	public void keyTyped(KeyEvent e) {
		
	}
	
	public static Clipboard getSystemClipboard() {
        Toolkit defaultToolkit = Toolkit.getDefaultToolkit();
        Clipboard systemClipboard = defaultToolkit.getSystemClipboard();

        return systemClipboard;
    }
}
