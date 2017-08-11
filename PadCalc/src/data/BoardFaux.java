package data;

import java.util.ArrayList;

public class BoardFaux extends Board {
	ArrayList<Combo> combos = new ArrayList<Combo>();
	
	public BoardFaux(int w, int h) {
		super(w, h);
	}

	public ArrayList<Combo> getCombos(Dungeon dungeon){ //TODO Bombs are not currently calculated for explosions
		return combos;
	}
}
