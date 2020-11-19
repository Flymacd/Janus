package main.gameloop;

import main.Janus;
import main.gamestate.State;
import main.menus.Menu;
import main.options.Options;

public class GameLoop implements Runnable {

	public Thread gameLoop;
	private Janus j;
	public int fps = 0;
	public boolean running;
	public boolean wchange = false;
	
	public GameLoop(Janus j, Thread thread) {
		this.j = j;
		if (gameLoop == null) {
			gameLoop = thread;
		}
	}
	
	public synchronized void start() {
		running = true;
		if (gameLoop != null) {
			gameLoop.start();
		}
	}
	
	public synchronized void stop() {
		running = false;
	}
	
	public void run() {
		long lastTime = System.nanoTime();
		double nsPerTick = 1000000000D/60D;
		int ticks = 0;
		int frames = 0;
		long lastTimer = System.currentTimeMillis();
		double delta = 0;
		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / nsPerTick;
			lastTime = now;
			boolean shouldRender = true;
			
			while (delta >= 1) {
				ticks++;
				if (!wchange) {
					j.h.tick();
				}
				delta -= 1;
				shouldRender = true;
			}
			
//			try { 
//				Thread.sleep(2);;
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
			if (shouldRender) {
				frames++;
				if (!wchange) {
					j.r.render();
				}
			}
			
			if (System.currentTimeMillis() - lastTimer > 1000) {
				lastTimer = System.currentTimeMillis();
				fps = frames;
				frames = 0;
				ticks = 0;
			}
		}
	}
	
}
