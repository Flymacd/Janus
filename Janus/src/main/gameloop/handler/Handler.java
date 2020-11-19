package main.gameloop.handler;

import java.awt.Graphics;
import java.util.ArrayList;

import main.Janus;
import main.debug.Debugger;
import main.game.Game;
import main.game.gameobject.entity.Player;
import main.game.map.Map;
import main.gamestate.State;
import main.load.Loading;
import main.menus.Menu;
import main.options.Options;
import main.ui.UIObject;

public class Handler {

	private Janus j;
	private ArrayList<Menu> menus = new ArrayList<Menu>();
	public Menu escapeMenu;
	public boolean loading = false;
	private Game game;
	
	public State state = State.running;
	
	private long tick;
	
	public Handler(Janus j) {
		this.j = j;
	}
	
	/**
	 * Creates new game
	 * @param width
	 * @param height
	 */
	public void newGame(int width, int height) {
		game = new Game(width, height, j);
	}
	
	/**
	 * Creates new game with a map the size of the game
	 * @param width
	 * @param height
	 * @param tileSize - essentially a map scale
	 * @param playerWidth
	 * @param playerHeight
	 * @param playerName
	 */
	public void newGameWithMap(int width, int height, int tileSize, int playerWidth, int playerHeight, String playerName) {
		game = new Game(width, height, j);
		game.createMap(0, 0, width, height, tileSize);
		game.getMaps().get(0).generateMap();
		game.getMaps().get(0).createPlayer(new Player(j.w.getWidth() / 2 - playerWidth / 2, j.w.getHeight() / 2 - playerHeight / 2, playerWidth, playerHeight, 0, 0, 0, 0, 0, playerName, j, game.getMaps().get(0)));
	}
	
	public void scaleUIMenus(Menu m2) {
		for (Menu m : getMenus()) {
			m.scaleUI(m2);
		}
	}
	
	public void tick() {
		if (loading) {
			tick = System.currentTimeMillis();
			return;
		}
		if (!loading && System.currentTimeMillis() - tick >= 2000) {
			if (state == State.running) {
				if (!j.r.s.started) {
					j.r.s.tick();
				}
				j.m.tick();
				j.k.tick();
				if (game != null) {
					if (game.isInGame()) {
						game.tick();
					}
				}
				for (Menu m : menus) {
					if (m.isSelected()) {
						if (!loading) {
							m.tick();
						}
					}
				}
			}
		}
	}
	
	public void render(Graphics g) {
		if (game != null) {
			if (game.isInGame()) {
				game.render(g);
			}
		}
		for (Menu m : menus) {
			if (m.isSelected()) {
				m.render(g);
			}
		}
	}
	
	public void mouseMoved(int mx, int my) {
		if (game != null) {
			if (game.isInGame()) {
				game.mouseMoved(mx, my);
			}
		}
		for (Menu m : menus) {
			if (m.isSelected()) {
				m.mouseMoved(mx, my);
			}
		}
	}
	
	public void mouseClicked(int mx, int my) {
		if (game != null) {
			if (!game.isInGame()) {
				game.mouseClicked(mx, my);
			}
		}
		for (Menu m : menus) {
			if (m.isSelected()) {
				m.mouseClicked(mx, my);
			}
		}
	}
	
	public void applyResolution(Menu m) {
		if (Options.selectRes == Options.currRes) {
			return;
		}
		
		state = State.loading;
		Options.applyResolution();
		loading = true;
		m.setSelected(false);
		j.w.frame.dispose();
		j.res = Options.currRes;
		j.setWidth(j.res.getWidth());
		j.setHeight(j.res.getHeight());
		j.w.r = j.res;
		j.w.createFrame();
		scaleUIMenus(m);
		m.setSelected(true);
		loading = false;
		state = State.running;
	}
	
	public void changeToGame(Game game, Menu menu) {
		j.l.startLoad();
		game.setInGame(true);
		game.getMaps().get(0).setSelected(true);
		menu.setSelected(false);
		for (UIObject obj : menu.getUiObjects()) {
			obj.setSelected(false);
		}
		j.l.stopLoad();
	}

	public void changeToMenu(Menu menu, Game game, Menu menu2) {
		j.l.startLoad();
		menu2.setSelected(false);
		for (UIObject obj : menu2.getUiObjects()) {
			obj.setSelected(false);
		}
		menu.setSelected(true);
		game.setInGame(false);
		for (Map m : game.getMaps()) {
			m.setSelected(false);
		}
		j.l.stopLoad();
	}
	
	public ArrayList<Menu> getMenus() {
		return menus;
	}

	public void setMenus(ArrayList<Menu> menus) {
		this.menus = menus;
	}
	
	public Menu getMenu(int id) {
		for (Menu m : menus) {
			if (m.getId() == id) {
				return m;
			}
		}
		Debugger.printError("MENU-ID: " + id + " | RETURNED NULL");
		return null;
	}
	
	public Menu getMenu(String name) {
		for (Menu m : menus) {
			if (m.getName().equalsIgnoreCase(name)) {
				return m;
			}
		}
		Debugger.printError("MENU-NAME: " + name + " | RETURNED NULL");
		return null;
	}
	
	public void changeMenu(Menu menu, Menu menu2) {
		j.l.startLoad();
		menu.setSelected(false);
		menu2.setSelected(true);
		for (UIObject obj : menu.getUiObjects()) {
			obj.setSelected(false);
		}
		j.l.stopLoad();
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}
	
}
