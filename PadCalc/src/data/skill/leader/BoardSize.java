package data.skill.leader;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BoardSize extends LeaderSkillEffect {
	public static String name = "BoardSize";
	public static String regex = "Change the board to (?<width>\\d)x(?<height>\\d) size.";
	public int width = 6;
	public int height = 5;
	
	public BoardSize(int w, int h){
		width = w;
		height = h;
	}
	
	public static LeaderSkillEffect[] readLeaderSkill(String desc){
		Matcher m1 = Pattern.compile(regex).matcher(desc);
		while(m1.find()){
			String width = m1.group("width");
			String height = m1.group("height");
			
			int w = 3;
			int h = 3;
			
			if(width!=null)
				w = Integer.parseInt(width);
			if(height!=null)
				h = Integer.parseInt(height);
			
			boolean errorflag = false;
			if(w < 1){
				System.out.println("[BoardSize.readLeaderSkill.m1] Regexes detected a bizarre board size leader skill (" + w + "x" + h + "). Correcting width to 1.");
				errorflag = true;
				w = 1;
			}
			if(h < 1){
				System.out.println("[BoardSize.readLeaderSkill.m1] Regexes detected a bizarre board size leader skill (" + w + "x" + h + "). Correcting height to 1.");
				errorflag = true;
				h = 1;
			}
			if(w != h + 1 || h < 1 || w < 1){
				System.out.println("[BoardSize.readLeaderSkill.m1] Regexes detected a bizarre board size leader skill (" + w + "x" + h + "). Assuming this is correct, but it seems off.");
				errorflag = true;
			}
			if(errorflag)
				System.out.println("[BoardSize.readLeaderSkill] Below is the entire leader skill description from the recent errors above:\n[Combos.readLeaderSkill] \""+ desc +"\"");
				
			return new LeaderSkillEffect[]{new BoardSize(w, h)};
		}
		return new LeaderSkillEffect[]{};
	}
	
	public String toString(){
		return "Change the board to " + width + "x" + height + " size.";
	}
}
