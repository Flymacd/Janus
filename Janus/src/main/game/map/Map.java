package main.game.map;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import main.Janus;
import main.debug.Debugger;
import main.game.gameobject.GameObject;
import main.game.gameobject.entity.Entity;
import main.game.gameobject.entity.Player;
import main.game.gameobject.entity.camera.Camera;
import main.game.map.tile.Tile;
import main.game.map.tile.voidtile.VoidTile;

public class Map {
	
	private int width, height, id;
	private float x, y, velX, velY;
	private ArrayList<GameObject> gameObjects = new ArrayList<GameObject>();
	private ArrayList<Tile> tiles = new ArrayList<Tile>();
	private ArrayList<Entity> entities = new ArrayList<Entity>();
	
	public boolean mouseObjects = false, mouseTiles = false, mouseEntities = false;
	
	public Color bgColor = new Color(0, 65, 0);
	
	private Player p;
	private Janus j;
	private boolean selected = false;
	
	private int tileSize = 32;
	public float startingX = 0, startingY = 0;
	
	private long tick;
	
	public Map(float x, float y, int width, int height, float velX, float velY, int tileSize, int id, Janus j) {
		this.x = x;
		this.y = y;
		this.velX = velX;
		this.velY = velY;
		this.j = j;
		this.width = width;
		this.height = height;
		this.tileSize = tileSize;
		this.id = id;
		tick = System.currentTimeMillis();
	}
	
	public void generateMap() {
		int id = 0;
		for (int xx = 0; xx < width / tileSize; xx++) {
			for (int yy = 0; yy < height / tileSize; yy++) {
				tiles.add(new VoidTile(xx * tileSize, yy * tileSize, tileSize, tileSize, id, xx, yy, "VOID " + id, j, this));
				tiles.get(id).setDefaultC(new Color(0, 155, 0));
//				Debugger.printLine("ID: " + id);
				id++;
			}
		}
	}
	
	public void tick() {
		if (System.currentTimeMillis() - tick >= 10) {
			// TODO: TICK
			if (p != null) {
				for (Tile t : tiles) {
					if (checkRenderDistance(t.getX(), t.getY(), t.getWidth(), t.getHeight())) {
						t.tick();
					}
				}
				for (Entity e : entities) {
					if (checkRenderDistance(e.getX(), e.getY(), e.getWidth(), e.getHeight())) {
						e.tick();
					}
				}
				for (GameObject obj : gameObjects) {
					if (checkRenderDistance(obj.getX(), obj.getY(), obj.getWidth(), obj.getHeight())) {
						obj.tick();
					}
				}
				p.tick();
				if (p.getC().followP) {
					x += -p.getVelX();
					y += -p.getVelY();
				}
			}
			tick = System.currentTimeMillis();
		}
	}
	
	public void render(Graphics g) {
		
		// TODO: Render
//		g.setColor(bgColor);
//		g.fillRect((int) x, (int) y, width, height);
		
		if (p != null) {
			for (Tile t : tiles) {
				if (checkRenderDistance(t.getX(), t.getY(), t.getWidth(), t.getHeight())) {
					t.render(g);
				}
			}
			for (Entity e : entities) {
				if (checkRenderDistance(e.getX(), e.getY(), e.getWidth(), e.getHeight())) {
					e.render(g);
				}
			}
			for (GameObject obj : gameObjects) {
				if (checkRenderDistance(obj.getX(), obj.getY(), obj.getWidth(), obj.getHeight())) {
					obj.render(g);
				}
			}
			p.render(g);
		}
		
	}
	
	public boolean checkRenderDistance(float x, float y, int width, int height) {
		if (x + width > p.getC().getX() && x < p.getC().getX() + p.getC().getWidth()) {
			if (y + height > p.getC().getY() && y < p.getC().getY() + p.getC().getHeight()) {
				return true;
			}
		}
		return false;
	}
	
	public void createPlayer(Player p) {
		this.p = p;
		p.setC(new Camera(startingX, startingY, j.w.getWidth(), j.w.getHeight(), 0, 0, p.getId(), p.getiDX(), p.getiDY(), p.getName() + " - Camera", j, p));
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

	public ArrayList<GameObject> getGameObjects() {
		return gameObjects;
	}

	public void setGameObjects(ArrayList<GameObject> gameObjects) {
		this.gameObjects = gameObjects;
	}

	public ArrayList<Tile> getTiles() {
		return tiles;
	}

	public void setTiles(ArrayList<Tile> tiles) {
		this.tiles = tiles;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	public ArrayList<Entity> getEntities() {
		return entities;
	}

	public void setEntities(ArrayList<Entity> entities) {
		this.entities = entities;
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

	public Player getP() {
		return p;
	}

	public void setP(Player p) {
		this.p = p;
	}

	public int getTileSize() {
		return tileSize;
	}

	public void setTileSize(int tileSize) {
		this.tileSize = tileSize;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void mouseMoved(int mx, int my) {
		if (mouseObjects) {
			for (GameObject obj : gameObjects) {
				if (obj.mouseOver(mx, my, (int) (x + obj.getX()), (int) (y + obj.getY()), obj.getWidth(), obj.getHeight())) {
					if (!obj.isSelected()) {
						obj.setHovered(true);
					}
				} else {
					obj.setHovered(false);
				}
			}
		}
		
		if (mouseEntities) {
			for (Entity e : entities) {
				if (e.mouseOver(mx, my, (int) (x + e.getX()), (int) (y + e.getY()), e.getWidth(), e.getHeight())) {
					if (!e.isSelected()) {
						e.setHovered(true);
					}
				} else {
					e.setHovered(false);
				}
			}
		}
		
		if (mouseTiles) {
			for (Tile t : tiles) {
				if (t.mouseOver(mx, my, (int) (x + t.getX()), (int) (y + t.getY()), t.getWidth(), t.getHeight())) {
					if (!t.isSelected()) {
						t.setHovered(true);
					}
				} else {
					t.setHovered(false);
				}
			}
		}
		
		if (p != null) {
			p.mouseMoved(mx, my);
		}
	}

	public void mouseClicked(int mx, int my) {
		if (mouseObjects) {
			for (GameObject obj : gameObjects) {
				if (obj.mouseOver(mx, my, (int) obj.getX(), (int) obj.getY(), obj.getWidth(), obj.getHeight())) {
					obj.setSelected(!obj.isSelected());
				} else {
					obj.setSelected(false);
				}
			}
		}
		
		if (mouseEntities) {
			for (Entity e : entities) {
				if (e.mouseOver(mx, my, (int) e.getX(), (int) e.getY(), e.getWidth(), e.getHeight())) {
					e.setSelected(!e.isSelected());
				} else {
					e.setSelected(false);
				}
			}
		}
		
		if (mouseTiles) {
			for (Tile t : tiles) {
				if (t.mouseOver(mx, my, (int) t.getX(), (int) t.getY(), t.getWidth(), t.getHeight())) {
					t.setSelected(!t.isSelected());
				} else {
					t.setSelected(false);
				}
			}
		}
		
		if (p != null) {
			p.mouseClicked(mx, my);
		}
	}
	
}
