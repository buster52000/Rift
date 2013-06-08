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

public class Player extends RObj {

	private int facing;
	private JPanel plr;
	private Image img;
	private JLabel lbl;
	private RObj inv;
	private int imageType;

	public Player(int x, int y, int face, int height, int width) {
		super(0, x, y, height, width, true, -1, false);
		facing = face;
		plr = new JPanel();
		lbl = new JLabel();
		plr.setOpaque(false);
		lbl.setOpaque(false);
		imageType = 0;
		try {
			if (imageType == 0) {
				if (facing == 1)
					img = ImageIO.read(new File("pics/plrL.png"));
				else
					img = ImageIO.read(new File("pics/plrR.png"));
			} else if(imageType == 1) {
				if (facing == 1)
					img = ImageIO.read(new File("pics/plrLCube.png"));
				else
					img = ImageIO.read(new File("pics/plrRCube.png"));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		lbl.setIcon(new ImageIcon(img));
		plr.setLayout(new BorderLayout());
		plr.add(lbl);
		inv = null;
		resize();
	}

	public int getFacing() {
		return facing;
	}

	public void setFacing(int facing) {
		this.facing = facing;
		try {
			if (imageType == 0) {
				if (facing == 1)
					img = ImageIO.read(new File("pics/plrL.png"));
				else
					img = ImageIO.read(new File("pics/plrR.png"));
			} else if(imageType == 1) {
				if (facing == 1)
					img = ImageIO.read(new File("pics/plrLCube.png"));
				else
					img = ImageIO.read(new File("pics/plrRCube.png"));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		img = img.getScaledInstance(aWidth, aHeight, Image.SCALE_DEFAULT);
		lbl.setIcon(new ImageIcon(img));
	}

	public JPanel getPanel() {
		return plr;
	}

	public void resize() {
		try {
			if (imageType == 0) {
				if (facing == 1)
					img = ImageIO.read(new File("pics/plrL.png"));
				else
					img = ImageIO.read(new File("pics/plrR.png"));
			} else if(imageType == 1) {
				if (facing == 1)
					img = ImageIO.read(new File("pics/plrLCube.png"));
				else
					img = ImageIO.read(new File("pics/plrRCube.png"));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		super.resize();
		if (aWidth == 0)
			aWidth = 1;
		if (aHeight == 0)
			aHeight = 1;
		img = img.getScaledInstance(aWidth, aHeight, Image.SCALE_DEFAULT);
		lbl.setIcon(new ImageIcon(img));
		plr.repaint();
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

	public RObj getInv() {
		return inv;
	}

	public void setInv(RObj inv) {
		this.inv = inv;
	}

	public int getOrderLoc() {
		return 0;
	}

	public void setImgType(int img) {
		imageType = img;
	}
	
	public boolean actionNeedIntersect() {
		return false;
	}

	public boolean riftCanIntersect() {
		return true;
	}

}
