package data;

import java.util.ArrayList;

public class Board {
	public Board boardCopy;
	public int width;
	public int height;
	public Orb[][] orbsOriginal;
	public Orb[][] orbs;
	public boolean[][] womboed; // wombos mark when there are three orbs in a row
	public boolean[][] comboed; // combos mark the orbs have already been removed
	public Board(int w, int h){
		width = w;
		height = h;
		orbs = new Orb[width][height];
		orbsOriginal = orbs;
		womboed = new boolean[width][height]; //marks if three orbs are in a line
		comboed = new boolean[width][height]; 
	}
	
	public Board(Board board) {
		width = board.width;
		height = board.height;
		orbs = board.orbs;
		orbsOriginal = board.orbsOriginal;
		womboed = board.womboed;
		comboed = board.comboed;
	}

	/**
	* Characters are as follows
	* u - undefined
	* r - fire
	* b - water
	* g - wood
	* l - light
	* d - dark
	* h - heal
	* j - jammer
	* p - poison
	* m - mortal poison
	* o - bomb (b is for water)
	*/
	public void setBoard(char[] types, boolean[] enhances, boolean[] locks){
		if(height*width == types.length && height*width == enhances.length && height*width == locks.length){
			for (int y = 0; y < height; y++){
				for (int x = 0; x < width; x++){
					orbs[x][y] = new Orb(EnumOrbType.fromChar(types[x + y*width]), null, enhances[x + y*width], locks[x + y*width], false);
				}
			}
		}else{
			for (int y = 0; y < height; y++){
				for (int x = 0; x < width; x++){
					orbs[x][y] = new Orb(EnumOrbType.undefined, null, false, false, false);
				}
			}
		}
		orbsOriginal = orbs;
	}
	
	public void updateWomboed(Dungeon dungeon){
		EnumOrbType prevOrbType=null;
		int count = 0;
		for (int x = 0; x < width; x++){
			for (int y = 0; y < height; y++){
				if(prevOrbType != null && prevOrbType != EnumOrbType.undefined && prevOrbType != EnumOrbType.cleared && prevOrbType == orbs[x][y].getType()){
					count++;
					if(count==3){
						womboed[x][y] = true;
						womboed[x][y-1] = true;
						womboed[x][y-2] = true;
					}else if(count>3){
						womboed[x][y] = true;
					}
				}else{
					prevOrbType = orbs[x][y].getType();
					count = 1;
				}
			}
			prevOrbType=null;
			count = 0;
		}
		for (int y = 0; y < height; y++){
			for (int x = 0; x < width; x++){
				if(prevOrbType != null && prevOrbType != EnumOrbType.undefined && prevOrbType != EnumOrbType.cleared && prevOrbType == orbs[x][y].getType()){
					count++;
					if(count==3){
						womboed[x][y] = true;
						womboed[x-1][y] = true;
						womboed[x-2][y] = true;
					}else if(count>3){
						womboed[x][y] = true;
					}
				}else{
					prevOrbType = orbs[x][y].getType();
					count = 1;
				}
			}
			prevOrbType=null;
			count = 0;
		}
		for (int x = 0; x < width; x++){
			for (int y = 0; y < height; y++){
				if(womboed[x][y]){
					Orb[][] comboOrbs = previewCombo(x,y);
					Combo combo = new Combo(comboOrbs,width,height);
					if(combo.orbCount<dungeon.team.getMinOrbRequirement()){
						for (int yy = 0; yy < height; yy++){
							for (int xx = 0; xx < width; xx++){
								if(comboOrbs[xx][yy]!=null){
									womboed[xx][yy] = false;
								}
							}
						}
					}
					
				}
			}
		}
		
	}
	
	/**
	 * I think this gets the combos from the board without consuming the board, important feature
	 * I was stupid and didnt record comments for this, but I think this is right. 
	 * @param dungeon
	 * @return
	 */
	public ArrayList<Combo> getCombos(Dungeon dungeon){ //TODO Bombs are not currently calculated for explosions
		boardCopy = new Board(this);
		ArrayList<Combo> combos = new ArrayList<Combo>();
		ArrayList<Combo> c = null;
		while(c==null || c.size()>0){
			boardCopy.womboed = new boolean[boardCopy.width][boardCopy.height];
			boardCopy.comboed = new boolean[boardCopy.width][boardCopy.height];
			c = new ArrayList<Combo>();
			boardCopy.updateWomboed(dungeon);
			for (int y = 0; y < boardCopy.height; y++){
				for (int x = 0; x < boardCopy.width; x++){
					if(boardCopy.womboed[x][y] && !boardCopy.comboed[x][y])
						c.add(boardCopy.createCombo(x,y));
				}
			}//board is simulated once, now calculate cascades; rinse and repeat until no combos remain.
			combos.addAll(c);
			boardCopy.getCascade();
		}
		return combos;
	}
	
	/**
	 * I think this comboes the board and consumes the board in the process, leaving the leftover orbs in place
	 * I was stupid and didnt record comments for this, but I think this is right. 
	 * @param dungeon
	 * @return
	 */
	public ArrayList<Combo> combo(Dungeon dungeon){ //TODO Bombs are not currently calculated for explosions
		ArrayList<Combo> combos = new ArrayList<Combo>();
		ArrayList<Combo> c = null;
		while(c==null || c.size()>0){
			womboed = new boolean[width][height];
			comboed = new boolean[width][height];
			c = new ArrayList<Combo>();
			updateWomboed(dungeon);
			for (int y = 0; y < height; y++){
				for (int x = 0; x < width; x++){
					if(womboed[x][y] && !comboed[x][y])
						c.add(createCombo(x,y));
				}
			}//board is simulated once, now calculate cascades; rinse and repeat until no combos remain.
			combos.addAll(c);
			getCascade();
		}
		return combos;
	}
	
