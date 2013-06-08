package rift.objs;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JPanel;

import rift.PhysEngine;
import rift.Play;
import rift.RObj;
import rift.levels.Level;

public class Cube extends RObj {

	private boolean fireThrough, intersect, needIntersect, riftIntersect;
	private JPanel panel;
	private PhysEngine phy = null;
	private int inRift;

	public Cube(int locationX, int locationY) {
		super(8, locationX, locationY, 40, 40, true, -1, true);
		fireThrough = false;
		intersect = false;
		panel = new JPanel();
		panel.setBackground(Color.BLUE);
		needIntersect = true;
		riftIntersect = false;
		inRift = 0;
		resize();
	}

	public JPanel getPanel() {
		return panel;
	}

	public boolean canIntersect() {
		return intersect;
	}

	public boolean canFireThrough() {
		return fireThrough;
	}

	public int constantAction(ArrayList<RObj> objs) {
		if (phy == null)
			phy = new PhysEngine(objs);
		if (panel.isEnabled())
			phy.gravity(this);
		if (inRift == 0 && (Play.doIntersect(panel, objs.get(2).getPanel()) || Play.doIntersect(panel, objs.get(3).getPanel()))) {
			if (Play.doIntersect(panel, objs.get(2).getPanel()))
				inRift = 3;
			else
				inRift = 2;
			if (((Rift) objs.get(2)).isActivated() && ((Rift) objs.get(3)).isActivated()) {
				setX(objs.get(inRift).getX() + Level.P_WIDTH / 2);
				setY(objs.get(inRift).getY() + Level.P_HEIGHT / 2);
			}
		} else if (inRift != 0 && !Play.doIntersect(panel, objs.get(inRift).getPanel()))
			inRift = 0;
		return 0;
	}

	public int action(ArrayList<RObj> objs, int actionBy) {
		Player plr = (Player) objs.get(0);
		needIntersect = !needIntersect;
		if (panel.isEnabled()) {
			if (plr.getInv() == null) {
				intersect = true;
				fireThrough = true;
				riftIntersect = true;
				panel.setEnabled(false);
				panel.setOpaque(false);
				plr.setInv(this);
				plr.setImgType(1);
			}
		} else {
			JPanel p = new JPanel();
			if (plr.getFacing() == 1)
				p.setLocation(plr.getAX() - getAWidth(), plr.getAY() + plr.getAHeight() / 2);
			else
				p.setLocation(plr.getAX() + plr.getAWidth(), plr.getAY() + plr.getAHeight() / 2);
			p.setSize(getAWidth(), getAHeight());
			boolean good = true;
			for (RObj o : objs)
				if (Play.doIntersect(o.getPanel(), p) && !o.canIntersect())
					good = false;
			if (good) {
				setAX((int) p.getLocation().getX());
				setAY((int) p.getLocation().getY());
				intersect = false;
				fireThrough = false;
				panel.setEnabled(true);
				panel.setOpaque(true);
				riftIntersect = false;
				plr.setInv(null);
				plr.setImgType(0);
			}
		}
		plr.setFacing(plr.getFacing());
		return 0;
	}

	public int getOrderLoc() {
		return 0;
	}

	public boolean actionNeedIntersect() {
		return needIntersect;
	}

	public boolean riftCanIntersect() {
		return riftIntersect;
	}

}
