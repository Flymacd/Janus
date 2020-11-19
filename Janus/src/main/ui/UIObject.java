package main.ui;

import java.awt.Color;
import java.awt.image.BufferedImage;

import main.ui.uitype.UIType;

public abstract class UIObject {
	
	protected int x, y, width, height;
	protected boolean selected, hovered;
	protected String string;
	
	protected BufferedImage img = null;
	
	protected int fillOpacity = 35;
	protected Color defaultC = Color.white, hoverC = Color.green, selectC = Color.MAGENTA, fillC = new Color(255, 255, 255, fillOpacity), fillHoverC = new Color(0, 225, 0, fillOpacity), fillSelectC = new Color(Color.magenta.getRed(), Color.magenta.getGreen(), Color.MAGENTA.getBlue(), fillOpacity);
	protected UIType type;
	
	public boolean fill = true, border = true;
	
	public UIObject(int x, int y, int width, int height, String string, UIType type, BufferedImage img) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.selected = false;
		this.hovered = false;
		this.type = type;
		this.string = string;
		this.img = img;
	}
	
	public abstract void tick();
	
	public int getX() {
		return x;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public int getY() {
		return y;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public int getWidth() {
		return width;
	}
	
	
	public void setWidth(int width) {
		this.width = width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public void setHeight(int height) {
		this.height = height;
	}
	
	public boolean isSelected() {
		return selected;
	}
	
	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	
	public boolean isHovered() {
		return hovered;
	}
	
	public void setHovered(boolean hovered) {
		this.hovered = hovered;
	}
	
	public String getString() {
		return string;
	}
	
	public void setString(String string) {
		this.string = string;
	}

	public BufferedImage getImg() {
		return img;
	}

	public void setImg(BufferedImage img) {
		this.img = img;
	}

	public Color getDefaultC() {
		return defaultC;
	}

	public void setDefaultC(Color defaultC) {
		this.defaultC = defaultC;
	}

	public Color getHoverC() {
		return hoverC;
	}

	public void setHoverC(Color hoverC) {
		this.hoverC = hoverC;
	}

	public Color getSelectC() {
		return selectC;
	}

	public void setSelectC(Color selectC) {
		this.selectC = selectC;
	}

	public UIType getType() {
		return type;
	}

	public void setType(UIType type) {
		this.type = type;
	}

	public Color getFillC() {
		return fillC;
	}

	public void setFillC(Color fillC) {
		Color nFillC = new Color(fillC.getRed(), fillC.getGreen(), fillC.getBlue(), fillOpacity);
		if (fillC.getAlpha() != 255) {
			this.fillC = fillC;
		} else {
			this.fillC = nFillC;
		}
	}

	public Color getFillHoverC() {
		return fillHoverC;
	}

	public void setFillHoverC(Color fillHoverC) {
		Color nFillC = new Color(fillHoverC.getRed(), fillHoverC.getGreen(), fillHoverC.getBlue(), fillOpacity);
		if (fillHoverC.getAlpha() != 255) {
			this.fillHoverC = fillHoverC;
		} else {
			this.fillHoverC = nFillC;
		}
	}

	public Color getFillSelectC() {
		return fillSelectC;
	}

	public void setFillSelectC(Color fillSelectC) {
		Color nFillC = new Color(fillSelectC.getRed(), fillSelectC.getGreen(), fillSelectC.getBlue(), fillOpacity);
		if (fillSelectC.getAlpha() != 255) {
			this.fillSelectC = fillSelectC;
		} else {
			this.fillSelectC = nFillC;
		}
	}
	
}
