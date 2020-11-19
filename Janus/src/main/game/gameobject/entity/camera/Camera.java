package main.game.gameobject.entity.camera;

import java.awt.Graphics;

import main.Janus;
import main.game.gameobject.GameObject;
import main.game.gameobject.entity.Player;

public class Camera extends GameObject {
	
	public boolean followP = false, renderOutline = false;
	private Player p;

	public Camera(float x, float y, int width, int height, int velX, int velY, int id, float iDX, float iDY, String name, Janus j, Player p) {
		super(x, y, width, height, velX, velY, id, iDX, iDY, name, j);
		this.p = p;
	}

	@Override
	public void tick() {
		if (followP) {
			x = (p.getX() + p.getWidth() / 2) - (width / 2);
			y = (p.getY() + p.getHeight() / 2) - (height / 2);
		}
	}

	@Override
	public void render(Graphics g) {
		if (renderOutline) {
			j.dgfx.drawCameraOutline(this, g, p.getM());
		}
	}

}
