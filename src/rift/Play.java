package rift;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import rift.levels.*;
import rift.objs.Player;
import rift.objs.Rift;

public class Play implements KeyListener, MouseListener {

	private UI ui;
	private ArrayList<RObj> objs = new ArrayList<RObj>();
	private ArrayList<JPanel> panels = new ArrayList<JPanel>();
	private ArrayList<Integer> pressed = new ArrayList<Integer>();
	private int jumpInProgress;
	private int enterRift;
	private boolean exitPlay, exitGame;
	private static boolean pause;
	private Level[] lvls = new Level[2];

	public Play(UI ui) {
		this.ui = ui;
		jumpInProgress = 0;
		pause = false;
	}

	public boolean playLevel(int level) throws Exception {
		if (Ryan.isAwesome()) {
			exitGame = false;
			while (!exitGame) {
				exitPlay = false;
				LevelCont controller = new LevelCont();
				lvls = controller.getLvls();
				Level lvl = lvls[level - 1];
				if (!exitPlay && !pause) {
					ArrayList<RObj> temp = lvl.getObjs();
					for (RObj r : temp) {
						try {
							RObj temp2 = (RObj) r.clone();
							temp2.resize();
							objs.add(temp2);
							panels.add(temp2.getPanel());
						} catch (CloneNotSupportedException e) {
							e.printStackTrace();
						}
					}
					ui.draw(panels);
					ui.getJFrame().addKeyListener(this);
					ui.getJFrame().getComponent(0).addMouseListener(this);
					Player plr = (Player) objs.get(0);
					PhysEngine phys = new PhysEngine(objs);
					while (!exitPlay) {
						if (UI.getResized()) {
							resize();
							ui.finishResize();
						} else {
							JPanel p = new JPanel();
							p.setSize(plr.getAWidth(), plr.getAHeight());
							boolean canMove = true;
							try {
								int dir = 0;
								for (int t : pressed) {
									switch (t) {
									case 68:
										if (plr.getFacing() != 2)
											plr.setFacing(2);
										dir = 2;
										break;
									case 65:
										if (plr.getFacing() != 1)
											plr.setFacing(1);
										dir = -2;
										break;
									case 83:
										try {
											boolean cont = true;
											for (RObj j : objs) {
												if (cont) {
													if (j.getType() == 1) {
														if (doIntersect(plr.getPanel(), j.getPanel())) {
															level = lvl.getNextLevel();
															String o1[] = { "Menu", "Quit" };
															String o2[] = { "Next Level", "Menu", "Quit" };
															String options[];
															if (level == 0) {
																options = o1;
															} else {
																options = o2;
															}
															if (level == 0)
																JOptionPane.showMessageDialog(null, "You have completed the game!");
															int option = JOptionPane.showOptionDialog(null, "You completed " + lvl.getLevel() + "!", "Level Complete!", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, 0);
															exitPlay = true;
															if (level == 0) {
																ui.getJFrame().setVisible(false);
																ui.getJFrame().dispose();
																return !((options.length == 2 && (option == 1 || option == -1) || (options.length == 3 && (option == 2 || option == -1))));
															} else {
																if (option == 2)
																	return false;
																else if (option == 1)
																	return true;
																else if (option == 0 || option == -1) {
																	ui.getJFrame().setVisible(false);
																	ui.getJFrame().dispose();
																	ui = new UI();
																	objs.clear();
																	panels.clear();
																	pressed.clear();
																	jumpInProgress = 0;
																}
															}
														}
													} else if (j.getType() == 2 || j.getType() == 3) {
														if (doIntersect(plr.getPanel(), j.getPanel())) {
															Rift rift = null;
															if (j.getType() == 2) {
																for (RObj r : objs)
																	if (r.getType() == 3)
																		rift = (Rift) r;
															} else if (j.getType() == 3) {
																for (RObj r : objs)
																	if (r.getType() == 2)
																		rift = (Rift) r;
															}
															if (rift == null)
																throw new Exception("Rift not found");
															if (rift.isActivated()) {
																plr.setAX(rift.getAX());
																plr.setAY(rift.getAY());
																enterRift = rift.getType();
																pressed.remove(new Integer(83));
															}
															cont = false;
														}
													}
												}
											}
										} catch (ConcurrentModificationException e) {
										}
										break;
									}
									if (dir != 0 && !exitPlay && phys.getMomentum() == 0) {
										p.setLocation(UI.getScaledWidth(plr.getX() + dir), plr.getAY());
										for (RObj o : objs) {
											if (!o.canIntersect())
												if (doIntersect(p, o.getPanel()))
													canMove = false;
										}
										if ((plr.getX() - 1 < 0 && dir < 0) || (plr.getX() + Level.P_WIDTH + 1 > UI.MAX_WIDTH && dir > 0))
											canMove = false;
										if (canMove) {
											plr.setX(plr.getX() + dir);
											ui.getJFrame().repaint();
										}
									}
								}
								phys.momentum(dir);
							} catch (ConcurrentModificationException e) {
							}
							if (!exitPlay && !pause) {
								canMove = true;
								if (jumpInProgress != 0) {
									p.setLocation(plr.getAX(), UI.getScaledHeight(plr.getY() - 2));
									for (RObj o : objs) {
										if (!o.canIntersect())
											if (doIntersect(p, o.getPanel()))
												canMove = false;
									}
									if (canMove) {
										jumpInProgress--;
										plr.setY(plr.getY() - 2);
										ui.getJFrame().repaint();
									} else
										jumpInProgress = 0;
								} else {
									phys.gravity(jumpInProgress);
								}
								JPanel tP = new JPanel();
								tP.setLocation(UI.getScaledWidth(plr.getX() + (Level.P_WIDTH / 2 - 5)), UI.getScaledHeight(plr.getY() + (Level.P_HEIGHT / 2 - 5)));
								tP.setSize(UI.getScaledWidth(10), UI.getScaledHeight(10));
								if (enterRift != 3 && doIntersect(tP, objs.get(3).getPanel())) {
									if (((Rift) objs.get(2)).isActivated()) {
										plr.setAX(objs.get(2).getAX());
										plr.setAY(objs.get(2).getAY());
										enterRift = 2;
									}
								} else if (enterRift != 2 && doIntersect(tP, objs.get(2).getPanel())) {
									if (((Rift) objs.get(3)).isActivated()) {
										plr.setAX(objs.get(3).getAX());
										plr.setAY(objs.get(3).getAY());
										enterRift = 3;
									}
								}
								if (!doIntersect(plr.getPanel(), objs.get(3).getPanel()) && !doIntersect(plr.getPanel(), objs.get(2).getPanel()))
									enterRift = 0;
								try {
									Thread.sleep(7);
								} catch (InterruptedException e) {
								}
							}
							ArrayList<RObj> t = new ArrayList<RObj>();
							for (RObj o : objs) {
								t.add((RObj) o.clone());
							}
							for (RObj o : t) {
								int r = o.constantAction(objs);
								if(r != 0)
									doAction(r);
							}
						}
					}
				}
			}
		}
		return false;
	}

