package rift.levels;

import rift.*;
import rift.objs.Lava;
import rift.objs.Wall;

import java.awt.Color;

public class Lvl2 extends Level {
	
	private String lvl = "Level 2";
	private int next = 0;

	public Lvl2() {
		super(UI.MAX_WIDTH - 5 - P_WIDTH, UI.MAX_HEIGHT - 20 - P_HEIGHT, 5, UI.MAX_HEIGHT - 20 - P_HEIGHT, 1);
		objs.add(new Wall(0, UI.MAX_HEIGHT - 20, 20, UI.MAX_WIDTH, Color.BLACK));
		objs.add(new Lava(200, UI.MAX_HEIGHT - 40, 20, 300));
	}
	
	public String getLevel() {
		return lvl;
	}
	
	public int getNextLevel() {
		return next;
	}

}
