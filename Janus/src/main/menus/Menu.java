package main.menus;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import main.Janus;
import main.gamestate.State;
import main.options.Options;
import main.ui.Text;
import main.ui.UIObject;
import main.ui.button.Button;
import main.ui.button.RadialButton;
import main.ui.numfield.NumField;
import main.ui.passfield.PassField;
import main.ui.textfield.TextField;
import main.ui.uitype.UIType;

public abstract class Menu {
	
	protected int id;
	protected String name;
	protected boolean selected = false, renderTitle = false;
	protected Janus j;
	protected Color bgColor = Color.black, titleColor = Color.white;
	public static int mID;
	
	protected ArrayList<UIObject> uiObjects = new ArrayList<UIObject>();
	
	public Menu(int id, String name, boolean selected, Janus j) {
		this.id = id;
		this.name = name;
		this.selected = selected;
		this.j = j;
		mID = id;
		generateUI();
	}
	
	public abstract void tick();
	
	public abstract void generateUI();
	
	int count = 0;
	
	protected void addUIObject(double xx, double yy, double width, double height, String s, UIType t, BufferedImage img, boolean horizInc, boolean vertInc, boolean horizDec, boolean vertDec) {
		double jwidth = j.getWidth();
		double jheight = j.getHeight();
		
		double scaleW = jwidth / Options.currMonitor.getLargestRes().getWidth();
		double scaleH = jheight / Options.currMonitor.getLargestRes().getHeight();
		int x = (int) (xx * scaleW);
		int y = (int) (yy * scaleH);
		int w = (int) (width * scaleW);
		int h = (int) (height * scaleH);
		
		
		if (horizInc || vertInc || horizDec || vertDec) {
			count++;
		} else {
			count = 0;
		}
		
		if (horizInc) {
			if (vertInc) {
				addUIObj(x + (w * count) + ((w / 3) * count), y + (h * count) + ((h / 3) * count), w, h, s, t, img);
			} else if (vertDec) {
				addUIObj(x + (w * count) + ((w / 3) * count), y - (h * count) - ((h / 3) * count), w, h, s, t, img);
			} else {
				addUIObj(x + (w * count) + ((w / 3) * count), y, w, h, s, t, img);
			}
		} else if (horizDec) {
			if (vertInc) {
				addUIObj(x - (w * count) - ((w / 3) * count), y + (h * count) + ((h / 3) * count), w, h, s, t, img);
			} else if (vertDec) {
				addUIObj(x - (w * count) - ((w / 3) * count), y - (h * count) - ((h / 3) * count), w, h, s, t, img);
			} else {
				addUIObj(x - (w * count) - ((w / 3) * count), y, w, h, s, t, img);
			}
		} else {
			if (vertInc) {
				addUIObj(x, y + (h * count) + ((h / 3) * count), w, h, s, t, img);
			} else if (vertDec) {
				addUIObj(x, y - (h * count) - ((h / 3) * count), w, h, s, t, img);
			} else {
				addUIObj(x, y, w, h, s, t, img);
			}
		}
	}
	
	private void addUIObj(int x, int y, int width, int height, String s, UIType t, BufferedImage img) {
		if (t == UIType.button) {
			uiObjects.add(new Button(x, y, width, height, s, img));
		} else if (t == UIType.password) {
			uiObjects.add(new PassField(x, y, width, height, s, img));
		} else if (t == UIType.numfield) {
			uiObjects.add(new NumField(x, y, width, height, s, img));
		} else if (t == UIType.textfield) {
			uiObjects.add(new TextField(x, y, width, height, s, img));
		} else if (t == UIType.text) {
			uiObjects.add(new Text(x, y, width, height, s));
		} else if (t == UIType.radial) {
			uiObjects.add(new RadialButton(x, y, width, s, img));
		} 
	}
	
	public void scaleUI(Menu m) {
		ArrayList<UIObject> rtm = new ArrayList<UIObject>();
		
		for (int i = 0; i < uiObjects.size(); i++) {
			rtm.add(uiObjects.get(i));
		}
		
		for (int i = 0; i < rtm.size(); i++) {
			uiObjects.remove(rtm.get(i));
		}
		
		count = 0;
		generateUI();
	}
	
	public void mouseMoved(int mx, int my) {
		for (UIObject obj : getUiObjects()) {
			if (mouseOver(mx, my, obj.getX(), obj.getY(), obj.getWidth(), obj.getHeight())) {
				if (!obj.isSelected()) {
					obj.setHovered(true);
				}
			} else {
				obj.setHovered(false);
			}
		}
	}
	
	public void mouseClicked(int mx, int my) {
		for (UIObject obj : getUiObjects()) {
			if (mouseOver(mx, my, obj.getX(), obj.getY(), obj.getWidth(), obj.getHeight())) {
				obj.setSelected(!obj.isSelected());
				if (obj.isSelected()) {
					obj.setHovered(false);
				}
			} else {
				obj.setSelected(false);
			}
		}
	}
	
	public void render(Graphics g) {
		g.setColor(bgColor);
		g.fillRect(0, 0, j.w.getWidth(), j.w.getHeight());
		if (renderTitle) {
			j.dgfx.drawTitle(name, j.w.getHeight() / 8, titleColor, g);
		}
		for (UIObject obj : getUiObjects()) {
			j.dgfx.drawUIObject(obj, g);
		}
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	public ArrayList<UIObject> getUiObjects() {
		return uiObjects;
	}

	public void setUiObjects(ArrayList<UIObject> uiObjects) {
		this.uiObjects = uiObjects;
	}
	
	protected boolean mouseOver(int mx, int my, int x, int y, int width, int height) {
		if(mx > x && mx < x + width) {
			if(my > y && my < y + height) {
				return true;
			} else return false;
		} else return false;
	}
	
}
