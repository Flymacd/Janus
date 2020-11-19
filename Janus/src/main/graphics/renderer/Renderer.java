package main.graphics.renderer;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import main.Janus;
import main.graphics.renderer.startup.StartUp;

public class Renderer {

	private Janus j;
	public static boolean showFps = false;
	public StartUp s;
	
	public Renderer(Janus j) {
		this.j = j;
		s = new StartUp(j);
	}
	
	public void render() {
		BufferStrategy bs = j.w.getBufferStrategy();
		if (bs == null) {
			j.w.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		g.setFont(j.fnt);
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, j.w.getWidth(), j.w.getHeight());
	
		j.h.render(g);
		
		if (!s.started) {
			s.render(g);
		}
		
		if (showFps) {
			g.setColor(Color.white);
			g.drawString(j.gl.fps + " FPS", 120, 120);
		}
		
		g.dispose();
		bs.show();
	}
}
