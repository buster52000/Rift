package rift.levels;

import rift.*;
import rift.objs.*;

import java.awt.Color;

public class Lvl2 extends Level {
	
	private String lvl = "Level 2";
	private int next = 0;

	public Lvl2() {
		super(5, UI.MAX_HEIGHT - 20 - P_HEIGHT, UI.MAX_WIDTH - 5 - P_WIDTH, UI.MAX_HEIGHT - 20 - P_HEIGHT, 2);
		objs.add(new Wall(0, UI.MAX_HEIGHT - 20, 20, UI.MAX_WIDTH, Color.BLACK));
		objs.add(new Wall(0, 350, 20, UI.MAX_WIDTH, Color.BLACK));
		objs.add(new Wall(300, 370, 330, 20, Color.BLACK, 8));
		objs.add(new Cube(200, 400));
		objs.add(new PressurePlate(Level.P_WIDTH + 20, UI.MAX_HEIGHT-30, 6));
	}
	
	public String getLevel() {
		return lvl;
	}
	
	public int getNextLevel() {
		return next;
	}

}
