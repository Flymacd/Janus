package main.graphics.drawgfx;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

import main.Janus;
import main.game.gameobject.GameObject;
import main.game.gameobject.entity.Player;
import main.game.gameobject.entity.camera.Camera;
import main.game.map.Map;
import main.game.map.tile.Tile;
import main.ui.UIObject;
import main.ui.button.RadialButton;
import main.ui.uitype.UIType;


public class DrawGFX {

	private Janus j;
	
	public DrawGFX(Janus j) {
		this.j = j;
	}
	
	public void drawGameObject(GameObject go, Map m, Graphics g) {
		
		if (go.isHovered()) {
			g.setColor(go.getHoverC());
		} else if (go.isSelected()) {
			g.setColor(go.getSelectC());
		} else {
			g.setColor(go.getDefaultC());
		}
		
		if (go.getImg() != null) {
			g.drawImage(go.getImg(), (int) (m.getX() + go.getX()), (int) (m.getY() + go.getY()), go.getWidth(), go.getHeight(), j.w);
		} else {
			g.fillRect((int) (m.getX() + go.getX()), (int) (m.getY() + go.getY()), go.getWidth(), go.getHeight());
		}
		
		if (((Tile) go).getType() != null) { drawTile((Tile)go, m, g); }
	}
	
	private void drawTile(Tile t, Map m, Graphics g) {
		g.setColor(Color.black);
		
		if (t.border) {
			g.drawRect((int) (m.getX() + t.getX()), (int) (m.getY() + t.getY()), t.getWidth(), t.getHeight());
		}
	}

	public void drawUIObject(UIObject b, Graphics g) {
		if (b.isHovered()) {
			g.setColor(b.getHoverC());
		} else if (b.isSelected()) {
			g.setColor(b.getSelectC());
		} else {
			g.setColor(b.getDefaultC());
		}
		
		if (b.getType().equals(UIType.radial)) { drawRadialButton(b, g); return; }
		
		if (b.getImg() != null) {
			g.drawImage(b.getImg(), b.getX(), b.getY(), b.getWidth(), b.getHeight(), j.w);
		} else if (b.border) {
			g.drawRect(b.getX(), b.getY(), b.getWidth(), b.getHeight());
		}
		
		if (!b.getString().trim().equalsIgnoreCase("")) {
			drawCenteredString(b, g);
		}
		
		if (b.fill) {
			if (b.isHovered()) {
				g.setColor(b.getFillHoverC());
			} else if (b.isSelected()) {
				g.setColor(b.getFillSelectC());
			} else {
				g.setColor(b.getFillC());
			}
			g.fillRect(b.getX(), b.getY(), b.getWidth(), b.getHeight());
		}
		
	}
	
	public void drawRadialButton(UIObject b, Graphics g) {
		RadialButton r = (RadialButton) b;
		
		g.drawOval(r.getX() - (r.getRadius() / 10), r.getY() - (r.getRadius() / 10), r.getRadius() * 2 + ((r.getRadius() / 10) * 2), r.getRadius() * 2 + ((r.getRadius() / 10) * 2));
		
		if (r.isSelected()) {
			g.fillOval(r.getX(), r.getY(), r.getRadius() * 2, r.getRadius() * 2);
		}
	}
	
	public void drawCenteredString(UIObject b, Graphics g) {
		j.fnt = new Font("Serif", 1, j.getWidth() / 56);
		FontMetrics fm = g.getFontMetrics();
	    int x = b.getX() + (b.getWidth() - fm.stringWidth(b.getString())) / 2 - 1;
	    int y = b.getY() + (fm.getAscent() + (b.getHeight() - (fm.getAscent() + fm.getDescent())) / 2) - 1;
	    g.drawString(b.getString(), x, y);
	}
	
	public void drawTitle(String s, int y, Color titleColor, Graphics g) {
		// TEXT SCALING
		Font fntT = new Font(j.fnt.getFontName(), 1, j.getWidth() / 20);

		g.setFont(fntT);
		FontMetrics fm = g.getFontMetrics();
		int x = (j.w.getWidth() - fm.stringWidth(s)) / 2;
	    g.setColor(titleColor);
	    g.drawString(s, x, y);
	    g.setFont(j.fnt);
	}

	public void drawPlayer(Player p, Graphics g) {
		// TODO : Render Player
		
		g.setColor(p.getDefaultC());
		
		Map m = p.getM();
		
		g.fillRect((int) (m.getX() + p.getX()), (int) (m.getY() + p.getY()), p.getWidth(), p.getHeight());
	}

	public void drawCameraOutline(Camera c, Graphics g, Map m) {
		g.setColor(Color.red);
		
		g.drawRect((int) (m.getX() + c.getX()) + 2, (int) (m.getY() + c.getY()) + 2, c.getWidth() - 4, c.getHeight() - 4);
		
		g.drawString("C-X: " + (c.getX()), 150, 150);
		g.drawString("C-Y: " + (c.getY()), 150, 180);
	}
	
}
