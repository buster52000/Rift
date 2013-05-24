package rift.levels;

public class LevelCont {

	private int numLvls;
	private Level lvls[];

	public LevelCont() {
		numLvls = 2;
		lvls = new Level[numLvls];
		lvls[0] = new Lvl1();
		lvls[1] = new Lvl2();
	}
	
	public Level[] getLvls() {
		return lvls;
	}
	
}
