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

public class Exit extends RObj {
	public static final int E_HEIGHT = 93;
	public static final int E_WIDTH = 77;
	private JPanel exit;
	private JLabel lbl;
	private Image img;
	
	public Exit(int locationX, int locationY) {
		super(1, locationX, locationY, E_HEIGHT, E_WIDTH, false, -1);
		exit = new JPanel();
		lbl = new JLabel();
		try {
			img = ImageIO.read(new File("pics/exit.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		exit.setLayout(new BorderLayout());
		lbl.setIcon(new ImageIcon(img));
		exit.add(lbl);
		resize();
	}

	
	public void resize() {
		aX = UI.getScaledWidth(getX());
		aY = UI.getScaledHeight(getY());
		aHeight = UI.getScaledHeight(getHeight());
		aWidth = UI.getScaledWidth(getWidth());
		exit.setSize(aWidth, aHeight);
		exit.setPreferredSize(new Dimension(aWidth, aHeight));
		exit.setLocation(aX, aY);
		exit.setMaximumSize(exit.getPreferredSize());
		exit.setMinimumSize(exit.getPreferredSize());
		try {
			img = ImageIO.read(new File("pics/exit.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (aWidth == 0)
			aWidth = 1; 
		if (aHeight == 0)
			aHeight = 1;
		img = img.getScaledInstance(aWidth, aHeight, Image.SCALE_DEFAULT);
		lbl.setIcon(new ImageIcon(img));
	}

	
	public JPanel getPanel() {
		return exit;
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