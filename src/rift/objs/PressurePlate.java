package rift.objs;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JPanel;

import rift.Play;
import rift.RObj;
import rift.UI;

public class PressurePlate extends RObj {

	private JPanel panel;
	private boolean activated;

	public PressurePlate(int locationX, int locationY, int actionReceiver) {
		super(9, locationX, locationY, 10, 100, false, actionReceiver, true);
		panel = new JPanel();
		panel.setBackground(Color.RED);
		activated = false;
		resize();
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
		JPanel p = new JPanel();
		p.setLocation(getAX(), UI.getScaledHeight(getY() - 1));
		p.setSize(getAWidth(), UI.getScaledHeight(getHeight() - 1));
		boolean action = false;
		for (RObj o : objs)
			if (Play.doIntersect(o.getPanel(), p) && o.isMoveable() && o.getType() != 2 && o.getType() != 3 && o.getPanel().isEnabled())
				action = true;
		int receiver = 0;
		for (int i = 0; i < objs.size(); i++)
			if (objs.get(i).getPanel().getBounds().equals(panel.getBounds()))
				receiver = i;
		if (action) {
			if (!activated) {
				objs.get(getActionReceiver()).action(objs, receiver);
				panel.setBackground(Color.GREEN);
				activated = true;
			}
		} else {
			if (activated) {
				objs.get(getActionReceiver()).action(objs, receiver);
				panel.setBackground(Color.RED);
				activated = false;
			}
		}
		return 0;
	}

	public int action(ArrayList<RObj> objs, int actionBy) {
		return 0;
	}

}
