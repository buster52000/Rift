package rift.objs;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JPanel;

import rift.RObj;

public class RiftBlock extends RObj {

	private JPanel panel;
	
	public RiftBlock(int locationX, int locationY, int height, int width) {
		super(7, locationX, locationY, height, width, false, -1, false);
		panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		resize();
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


	public int getOrderLoc() {
		return 4;
	}

	public boolean actionNeedIntersect() {
		return false;
	}

	public boolean riftCanIntersect() {
		return false;
	}
}
