package rift.objs;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JPanel;

import rift.*;

public class Wall extends RObj {

	private JPanel wall;

	public Wall(int rLocationX, int rLocationY, int rHeight, int rWidth, Color color, int actionReceiver) {
		super(4, rLocationX, rLocationY, rHeight, rWidth, true, actionReceiver);
		wall = new JPanel();
		wall.setBackground(color);
		resize();
	}
	
	public Wall(int rLocationX, int rLocationY, int rHeight, int rWidth, Color color) {
		super(4, rLocationX, rLocationY, rHeight, rWidth, false, -1);
		wall = new JPanel();
		wall.setBackground(color);
		resize();
	}

	public void resize() {
		aX = UI.getScaledWidth(getX());
		aY = UI.getScaledHeight(getY());
		aHeight = UI.getScaledHeight(getHeight());
		aWidth = UI.getScaledWidth(getWidth());
		wall.setSize(aWidth, aHeight);
		wall.setPreferredSize(new Dimension(aWidth, aHeight));
		wall.setLocation(aX, aY);
		wall.setMaximumSize(wall.getPreferredSize());
		wall.setMinimumSize(wall.getPreferredSize());
	}

	public JPanel getPanel() {
		return wall;
	}

	
	public boolean canIntersect() {
		return false;
	}

	
	public boolean canFireThrough() {
		if(wall.getBackground().equals(Color.BLACK))
			return false;
		return true;
	}

	
	public void constantAction(ArrayList<RObj> objs) {
		
	}
	
	public void action(ArrayList<RObj> objs, int actionBy) {		
		if(isMoveable() && actionBy == getActionReceiver()) {
			
		}
	}
}
