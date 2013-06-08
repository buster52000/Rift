package rift.objs;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JPanel;

import rift.*;

public class Wall extends RObj {

	private JPanel wall;
	private boolean canFire, canIntersect, riftIntersect, canOpen;

	public Wall(int rLocationX, int rLocationY, int rHeight, int rWidth, Color color, int actionSender) {
		super(4, rLocationX, rLocationY, rHeight, rWidth, false, actionSender, false);
		canOpen = true;
		wall = new JPanel();
		wall.setBackground(color);
		canFire = false;
		canIntersect = false;
		resize();
	}
	
	public Wall(int rLocationX, int rLocationY, int rHeight, int rWidth, Color color) {
		super(4, rLocationX, rLocationY, rHeight, rWidth, false, -1, false);
		canOpen = false;
		wall = new JPanel();
		wall.setBackground(color);
		canFire = false;
		canIntersect = false;
		resize();
	}

	public JPanel getPanel() {
		return wall;
	}

	
	public boolean canIntersect() {
		return canIntersect;
	}

	
	public boolean canFireThrough() {
		if(wall.getBackground().equals(Color.GRAY))
			return true;
		return canFire;
	}

	
	public int constantAction(ArrayList<RObj> objs) {
		return 0;
	}
	
	public int action(ArrayList<RObj> objs, int actionBy) {		
		if(canOpen && actionBy == getActionReceiver()) {
			wall.setEnabled(!wall.isEnabled());
			wall.setOpaque(!wall.isOpaque());
			canFire = !canFire;
			canIntersect = !canIntersect;
		}
		return 0;
	}

	public int getOrderLoc() {
		return 1;
	}
	
	public boolean actionNeedIntersect() {
		return false;
	}

	public boolean riftCanIntersect() {
		return riftIntersect;
	}
}
