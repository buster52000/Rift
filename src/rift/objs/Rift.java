package rift.objs;

import java.awt.BorderLayout;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import rift.RObj;
import rift.UI;
import rift.levels.Level;

public class Rift extends RObj {

	private JPanel rift;
	private JLabel lbl;
	private Image img;
	private boolean active;
	
	public Rift(int type) {
		super(type, UI.MAX_WIDTH, UI.MAX_HEIGHT, Level.P_HEIGHT, Level.P_WIDTH, true, -1, false);
		active = false;
		rift = new JPanel();
		lbl = new JLabel();
		rift.setOpaque(false);
		lbl.setOpaque(false);
		try {
			if(getType() == 2)
				img = ImageIO.read(new File("pics/rRift.png"));
			else
				img = ImageIO.read(new File("pics/pRift.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		rift.setLayout(new BorderLayout());
		lbl.setIcon(new ImageIcon(img));
		rift.add(lbl);
		resize();
	}

	public void resize() {
		super.resize();
		try {
			if(getType() == 2)
				img = ImageIO.read(new File("pics/rRift.png"));
			else
				img = ImageIO.read(new File("pics/pRift.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (aWidth == 0)
			aWidth = 1; 
		if (aHeight == 0)
			aHeight = 1;
		img = img.getScaledInstance(aWidth, aHeight, Image.SCALE_SMOOTH);
		lbl.setIcon(new ImageIcon(img));
	}

	public JPanel getPanel() {
		return rift;
	}
	
	public boolean isActivated() {
		return active;
	}
	
	public void setActive(boolean active) {
		this.active = active;
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
		return 3;
	}

	public boolean actionNeedIntersect() {
		return false;
	}

	public boolean riftCanIntersect() {
		return false;
	}

}
