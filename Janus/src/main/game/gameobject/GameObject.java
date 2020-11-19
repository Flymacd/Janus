package main.game.gameobject;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import main.Janus;

public abstract class GameObject {
	
	protected int width, height, id;
	protected float x, y, iDX, iDY, velX, velY;
	protected String name;
	protected boolean shouldRender = false, selected = false, hovered = false;
	protected Color defaultC = Color.white, hoverC = Color.green, selectC = Color.red;
	protected Janus j;
	protected BufferedImage img = null;
	
	/**
	 * GameObject with Veloctiy without image
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param velX
	 * @param velY
	 * @param id
	 * @param iDX
	 * @param iDY
	 * @param name
	 * @param j
	 */
	public GameObject(float x, float y, int width, int height, float velX, float velY, int id, float iDX, float iDY, String name, Janus j) {
		this.width = width;
		this.height = height;
		this.velX = velX;
		this.velY = velY;
		this.id = id;
		this.x = x;
		this.y = y;
		this.iDX = iDX;
		this.iDY = iDY;
		this.name = name;
		this.j = j;
	}
	
	/**
	 * GameObject without Velocity without Image
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param id
	 * @param iDX
	 * @param iDY
	 * @param name
	 * @param j
	 */
	public GameObject(float x, float y, int width, int height, int id, float iDX, float iDY, String name, Janus j) {
		this.width = width;
		this.height = height;
		this.id = id;
		this.x = x;
		this.y = y;
		this.iDX = iDX;
		this.iDY = iDY;
		this.name = name;
		this.j = j;
	}
	
	/**
	 * GameObject with Velocity with Image
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param velX
	 * @param velY
	 * @param id
	 * @param iDX
	 * @param iDY
	 * @param name
	 * @param j
	 * @param img
	 */
	public GameObject(float x, float y, int width, int height, float velX, float velY, int id, float iDX, float iDY, String name, Janus j, BufferedImage img) {
		this.width = width;
		this.height = height;
		this.velX = velX;
		this.velY = velY;
		this.id = id;
		this.x = x;
		this.y = y;
		this.iDX = iDX;
		this.iDY = iDY;
		this.name = name;
		this.j = j;
		this.img = img;
	}
	
	public abstract void tick();
	
	public abstract void render(Graphics g);

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

	public float getVelX() {
		return velX;
	}

	public void setVelX(float velX) {
		this.velX = velX;
	}

	public float getVelY() {
		return velY;
	}

	public void setVelY(float velY) {
		this.velY = velY;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public float getiDX() {
		return iDX;
	}

	public void setiDX(float iDX) {
		this.iDX = iDX;
	}

	public float getiDY() {
		return iDY;
	}

	public void setiDY(float iDY) {
		this.iDY = iDY;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isShouldRender() {
		return shouldRender;
	}

	public void setShouldRender(boolean shouldRender) {
		this.shouldRender = shouldRender;
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

	public boolean mouseOver(int mx, int my, int x, int y, int width, int height) {
		if(mx > x && mx < x + width) {
			if(my > y && my < y + height) {
				return true;
			} else return false;
		} else return false;
	}
	
}
