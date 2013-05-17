package rift;

import rift.objs.Player;

import java.util.ArrayList;

import javax.swing.JPanel;

public class PhysEngine {

	boolean stillFalling;
	int speed;
	int dFallen;
	private ArrayList<RObj> objs;

	public PhysEngine(ArrayList<RObj> objs) {
		this.objs = objs;
		stillFalling = false;
		speed = 1;
		dFallen = 0;
	}

	public void gravity(int jumpInProgress) {
		if (!UI.getResized()) {
			if (dFallen % 15 == 0 && speed <= 10)
				speed++;
			dFallen += speed;
			for (int i = 0; i < speed; i++) {
				if (jumpInProgress == 0) {
					Player plr = (Player) objs.get(0);
					JPanel p = new JPanel();
					p.setSize(plr.getAWidth(), plr.getAHeight());
					p.setLocation(plr.getAX(), UI.getScaledHeight(plr.getY() + 1));
					boolean canFall = true;
					for (RObj o : objs) {
						if (!o.canIntersect())
							if (Play.doIntersect(p, o.getPanel())) {
								canFall = false;
							}
					}
					if (canFall) {
						stillFalling = true;
						if (!UI.getResized())
							objs.get(0).setY(objs.get(0).getY() + 1);
					} else {
						stillFalling = false;
						dFallen = 0;
						speed = 1;
					}
				}
			}
		}
	}
}
