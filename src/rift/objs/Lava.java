package rift.objs;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JPanel;

import rift.Play;
import rift.RObj;
import rift.UI;

public class Lava extends RObj {
	
	private JPanel lava;
	
	public Lava(int locationX, int locationY, int height, int width) {
		super(5, locationX, locationY, height, width, false, -1);
		lava = new JPanel();
		lava.setBackground(Color.ORANGE);
		resize();
	}

	
	public void resize() {
		aX = UI.getScaledWidth(getX());
		aY = UI.getScaledHeight(getY());
		aHeight = UI.getScaledHeight(getHeight());
		aWidth = UI.getScaledWidth(getWidth());
		lava.setSize(aWidth, aHeight);
		lava.setPreferredSize(new Dimension(aWidth, aHeight));
		lava.setLocation(aX, aY);
		lava.setMaximumSize(lava.getPreferredSize());
		lava.setMinimumSize(lava.getPreferredSize());		
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
}
