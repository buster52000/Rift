package rift;

import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JPanel;

public abstract class RObj implements Cloneable {

	private int x, y, height, width, type, actionReceiver;
	private boolean canMove, hasAction;
	protected int aX, aY, aHeight, aWidth;

	public RObj(int type, int locationX, int locationY, int height, int width, boolean movable, int actionReceiver, boolean hasAction) {
		this.actionReceiver = actionReceiver;
		this.hasAction = hasAction;
		this.type = type;
		x = locationX;
		y = locationY;
		this.height = height;
		this.width = width;
		canMove = movable;
		aX = x;
		aY = y;
		aHeight = height;
		aWidth = width;
	}

	/**
	 * Gets the X - coordinate of the object
	 * 
	 * @return x - X - coordinate of the object
	 */
	public int getX() {
		return x;
	}

	/**
	 * Gets the Y - coordinate of the object
	 * 
	 * @return y - Y - coordinate of the object
	 */
	public int getY() {
		return y;
	}

	public void setX(int x) {
		if (canMove && !UI.getResized()) {
			this.x = x;
			this.aX = UI.getScaledWidth(x);
			getPanel().setLocation(aX, getAY());
		}
	}

	public void setY(int y) {
		if (canMove && !UI.getResized()) {
			this.y = y;
			this.aY = UI.getScaledHeight(y);
			getPanel().setLocation(getAX(), aY);
		}
	}

	public void setAX(int x) {
		if (canMove && !UI.getResized()) {
			this.aX = x;
			int i = UI.getFWidth();
			if (i == 0)
				i = 1;
			this.x = (1300 * x) / i;
			getPanel().setLocation(x, getAY());
		}
	}

	public void setAY(int y) {
		if (canMove && !UI.getResized()) {
			this.aY = y;
			int i = UI.getFHeight();
			if (i == 0)
				i = 1;
			this.y = (700 * y) / i;
			getPanel().setLocation(getAX(), aY);
		}
	}

	/**
	 * Gets the length of the object
	 * 
	 * @return length - the length of the object
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * Gets the width of the object
	 * 
	 * @return width - the width of the object
	 */
	public int getWidth() {
		return width;
	}

	public int getAHeight() {
		return aHeight;
	}

	public int getAWidth() {
		return aWidth;
	}

	public int getAX() {
		return aX;
	}

	public int getAY() {
		return aY;
	}

	public boolean doesHaveAction() {
		return hasAction;
	}

	/**
	 * Returns if the object is movable by the player
	 * 
	 * @return canMove - if the object can be moved by the player
	 */
	public boolean isMoveable() {
		return canMove;
	}

	public int getType() {
		return type;
	}

	public int getActionReceiver() {
		return actionReceiver;
	}

	public void resize() {
		JPanel panel = getPanel();
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

	public abstract int getOrderLoc();

	public abstract boolean actionNeedIntersect();

	public abstract boolean riftCanIntersect();

	public abstract JPanel getPanel();

	public abstract boolean canIntersect();

	public abstract boolean canFireThrough();

	public abstract int constantAction(ArrayList<RObj> objs);

	public abstract int action(ArrayList<RObj> objs, int actionBy);

	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

}
