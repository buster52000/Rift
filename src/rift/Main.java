package rift;

import javax.swing.JOptionPane;

public class Main {

	public static void main(String args[]) {
		String lvls[] = { "1", "2" };
		boolean playing = true;
		while (playing) {
			String lvl = null;
			lvl = (String) JOptionPane.showInputDialog(null, "Choose your level.", "Level Select", JOptionPane.QUESTION_MESSAGE, null, lvls, lvls[0]);
			if (lvl == null)
				System.exit(0);
			UI ui = new UI();
			Play play = new Play(ui);
			try {
				playing = play.playLevel(Integer.parseInt(lvl));
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
			ui.getJFrame().setVisible(false);
			ui.getJFrame().dispose();
		}
	}
}
