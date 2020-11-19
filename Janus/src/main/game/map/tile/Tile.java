package main.game.map.tile;

import java.awt.Graphics;

import main.Janus;
import main.game.gameobject.GameObject;
import main.game.map.Map;
import main.game.map.tile.tiletype.TileType;

public abstract class Tile extends GameObject {

	protected TileType type;
	protected Map m;
	
	public boolean border = true;
	
	public Tile(float x, float y, int width, int height, int id, float iDX, float iDY, String name, Janus j, TileType type, Map m) {
		super(x, y, width, height, id, iDX, iDY, name, j);
		this.m = m;
		this.type = type;
	}

	public TileType getType() {
		return type;
	}

	public void setType(TileType type) {
		this.type = type;
	}
	
	@Override
	public void tick() {
		
	}
	
	@Override
	public void render(Graphics g) {
		j.dgfx.drawGameObject(this, m, g);
	}

	public Map getM() {
		return m;
	}

	public void setM(Map m) {
		this.m = m;
	}
	
}
