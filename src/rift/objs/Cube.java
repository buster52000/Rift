package rift.objs;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JPanel;

import rift.RObj;
import rift.UI;

public class Cube extends RObj {

	private boolean fireThrough, intersect;
	private JPanel panel;

	public Cube(int locationX, int locationY) {
		super(8, locationX, locationY, 20, 20, true, -1);
		fireThrough = false;
		intersect = false;
		panel = new JPanel();
		panel.setBackground(Color.RED);
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
		return false;
	}

	public boolean canFireThrough() {
		return false;
	}

	public int constantAction(ArrayList<RObj> objs) {
		return 0;
	}

	public int action(ArrayList<RObj> objs, int actionBy) {
		Player plr = (Player) objs.get(0);
		if (panel.isEnabled()) {
			if (plr.invIsEmpty()) {
				intersect = true;
				fireThrough = true;
				panel.setEnabled(false);
				plr.setInvIsEmpty(false);
			}
		} else {
			if (plr.getFacing() == 1)
				setX(plr.getX() - getWidth());
			else
				setX(plr.getX() + plr.getWidth());
			setY(plr.getY() + plr.getHeight() / 2);
			intersect = false;
			fireThrough = false;
			panel.setEnabled(true);
			plr.setInvIsEmpty(true);
		}
		return 0;
	}
}
