package main.graphics.renderer.startup;

import java.awt.Color;
import java.awt.Graphics;

import main.Janus;

public class StartUp {
	
	public boolean started = false, shouldOtick = true, shouldTick = true, opcheck = false;
	private long tick, oTick;
	private Janus j;
	private int opacity = 255;
	
	public StartUp(Janus j) {
		this.j = j;
	}
	
	public void tick() {
		if (System.currentTimeMillis() - tick >= 500) {
			if (shouldOtick) {
				oTick = System.currentTimeMillis();
				shouldOtick = false;
			}
		}
		
		if (!shouldOtick) {
			if (System.currentTimeMillis() - oTick >= 2) {
				if (opacity > 0) {
					opacity--;
				}
				oTick = System.currentTimeMillis();
			}
		}
		
		if (opacity <= 0 && !opcheck) {
			tick = System.currentTimeMillis();
			opcheck = true;
		}
		
		if (System.currentTimeMillis() - tick >= 250) {
			if (opcheck) {
				started = true;
			}
		}
		
	}
	
	public void render(Graphics g) {
		if (shouldTick) {
			tick = System.currentTimeMillis();
			shouldTick = false;
		}
		g.setColor(Color.black);
		g.fillRect(0, 0, j.w.getWidth(), j.w.getHeight());
		
		j.dgfx.drawTitle("Janus - v" + Janus.JANUS_VERSION, j.w.getHeight() / 2 - 75, new Color(255, 255, 255, opacity), g);
	}

}
