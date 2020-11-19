package main.graphics.window;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

import main.Janus;
import main.gamestate.State;
import main.graphics.window.resolution.Resolution;
import main.menus.Menu;
import main.options.Options;


public class Window extends Canvas {
	
	private static final long serialVersionUID = 1L;
	
	public JFrame frame;
	public Resolution r;
	private int id;
	private Janus j;
	
	public Window(Janus j, Resolution r, int id) {
		this.j = j;
		this.r = r;
		this.id = id;
		createFrame();
	}
	
	public void createFrame() {
		frame = new JFrame(j.getName());
		
		setMinimumSize(new Dimension(r.getWidth(), r.getHeight()));
		setMaximumSize(new Dimension(r.getWidth(), r.getHeight()));
		setPreferredSize(new Dimension(r.getWidth(), r.getHeight()));
		
		if (!j.isDecorated()) {
			frame.setUndecorated(true);
		}
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		
		frame.add(this, BorderLayout.CENTER);
		frame.pack();
		
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setFocusable(true);
		
		frame.requestFocus();
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String toString() {
		return "Window: " + getId() + " | Width: " + j.getWidth() + " | Height: " + j.getHeight(); 
	}
}
