package main;

import java.awt.Font;
import java.util.ArrayList;

import main.debug.Debugger;
import main.gameloop.GameLoop;
import main.gameloop.handler.Handler;
import main.graphics.drawgfx.DrawGFX;
import main.graphics.renderer.Renderer;
import main.graphics.window.Window;
import main.graphics.window.resolution.Resolution;
import main.input.keyboard.Keyboard;
import main.input.mouse.Mouse;
import main.load.Loading;
import main.options.Options;
import main.utils.SaveData;
import main.utils.SaveLoad;
import main.utils.profile.Profile;

public class Janus {

	public static final double JANUS_VERSION = 0.66;
	
	public Janus GET_JANUS;
	
	private String name, version;
	private int width, height;
	private boolean decorated = true;
	
	public boolean firstRun = true;
	
	public ArrayList<Profile> profiles = new ArrayList<Profile>();
	public Profile profile;
	
	public Resolution res;
	
	// Main Thread
	private Thread thread;
	
	// Engine Objects
	public Window w;
	public Renderer r;
	public GameLoop gl;
	public Handler h;
	public Mouse m;
	public Keyboard k;
	public DrawGFX dgfx;
	public Loading l;
	// End
	
	// Font
	public Font fnt = new Font("Serif", 1, 24);
	
	public Janus(String name, String version, int width, int height, boolean decorated, Thread thread) {
		this.name = name + " /\\ JANUS - " + JANUS_VERSION;
		this.version = version;
		this.width = width;
		this.height = height;
		res = new Resolution(width, height);
		this.thread = thread;
		this.decorated = decorated;
		init();
	}
	
	public void init() {
		SaveData frdata = SaveLoad.readFromFile("saves/fr");
		if (!frdata.getData().equalsIgnoreCase("complete")) {
			SaveLoad.writeToFile(new SaveData("complete"), "saves/fr");
			Debugger.printLine("First Time Set Up Complete");
			Options.currRes = res;
			Options.selectRes = res;
			Options.updateSelectResoText();
		} else {
			profiles = SaveLoad.readAnyExistingProfiles();
			Debugger.printLine("Successfully Loaded");
			firstRun = false;
		}
		Options.initRes();
		if (!firstRun) {
			SaveData resData = SaveLoad.readFromFile("saves/settings");
			Options.loadRes(resData);
			res = Options.currRes;
			width = res.getWidth();
			height = res.getHeight();
		}
		l = new Loading();
		w = new Window(this, res, 0);
		dgfx = new DrawGFX(this);
		gl = new GameLoop(this, thread);
		h = new Handler(this);
		r = new Renderer(this);
		m = new Mouse(this);
		k = new Keyboard(this);
		GET_JANUS = this;
		gl.start();
	}
	
	public void start() {
		gl.run();
	}
	
	public void exit() {
		SaveLoad.writeToFile(new SaveData(Options.currRes.getWidth() + ":" + Options.currRes.getHeight() + ":" + Options.currMonitor.id + ":" + Options.showFps), "saves/settings");
		for (Profile p : profiles) {
			p.saveProfile();
		}
		safeExit();
	}
	
	public boolean checkProfileLogin(String username, String password) {
		for (Profile p : profiles) {
			if (p.getUsername().equalsIgnoreCase(username) && p.getPassword().equalsIgnoreCase(password)) {
				p.active = true;
				profile = p;
				return true;
			}
		}
		return false;
	}
	
	private static void safeExit() {
		System.exit(0);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public boolean isDecorated() {
		return decorated;
	}

	public void setDecorated(boolean decorated) {
		this.decorated = decorated;
	}

	public Resolution getRes() {
		return res;
	}

	public void setRes(Resolution res) {
		this.res = res;
	}
	
}
