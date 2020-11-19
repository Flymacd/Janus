package main.game.gameobject.entity;

import main.Janus;
import main.game.gameobject.GameObject;
import main.game.gameobject.entity.entitytype.EntityType;

public abstract class Entity extends GameObject {

	protected EntityType eType;
	protected boolean alive;
	
	public Entity(float x, float y, int width, int height, float velX, float velY, int id, float iDX, float iDY, String name, Janus j, EntityType eType) {
		super(x, y, width, height, velX, velY, id, iDX, iDY, name, j);
		this.eType = eType;
	}

	public EntityType geteType() {
		return eType;
	}

	public void seteType(EntityType eType) {
		this.eType = eType;
	}

	public boolean isAlive() {
		return alive;
	}

	public void setAlive(boolean alive) {
		this.alive = alive;
	}
	
}
