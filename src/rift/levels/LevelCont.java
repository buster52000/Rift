package rift.levels;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class LevelCont {

	private ArrayList<Level> lvls = new ArrayList<Level>();

	public LevelCont() {
		File dir = new File("bin/rift/levels/");
		File[] listT = dir.listFiles();
		if (listT != null && listT.length > 0) {
			ArrayList<File> list = new ArrayList<File>();
			for (File f : listT)
				list.add(f);

			for (File f : list) {
				if (f.isDirectory()) {
					System.out.println("Levels in subdirectories will not be loaded.");
					list.remove(f);
				}
			}

			if (list != null && list.size() > 0) {
				for (File f : list) {
					String name = "rift.levels." + f.getName().substring(0, f.getName().length() - 6);
					if (!name.equals("rift.levels.Level") && !name.equals("rift.levels.LevelCont")) {
						try {
							Level lvl = (Level) Class.forName(name).newInstance();
							lvls.add(lvl);
						} catch (InstantiationException e) {
							e.printStackTrace();
						} catch (IllegalAccessException e) {
							e.printStackTrace();
						} catch (ClassNotFoundException e) {
							e.printStackTrace();
						}
					}
				}

			} else {
				try {
					throw new FileNotFoundException("No files were found in the levels Folder");
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			}
		} else {
			try {
				throw new FileNotFoundException("No files were found in the levels Folder");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
//		lvls.add(new Lvl1());
//		lvls.add(new Lvl2());
	}

	public ArrayList<Level> getLvls() {
		return lvls;
	}

}