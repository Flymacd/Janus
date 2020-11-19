package main.game.gameobject.entity.item;

import main.Janus;
import main.game.gameobject.entity.Entity;
import main.game.gameobject.entity.entitytype.EntityType;

public abstract class ItemEntity extends Entity {

	public ItemEntity(float x, float y, int width, int height, int velX, int velY, int id, float iDX, float iDY, String name, Janus j, EntityType eType) {
		super(x, y, width, height, velX, velY, id, iDX, iDY, name, j, eType);
	}

}