	@Override
	public String toString(){
		String s = "";
		for (int y = 0; y < height; y++){
			for (int x = 0; x < width; x++){
				s = s + orbs[x][y].type.toSymbol();
			}
			if(y+1 < height)
				s = s + "\n";
		}
		return s;
	}
	
	public void getCascade() {
		int drop = 0;
		for (int x = 0; x < width; x++){
			drop = 0;
			for (int y = height-1; y >= 0; y--){
				if(orbs[x][y].type==EnumOrbType.cleared){
					drop++;
					orbs[x][y] = new Orb(EnumOrbType.undefined, null, false, false, false);
				}else if(drop>0){
					orbs[x][y+drop] = orbs[x][y].copy();//Positive y is down
					orbs[x][y] = new Orb(EnumOrbType.undefined, null, false, false, false);
				}
			}
		}
	}

	private Combo createCombo(int w, int h){
		boolean expanded;
		EnumOrbType type = orbsOriginal[w][h].type;
		Orb[][] comboOrbs = new Orb[width][height];
		
		comboOrbs[w][h] = orbsOriginal[w][h].copy();
		comboed[w][h] = true;
		orbs[w][h].type = EnumOrbType.cleared;
		expanded = true;
		
		while(expanded){
			expanded = false;
			for (int y = 0; y < height; y++){
				for (int x = 0; x < width; x++){
					if(comboOrbs[x][y]!=null){ //if this orb was comboed, check adjacent orbs
						if(x+1<width && womboed[x+1][y] && !comboed[x+1][y] && orbs[x+1][y].type==type){
							comboOrbs[x+1][y] = orbsOriginal[x+1][y].copy();
							comboed[x+1][y] = true;
							this.orbs[x+1][y].type = EnumOrbType.cleared;
							expanded = true;
						}
						if(y+1<height && womboed[x][y+1] && !comboed[x][y+1] && orbs[x][y+1].type==type){
							comboOrbs[x][y+1] = orbsOriginal[x][y+1].copy();
							comboed[x][y+1] = true;
							this.orbs[x][y+1].type = EnumOrbType.cleared;
							expanded = true;
						}
						if(x-1>=0 && womboed[x-1][y] && !comboed[x-1][y] && orbs[x-1][y].type==type){
							comboOrbs[x-1][y] = orbsOriginal[x-1][y].copy();
							comboed[x-1][y] = true;
							this.orbs[x-1][y].type = EnumOrbType.cleared;
							expanded = true;
						}
						if(y-1>=0 && womboed[x][y-1] && !comboed[x][y-1] && orbs[x][y-1].type==type){
							comboOrbs[x][y-1] = orbsOriginal[x][y-1].copy();
							comboed[x][y-1] = true;
							this.orbs[x][y-1].type = EnumOrbType.cleared;
							expanded = true;
						}
					}
				}
			}
		}
		return new Combo(comboOrbs,width,height);
	}
	
	private Orb[][] previewCombo(int w, int h){
		boardCopy = new Board(this);
		boolean expanded;
		EnumOrbType type = boardCopy.orbsOriginal[w][h].type;
		Orb[][] comboOrbs = new Orb[boardCopy.width][boardCopy.height];
		
		comboOrbs[w][h] = boardCopy.orbsOriginal[w][h].copy();
		boardCopy.comboed[w][h] = true;
		boardCopy.orbs[w][h].type = EnumOrbType.cleared;
		expanded = true;
		
		while(expanded){
			expanded = false;
			for (int y = 0; y < boardCopy.height; y++){
				for (int x = 0; x < boardCopy.width; x++){
					if(comboOrbs[x][y]!=null){ //if this orb was comboed, check adjacent orbs
						if(x+1<boardCopy.width && boardCopy.womboed[x+1][y] && !boardCopy.comboed[x+1][y] && boardCopy.orbs[x+1][y].type==type){
							comboOrbs[x+1][y] = boardCopy.orbsOriginal[x+1][y].copy();
							boardCopy.comboed[x+1][y] = true;
							boardCopy.orbs[x+1][y].type = EnumOrbType.cleared;
							expanded = true;
						}
						if(y+1<boardCopy.height && boardCopy.womboed[x][y+1] && !boardCopy.comboed[x][y+1] && boardCopy.orbs[x][y+1].type==type){
							comboOrbs[x][y+1] = boardCopy.orbsOriginal[x][y+1].copy();
							boardCopy.comboed[x][y+1] = true;
							boardCopy.orbs[x][y+1].type = EnumOrbType.cleared;
							expanded = true;
						}
						if(x-1>=0 && boardCopy.womboed[x-1][y] && !boardCopy.comboed[x-1][y] && boardCopy.orbs[x-1][y].type==type){
							comboOrbs[x-1][y] = boardCopy.orbsOriginal[x-1][y].copy();
							boardCopy.comboed[x-1][y] = true;
							boardCopy.orbs[x-1][y].type = EnumOrbType.cleared;
							expanded = true;
						}
						if(y-1>=0 && boardCopy.womboed[x][y-1] && !boardCopy.comboed[x][y-1] && boardCopy.orbs[x][y-1].type==type){
							comboOrbs[x][y-1] = boardCopy.orbsOriginal[x][y-1].copy();
							boardCopy.comboed[x][y-1] = true;
							boardCopy.orbs[x][y-1].type = EnumOrbType.cleared;
							expanded = true;
						}
					}
				}
			}
		}
		return comboOrbs;
	}
}
