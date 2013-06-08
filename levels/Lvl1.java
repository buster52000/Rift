package rift.levels;

import rift.*;
import rift.objs.*;

import java.awt.Color;

public class Lvl1 extends Level {
	
	private String lvl = "Level 1";
	private int next = 2;

	public Lvl1() {
		super(5, UI.MAX_HEIGHT - 20 - P_HEIGHT, UI.MAX_WIDTH - 5 - P_WIDTH, UI.MAX_HEIGHT - 20 - P_HEIGHT, 2);
		objs.add(new RiftBlock(0, 0, UI.MAX_HEIGHT, UI.MAX_WIDTH));
		objs.add(new RiftPlace(300, 300, -1));
		objs.add(new RiftPlace(500, 300, -1));
		objs.add(new Wall(0, UI.MAX_HEIGHT - 20, 20, UI.MAX_WIDTH, Color.BLACK));
		objs.add(new Cube(500, 600));
		objs.add(new PressurePlate(450, UI.MAX_HEIGHT - 30, 0));
	}
	
	public String getLevel() {
		return lvl;
	}
	
	public int getNextLevel() {
		return next;
	}

}
