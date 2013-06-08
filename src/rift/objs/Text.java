package rift.objs;

import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;

import rift.RObj;

public class Text extends RObj {
	
	private JPanel text = new JPanel();
	private String txt;
	private JLabel lbl;

	public Text(int locationX, int locationY, int height, int width, String txt, int textSize) {
		super(6, locationX, locationY, height, width, false, -1, false);
		this.txt = txt;
		text = new JPanel();
		lbl = new JLabel(this.txt);
		lbl.setFont(new Font("Times New Roman", Font.PLAIN, textSize));
		text.add(lbl);
		resize();
	}
	
	public JPanel getPanel() {
		
		return text;
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
		return 0;
	}

	public boolean actionNeedIntersect() {
		return false;
	}

	public boolean riftCanIntersect() {
		return true;
	}

}
