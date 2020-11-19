package main.input.keyboard.keys;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

import main.input.keyboard.Keyboard;
import main.input.keyboard.keys.key.Key;


public class Keys {
	
	public static Key ZERO, ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, W, 
	X, Y, Z, PERIOD, SPACE, LEFT, RIGHT, UP, DOWN, BACKSPACE, ENTER, SHIFT, ESCAPE;
	
	public Keys() {
		initKeys();
	}
	
	private void initKeys() {
		ArrayList<Key> keys = (ArrayList<Key>) Keyboard.keys;
		ZERO = new Key(KeyEvent.VK_0, "0", keys);
		ONE = new Key(KeyEvent.VK_1, "1", keys);
		TWO = new Key(KeyEvent.VK_2, "2", keys);
		THREE = new Key(KeyEvent.VK_3, "3", keys);
		FOUR = new Key(KeyEvent.VK_4, "4", keys);
		FIVE = new Key(KeyEvent.VK_5, "5", keys);
		SIX = new Key(KeyEvent.VK_6, "6", keys);
		SEVEN = new Key(KeyEvent.VK_7, "7", keys);
		EIGHT = new Key(KeyEvent.VK_8, "8", keys);
		NINE = new Key(KeyEvent.VK_9, "9", keys);
		A = new Key(KeyEvent.VK_A, "A", keys);
		B = new Key(KeyEvent.VK_B, "B", keys);
		C = new Key(KeyEvent.VK_C, "C", keys);
		D = new Key(KeyEvent.VK_D, "D", keys);
		E = new Key(KeyEvent.VK_E, "E", keys);
		F = new Key(KeyEvent.VK_F, "F", keys);
		G = new Key(KeyEvent.VK_G, "G", keys);
		H = new Key(KeyEvent.VK_H, "H", keys);
		I = new Key(KeyEvent.VK_I, "I", keys);
		J = new Key(KeyEvent.VK_J, "J", keys);
		K = new Key(KeyEvent.VK_K, "K", keys);
		L = new Key(KeyEvent.VK_L, "L", keys);
		M = new Key(KeyEvent.VK_M, "M", keys);
		N = new Key(KeyEvent.VK_N, "N", keys);
		O = new Key(KeyEvent.VK_O, "O", keys);
		P = new Key(KeyEvent.VK_P, "P", keys);
		Q = new Key(KeyEvent.VK_Q, "Q", keys);
		R = new Key(KeyEvent.VK_R, "R", keys);
		S = new Key(KeyEvent.VK_S, "S", keys);
		T = new Key(KeyEvent.VK_T, "T", keys);
		U = new Key(KeyEvent.VK_U, "U", keys);
		V = new Key(KeyEvent.VK_V, "V", keys);
		W = new Key(KeyEvent.VK_W, "W", keys);
		X = new Key(KeyEvent.VK_X, "X", keys);
		Y = new Key(KeyEvent.VK_Y, "Y", keys);
		Z = new Key(KeyEvent.VK_Z, "Z", keys);
		PERIOD = new Key(KeyEvent.VK_PERIOD, ".", keys);
		SPACE = new Key(KeyEvent.VK_SPACE, " ", keys);
		LEFT = new Key(KeyEvent.VK_LEFT, "LEFT_ARROW", keys);
		RIGHT = new Key(KeyEvent.VK_RIGHT, "RIGHT_ARROW", keys);
		UP = new Key(KeyEvent.VK_UP, "DOWN_ARROW", keys);
		DOWN = new Key(KeyEvent.VK_DOWN, "UP_ARROW", keys);
		BACKSPACE = new Key(KeyEvent.VK_BACK_SPACE, "BACK_SPACE", keys);
		ENTER = new Key(KeyEvent.VK_ENTER, "ENTER", keys);
		SHIFT = new Key(KeyEvent.VK_SHIFT, "SHIFT", keys);
		ESCAPE = new Key(KeyEvent.VK_ESCAPE, "ESCAPE", keys);
	}

}
