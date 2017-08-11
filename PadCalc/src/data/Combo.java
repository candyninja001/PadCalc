package data;

import java.util.ArrayList;

public class Combo {
	public int boardWidth;
	public int boardHeight;
	
	public EnumOrbType orbType;
	public Orb[][] layout;
	public int orbCount;
	public int enhanceCount;
	
	public Combo(Orb[][] combo, int w, int h){
		boardWidth = w;
		boardHeight = h;
		layout = combo;
		orbType = EnumOrbType.undefined;
		defineOrbType();
		countOrbs();
	}
	
	@Override
	public String toString(){
		String s = orbCount+"x"+orbType.toString();
		if(this.enhanceCount>0)
			s += "("+enhanceCount+"+)";
		if(this.isRow()){
			s += " Row";
		}
		if(this.isCross()){
			s += " Cross";
		}
		if(this.isColumn()){
			s += " Column";
		}
		return s;
	}
	
	private void defineOrbType(){
		for (int y = 0; y < boardHeight; y++){
			for (int x = 0; x < boardWidth; x++){
				if(layout[x][y]!=null){
					orbType = layout[x][y].type;
				}
			}
		}
	}
	
	public void countOrbs(){
		orbCount = 0;
		enhanceCount = 0;
		for (int y = 0; y < boardHeight; y++){
			for (int x = 0; x < boardWidth; x++){
				if(layout[x][y]!=null){
					orbCount++;
					if(layout[x][y].enhanced)
						enhanceCount++;
				}
			}
		}
	}
	
	public boolean[][] getTrimmedLayout(){
		int newWidth = 0;
		int newHeight = 0;
		int wIndex = 0;
		int hIndex = 0;
		for (int x = 0; x < boardWidth; x++){
			if(isColumnPopulated(x)){
				newWidth++;
			}else if(newWidth==0){
				wIndex++;
			}
		}
		for (int y = 0; y < boardHeight; y++){
			if(isRowPopulated(y)){
				newHeight++;
			}else if(newHeight==0){
				hIndex++;
			}
		}
		boolean[][] trim = new boolean[newWidth][newHeight];
		for (int y = hIndex; y < hIndex + newHeight; y++){
			for (int x = wIndex; x < wIndex + newWidth; x++){
				trim[x-wIndex][y-hIndex] = (layout[x][y]!=null);
			}
		}
		return trim;
	}
	
	public boolean isRowPopulated(int row){
		for (int x = 0; x < boardWidth; x++){
			if(layout[x][row]!=null)
				return true;
		}
		return false;
	}
	
	public boolean isRowRow(int row){
		for (int x = 0; x < boardWidth; x++){
			if(layout[x][row]==null)
				return false;
		}
		return true;
	}
	
	public boolean isColumnColumn(int column){
		for (int y = 0; y < boardHeight; y++){
			if(layout[column][y]==null)
				return false;
		}
		return true;
	}
	
	public boolean isColumnPopulated(int column){
		for (int y = 0; y < boardHeight; y++){
			if(layout[column][y]!=null)
				return true;
		}
		return false;
	}
	
	public boolean isCross(){
		boolean[][] trim = getTrimmedLayout();
		if(trim.length==3 && trim[0].length==3)
			if(trim[0][0]==false && trim[1][0]==true && trim[2][0]==false)
				if(trim[0][1]==true && trim[1][1]==true && trim[2][1]==true)
					if(trim[0][2]==false && trim[1][2]==true && trim[2][2]==false)
						return true;
		return false;
	}
	
	public boolean isRow(){
		for (int y = 0; y < boardHeight; y++){
			if(isRowRow(y))
				return true;
		}
		return false;
	}
	
	public boolean isColumn(){
		for (int x = 0; x < boardWidth; x++){
			if(isColumnColumn(x))
				return true;
		}
		return false;
	}
}
