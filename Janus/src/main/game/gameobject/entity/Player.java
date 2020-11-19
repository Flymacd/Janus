package main.game.gameobject.entity;

import java.awt.Graphics;

import main.Janus;
import main.game.gameobject.entity.camera.Camera;
import main.game.gameobject.entity.entitytype.EntityType;
import main.game.map.Map;
import main.input.keyboard.keys.Keys;
import main.input.keyboard.keys.key.Key;

public class Player extends Entity {

	private Camera c;
	
	private float moveSpeed = 5f;
	private Map m;
	private boolean moving = false;
	public boolean canMove = true;
	
	public Key up = Keys.W, down = Keys.S, left = Keys.A, right = Keys.D, esc = Keys.ESCAPE;
	
	public Player(float x, float y, int width, int height, float velX, float velY, int id, float iDX, float iDY, String name, Janus j, Map m) {
		super(x, y, width, height, velX, velY, id, iDX, iDY, name, j, EntityType.player);
		this.m = m;
	}

	@Override
	public void tick() {
		c.tick();
		if (canMove) {
			handleMovement();
		}
		if (c.followP) {
			if (c.getX() + velX <= m.getX() || (c.getX() + c.getWidth()) + velX >= m.getWidth()) {
				velX = 0;
			}
		}
		x += velX;
		if (c.followP) {
			if (c.getY() + velY <= m.getY() || (c.getY() + c.getHeight()) + velY >= m.getHeight()) {
				velY = 0;
			}
		}
		y += velY;
	}

	private void handleMovement() {
//		if (j.k.getKeys() != null) {
//			if (j.k.getKeys()[up.getKeyCode()]) {
//				velY = -moveSpeed;
//			} else if (j.k.getKeys()[down.getKeyCode()]) {
//				velY = moveSpeed;
//			} else {
//				velY = 0;
//			}
//			
//			if (j.k.getKeys()[left.getKeyCode()]) {
//				velX = -moveSpeed;
//			} else if (j.k.getKeys()[right.getKeyCode()]) {
//				velX = moveSpeed;
//			} else {
//				velX = 0;
//			}
//			
//			if (j.k.getKeys()[esc.getKeyCode()]) {
//				if (j.h.escapeMenu != null) {
//					j.h.changeToMenu(j.h.escapeMenu, j.h.getGame(), j.h.escapeMenu);
//				}
//			}
//		}
	}

	@Override
	public void render(Graphics g) {
		j.dgfx.drawPlayer(this, g);
		c.render(g);
	}
	
	/**
	 * OVERRIDE THIS
	 * @param mx
	 * @param my
	 */
	public void mouseMoved(int mx, int my) {
		
	}
	
	/**
	 * OVERRIDE THIS
	 * @param mx
	 * @param my
	 */
	public void mouseClicked(int mx, int my) {
		
	}

	public Camera getC() {
		return c;
	}

	public void setC(Camera c) {
		this.c = c;
	}

	public float getMoveSpeed() {
		return moveSpeed;
	}

	public void setMoveSpeed(float moveSpeed) {
		this.moveSpeed = moveSpeed;
	}

	public Map getM() {
		return m;
	}

	public void setM(Map m) {
		this.m = m;
	}

	public boolean isMoving() {
		return moving;
	}

	public void setMoving(boolean moving) {
		this.moving = moving;
	}

}
