package main.options;

import java.awt.DisplayMode;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.util.ArrayList;

import main.graphics.renderer.Renderer;
import main.graphics.window.resolution.Monitor;
import main.graphics.window.resolution.Resolution;
import main.load.Loading;
import main.utils.SaveData;

public class Options {
	
	public static boolean showFps = false;
	public static Resolution currRes, selectRes;
	public static String selectResoText;
	public static Monitor currMonitor;
	
	public static ArrayList<Monitor> monitors = new ArrayList<Monitor>();
	
	public Options () {
		
	}
	
	public static void initRes() {
	    GraphicsDevice[] devices = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices();
        for (int i = 0; i < devices.length; i++) {
            GraphicsDevice dev = devices[i];
            DisplayMode[] modes = dev.getDisplayModes();
            ArrayList<Resolution> rta = new ArrayList<Resolution>();
            for (int j = 0; j < modes.length; j++) {
                DisplayMode m = modes[j];
                if (j > 0) {
                	boolean canAdd = true;
	                for (int k = 0; k < rta.size(); k++) {
	                	Resolution r = rta.get(k);
	                	if (r.getWidth() == m.getWidth() && r.getHeight() == m.getHeight()) {
	                		canAdd = false;
	                	}
	                }
	                if (canAdd) {
	                	rta.add(new Resolution(m.getWidth(), m.getHeight()));
	                }
                } else {
                	rta.add(new Resolution(m.getWidth(), m.getHeight()));
                }
            }
            monitors.add(new Monitor(i, rta));
        }
        
        currMonitor = monitors.get(0);
	}
	
	public static void changeResolution(int ud) {
		for (int i = 0; i < currMonitor.resos.size(); i++) {
			Resolution r = currMonitor.resos.get(i);
			if (r.getWidth() == selectRes.getWidth() && r.getHeight() == selectRes.getHeight()) {
				if (i == currMonitor.resos.size() - 1 && ud == 1) {
					selectRes = currMonitor.resos.get(0);
					updateSelectResoText();
					break;
				} else if (i == 0 && ud == 3) {
					selectRes = currMonitor.resos.get(currMonitor.resos.size() - 1);
					updateSelectResoText();
					break;
				} else {
					if (ud == 1) {
						selectRes = currMonitor.resos.get(i + 1);
						updateSelectResoText();
						break;
					} else if (ud == 3) {
						selectRes = currMonitor.resos.get(i - 1);
						updateSelectResoText();
						break;
					}
				}
			}
		}
	}
	
	public static void updateSelectResoText() {
		selectResoText = selectRes.getWidth() + "x" + selectRes.getHeight();
	}
	
	public static void applyResolution() {
		currRes = selectRes;
	}
	
	public static void loadRes(SaveData data) {
		String split[] = data.getData().split(":");
		int wid = Integer.parseInt(split[0]);
		int hei = Integer.parseInt(split[1]);
		int mon = Integer.parseInt(split[2]);
		boolean fps = Boolean.parseBoolean(split[3]);
		Renderer.showFps = fps;
		showFps = fps;
		for (Resolution r : monitors.get(mon).resos) {
			if (wid == r.getWidth() && hei == r.getHeight()) {
				currRes = r;
				selectRes = r;
				updateSelectResoText();
			}
		}
	}
	
	public static void setShowFps(boolean fps) {
		showFps = fps;
		Renderer.showFps = fps;
	}
	
}
