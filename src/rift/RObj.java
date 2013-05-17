package rift;

import java.util.ArrayList;

import javax.swing.JPanel;

public abstract class RObj implements Cloneable {

	private int x, y, height, width, type, actionReceiver;
	private boolean canMove;
	protected int aX, aY, aHeight, aWidth;

	public RObj(int type, int locationX, int locationY, int height, int width, boolean movable, int actionReceiver) {
		this.actionReceiver = actionReceiver;
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
			this.aX = x;
			int i = UI.getFWidth();
			if (i == 0)
				i = 1;
			this.x = (1300 * x) / i;
			getPanel().setLocation(aX, getAY());
		}
	}

	public void setY(int y) {
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

	public abstract void resize();

	public abstract JPanel getPanel();

	public abstract boolean canIntersect();

	public abstract boolean canFireThrough();

	public abstract void constantAction(ArrayList<RObj> objs);

	public abstract void action(ArrayList<RObj> objs, int actionBy);

	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

}
