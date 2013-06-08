package rift;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class UI extends JFrame implements ComponentListener {

	private JFrame frame;
	private static int height;
	private static int width;
	public static final int MAX_WIDTH = 1300;
	public static final int MAX_HEIGHT = 700;
	private JPanel mainPanel;
	private static boolean resize;

	@SuppressWarnings("deprecation")
	public UI() {
		mainPanel = new JPanel();
		frame = new JFrame("Rift");
		frame.setExtendedState(MAXIMIZED_BOTH);
		frame.setMinimumSize(new Dimension(500, 200));
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		mainPanel.setLayout(null);
		frame.add(mainPanel);
		frame.show();
		frame.addComponentListener(this);
		resize = false;
		resize();
	}

	public void resize() {
		width = mainPanel.getWidth();
		height = mainPanel.getHeight();
	}

	@SuppressWarnings("deprecation")
	public void draw(ArrayList<JPanel> objs) {
		for (JPanel r : objs) {
			r.setBounds(new Rectangle(r.getX(), r.getY(), r.getWidth(), r.getHeight()));
			mainPanel.add(r);
		}
		frame.repaint();
		frame.show();
	}

	public static int getScaledHeight(int scale) {
		double x = (scale / 700.0) * height;
		return (int) Math.round(x);
	}

	public static int getScaledWidth(int scale) {
		double x = (scale / 1300.0) * width;
		return (int) Math.round(x);
	}

	public JFrame getJFrame() {
		return frame;
	}

	public static boolean getResized() {
		return resize;
	}

	public void finishResize() {
		try {
			Thread.sleep(15);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		resize = false;
	}

	public void componentHidden(ComponentEvent e) {

	}

	public void componentMoved(ComponentEvent e) {

	}

	public void componentResized(ComponentEvent e) {
		resize = true;
		resize();
	}

	public void componentShown(ComponentEvent e) {

	}

	public static int getFHeight() {
		return height;
	}

	public static int getFWidth() {
		return width;
	}

	
	public int getHeight() {
		return height;
	}
	
	public int getWidth() {
		return width;
	}

}
