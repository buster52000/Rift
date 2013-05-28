package rift.objs;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JPanel;

import rift.RObj;
import rift.UI;

public class RiftBlock extends RObj {

	private JPanel panel;
	
	public RiftBlock(int locationX, int locationY, int height, int width) {
		super(7, locationX, locationY, height, width, false, -1);
		panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		resize();
	}

	
	public void resize() {
		aX = UI.getScaledWidth(getX());
		aY = UI.getScaledHeight(getY());
		aHeight = UI.getScaledHeight(getHeight());
		aWidth = UI.getScaledWidth(getWidth());
		panel.setSize(aWidth, aHeight);
		panel.setPreferredSize(new Dimension(aWidth, aHeight));
		panel.setLocation(aX, aY);
		panel.setMaximumSize(panel.getPreferredSize());
		panel.setMinimumSize(panel.getPreferredSize());		
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
