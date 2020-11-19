package main.game;

import java.awt.Graphics;
import java.util.ArrayList;

import main.Janus;
import main.debug.Debugger;
import main.game.map.Map;

public class Game {
	
	private int width, height;
	private boolean inGame = false;
	private Janus j;
	private ArrayList<Map> maps = new ArrayList<Map>();
	
	public Game(int width, int height, Janus j) {
		this.width = width;
		this.height = height;
		this.j = j;
	}
	
	public void tick() {
		for (Map m : maps) {
			if (m.isSelected()) {
				m.tick();
			}
		}
	}
	
	public void render(Graphics g) {
		for (Map m : maps) {
			if (m.isSelected()) {
				m.render(g);
			}
		}
	}
	
	public void createMap(float x, float y, int width, int height, int tileSize) {
		maps.add(new Map(x, y, width * tileSize, height * tileSize, 0f, 0f, tileSize, maps.size(), j));
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

	public boolean isInGame() {
		return inGame;
	}

	public void setInGame(boolean inGame) {
		this.inGame = inGame;
	}

	public ArrayList<Map> getMaps() {
		return maps;
	}

	public Map getMap(int id) {
		for (Map m : maps) {
			if (m.getId() == id) {
				return m;
			}
		}
		Debugger.printError("MAP-ID: " + id + " | RETURNED NULL");
		return null;
	}
	
	public void setMaps(ArrayList<Map> maps) {
		this.maps = maps;
	}

	public void mouseMoved(int mx, int my) {
		for (Map m : maps) {
			if (m.isSelected()) {
				m.mouseMoved(mx, my);
			}
		}
	}

	public void mouseClicked(int mx, int my) {
		for (Map m : maps) {
			if (m.isSelected()) {
				m.mouseClicked(mx, my);
			}
		}
	}
	
}
