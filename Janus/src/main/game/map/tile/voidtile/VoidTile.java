package main.game.map.tile.voidtile;


import main.Janus;
import main.game.map.Map;
import main.game.map.tile.Tile;
import main.game.map.tile.tiletype.TileType;

public class VoidTile extends Tile {

	public VoidTile(float x, float y, int width, int height, int id, float iDX, float iDY, String name, Janus j, Map m) {
		super(x, y, width, height, id, iDX, iDY, name, j, TileType.voidTile, m);
	}

}
