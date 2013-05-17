package rift.levels;

import java.util.ArrayList;

import rift.RObj;
import rift.objs.*;

public abstract class Level {

	protected ArrayList<RObj> objs;
	private int startX, startY, endX, endY;
	public static final int P_HEIGHT = 93;
	public static final int P_WIDTH = 77;
	private Player plr;
	private Exit exit;
	private Rift rift1, rift2;
	
	public Level(int sX, int sY, int eX, int eY, int face) {
		objs = new ArrayList<RObj>();
		plr = new Player(sX, sY, face, P_HEIGHT, P_WIDTH);
		exit = new Exit(eX, eY);
		rift1 = new Rift(2);
		rift2 = new Rift(3);
		objs.add(plr);
		objs.add(exit);
		objs.add(rift1);
		objs.add(rift2);
	}

	public ArrayList<RObj> getObjs() {
		return objs;
	}

	public int getStartX() {
		return startX;
	}

	public int getStartY() {
		return startY;
	}

	public int getEndX() {
		return endX;
	}

	public int getEndY() {
		return endY;
	}
	
	public abstract String getLevel();
	
	public abstract int getNextLevel();

}
