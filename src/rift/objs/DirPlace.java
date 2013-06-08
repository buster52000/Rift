package rift.objs;

import java.util.ArrayList;

import javax.swing.JPanel;

import rift.RObj;
import rift.levels.Level;

public class DirPlace extends RObj {

	JPanel panel;
	
	public DirPlace(int locationX, int locationY, int actionSender, int dir) {
		super(11, locationX, locationY, Level.P_HEIGHT, Level.P_WIDTH, false, actionSender, false);
		panel = new JPanel();
		resize();
	}

	public int getOrderLoc() {
		return 3;
	}

	public boolean actionNeedIntersect() {
		return false;
	}

	public boolean riftCanIntersect() {
		return true;
	}

	public JPanel getPanel() {
		return panel;
	}

	public boolean canIntersect() {
		return true;
	}

	public boolean canFireThrough() {
		return true;
	}

	public int constantAction(ArrayList<RObj> objs) {
		return 0;
	}

	public int action(ArrayList<RObj> objs, int actionBy) {
		return 0;
	}

}