	public void resize() {
		for (RObj p : objs) {
			p.resize();
		}
		ui.getJFrame().repaint();
	}

	public void keyPressed(KeyEvent key) {
		int i = key.getKeyCode();
		if (i == 87) {
			if (jumpInProgress == 0) {
				Player plr = (Player) objs.get(0);
				JPanel p = new JPanel();
				p.setSize(plr.getAWidth(), plr.getAHeight());
				p.setLocation(plr.getAX(), UI.getScaledHeight(plr.getY() + 1));
				boolean canJump = false;
				for (RObj o : objs) {
					if (!o.canIntersect() && doIntersect(p, o.getPanel()))
						canJump = true;
				}
				if (canJump)
					jumpInProgress = (int) Level.P_HEIGHT / 2;
			}
		} else if (i == 68 || i == 65 || i == 83) {
			boolean temp = true;
			boolean exception = false;
			do {
				try {
					for (int t : pressed)
						if (t == i)
							temp = false;
				} catch (ConcurrentModificationException e) {
					exception = true;
				}
			} while (exception);
			if (temp)
				pressed.add(i);
		} else {
			System.out.println("Invalid Key - " + i);
		}
	}

	public static boolean doIntersect(JPanel p1, JPanel p2) {
		boolean Q = p1.getBounds().intersects(p2.getBounds());
		return Q;
	}

	public void doAction(int i) {
		switch (i) {
		case 1:
			restartLvl();
		}
	}

	public void restartLvl() {
		ui.getJFrame().setVisible(false);
		ui.getJFrame().dispose();
		ui = new UI();
		objs.clear();
		panels.clear();
		pressed.clear();
		jumpInProgress = 0;
		exitPlay = true;
	}

	public void keyReleased(KeyEvent key) {
		int i = key.getKeyCode();
		pressed.remove(new Integer(i));
	}

	public void keyTyped(KeyEvent arg0) {
	}

	public void mouseClicked(MouseEvent e) {

	}

	public void mouseEntered(MouseEvent e) {

	}

	public void mouseExited(MouseEvent e) {

	}

	public void mousePressed(MouseEvent e) {

	}

	public void mouseReleased(MouseEvent e) {
		int button = e.getButton();
		int x = e.getX() - objs.get(2).getAWidth() / 2;
		int y = e.getY() - objs.get(2).getAHeight() / 2;
		boolean good = true;
		int t = button;
		if (button == 1) {
			t = 2;
		}
		JPanel rift = new JPanel();
		rift.setSize(objs.get(t).getAWidth(), objs.get(t).getAHeight());
		rift.setLocation(x, y);
		for (RObj o : objs) {
			if (o.getType() != 0 && o.getType() != 2 && o.getType() != 3) {
				if (doIntersect(o.getPanel(), rift)) {
					good = false;
				}
			}
		}
		if (good) {
			Rift r = null;
			switch (button) {
			case 1:
				r = (Rift) objs.get(2);
				break;
			case 3:
				r = (Rift) objs.get(3);
				break;
			default:
				objs.get(2).setAX(UI.getFWidth());
				objs.get(2).setAY(UI.getFHeight());
				objs.get(3).setAX(UI.getFWidth());
				objs.get(3).setAY(UI.getFHeight());
				((Rift) objs.get(2)).setActive(false);
				((Rift) objs.get(3)).setActive(false);
				break;
			}
			if (r != null) {
				boolean temp = true;
				for (RObj o : objs) {
					if (!o.canFireThrough() && o.getPanel().getBounds().intersectsLine(new Line2D.Float(x, y, objs.get(0).getAX() + objs.get(0).getAWidth() / 2, objs.get(0).getAY() + objs.get(0).getAHeight() / 2))) {
						temp = false;
					}
				}
				if (temp) {
					r.setAX(x);
					r.setAY(y);
					r.setActive(true);
				}
			}
		}
	}
}