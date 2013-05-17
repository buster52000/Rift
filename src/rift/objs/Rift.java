package rift.objs;

import java.awt.BorderLayout;
import java.awt.Dimension;
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
		super(type, UI.MAX_WIDTH, UI.MAX_HEIGHT, Level.P_HEIGHT, Level.P_WIDTH, true, -1);
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
		try {
			if(getType() == 2)
				img = ImageIO.read(new File("pics/rRift.png"));
			else
				img = ImageIO.read(new File("pics/pRift.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		aX = UI.getScaledWidth(getX());
		aY = UI.getScaledHeight(getY());
		aHeight = UI.getScaledHeight(getHeight());
		aWidth = UI.getScaledWidth(getWidth());
		if (aWidth == 0)
			aWidth = 1; 
		if (aHeight == 0)
			aHeight = 1;
		img = img.getScaledInstance(aWidth, aHeight, Image.SCALE_SMOOTH);
		lbl.setIcon(new ImageIcon(img));
		rift.setSize(aWidth, aHeight);
		rift.setPreferredSize(new Dimension(aWidth, aHeight));
		rift.setLocation(aX, aY);
		rift.setMaximumSize(rift.getPreferredSize());
		rift.setMinimumSize(rift.getPreferredSize());
		rift.repaint();
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

	
	public void constantAction(ArrayList<RObj> objs) {
		
	}

	
	public void action(ArrayList<RObj> objs, int actionBy) {
		
	}

}
