package rift.objs;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JPanel;

import rift.Play;
import rift.RObj;

public class Lava extends RObj {
	
	private JPanel lava;
	
	public Lava(int locationX, int locationY, int height, int width) {
		super(5, locationX, locationY, height, width, false, -1, false);
		lava = new JPanel();
		lava.setBackground(Color.ORANGE);
		resize();
	}
	
	public JPanel getPanel() {
		return lava;
	}
	
	public boolean canIntersect() {
		return true;
	}
	
	public boolean canFireThrough() {	
		return false;
	}

	public int constantAction(ArrayList<RObj> objs) {
		if(Play.doIntersect(objs.get(0).getPanel(), lava))
			return 1;
		return 0;
	}
	
	public int action(ArrayList<RObj> objs, int actionBy) {
		return 0;
	}

	public int getOrderLoc() {
		return 1;
	}


	public boolean actionNeedIntersect() {
		return false;
	}

	public boolean riftCanIntersect() {
		return false;
	}
}
