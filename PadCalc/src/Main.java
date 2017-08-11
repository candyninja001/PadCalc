import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.font.TextAttribute;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.text.html.HTMLDocument.Iterator;

import data.BinaryData;
import data.Board;
import data.Dungeon;
import data.EnumAwakening;
import data.EnumLatentAwakening;
import data.EnumType;
import data.Floor;
import data.Monster;
import data.Team;
import data.skill.CardSkill;
import data.skill.leader.CoinBoost;
import data.skill.leader.LeaderSkill;
import data.skill.leader.LeaderSkillEffect;
import gui.MenuGUI;
import gui.TeamGUI;
import padherder.User;

public class Main {
	public static void main(String[] args){
		//download();
		CardSkill.loadSkillsFromJSON();
		LeaderSkill.loadLeaderSkillsFromJSON();
		Monster.loadMonstersFromJSON();
		EnumType.loadImages();
		EnumAwakening.loadImages();
		EnumLatentAwakening.loadImages();
		MenuGUI.createFonts();
		//testCombos();
		//skillMax();
		//testBinary();
		//testEnhance();
		testRegexes();
		testLeaderSkillReader();
		
		//User u = new User("candyninja");
		//u.loadMonsters();
		
		//testFont();
		//TeamGUI gui = new TeamGUI();
	}
	
	public static void testLeaderSkillReader() {
		int id = 3305;
		Monster m = Monster.monsters.get(id);
		if(m==null)
			return;
		System.out.println("Monster name: " + m.name);
		System.out.println("Leader skill name: " + m.leaderSkill.leaderSkillName);
		LeaderSkillEffect[] e = m.leaderSkill.getAllEffects();
		System.out.println("Original leader skill desc: " + m.leaderSkill.effect);
		for(int i = 0; i < e.length; i++){
			System.out.println(e.toString());
		}
	}

	public static void download(){
		BufferedImage image = null;
		for(int i = 1; i < 20; i++){
			try {
				URL url = new URL("http://puzzledragonx.com/en/img/latent/"+i+".png");
				image = ImageIO.read(url);
				File outputfile = new File("img/latent/"+(i-2)+".png");
				ImageIO.write(image, "png", outputfile);
			} catch (IOException e) {
			}
		}
	}
	
	public static void skillMax(){
		Monster mon = null;
		int m = 1;
		try{
			for(int i = 1; i < Monster.monsters.size(); i++){
				mon = Monster.monsters.get(i);
				if(mon!=null){
					CardSkill s = mon.activeSkill;
					if(mon.sellValue > 999 && s != null && s.maxCooldown > m){
						System.out.println("Found new max, \""+s.skillName+"\" with a max cooldown of "+s.maxCooldown);
						m = s.maxCooldown;
					}
				}
			}
			System.out.println("Done?");
		}catch(Exception e){
			System.out.println("Crashed at "+mon.id);
		}
	}
	
	public static void testCombos(){
		Board board = new Board(6,5);
		boolean[] nn = {false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false};
		board.setBoard("RGJJJJGGGLLLRGHHHHBBBBBBRDDDDD".toCharArray(), nn, nn);
		//board.setBoard("rdddddrdddddrdddddrdddddrddddd".toCharArray(), nn, nn);
		System.out.println(board.orbs[2][2].type);
		System.out.println(board.womboed[2][0]);
		System.out.println(board.getCombos(new Dungeon(board, new Team(), new Floor[]{})));
	}
	
	public static void testFont(){
		try {
			Font font = Font.createFont(Font.TRUETYPE_FONT, new File("PaD Font.otf"));
	        font = font.deriveFont(12f);
	        GraphicsEnvironment ge =
	            GraphicsEnvironment.getLocalGraphicsEnvironment();
	        ge.registerFont(font);

	        JLabel l = new JLabel(
	            "The quick brown fox jumps over the lazy dog. 0123456789");
	        l.setFont(font);
	        JOptionPane.showMessageDialog(null, l);
		} catch (FontFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void testBinary(){
		int i = 2969; // 00001011 10011001
		//TeamData data = new TeamData(b, 32);
		BinaryData data = new BinaryData(i, 16);
		//data.writeNext(TeamData.toBitSet(i), 16);
		System.out.println(i);
		System.out.println(Integer.toBinaryString(i));
		//System.out.println(b[0]+" "+b[1]+" "+b[2]+" "+b[3]+" ");
		System.out.println(data);
		//data.reset();
		System.out.println(data);
	}
	
	public static void testEnhance(){
		int e = 4;
		double enhanceMultiplier=(1.0+(0.06*e))*(1.0+(0.05*4));
		System.out.println(enhanceMultiplier);
		enhanceMultiplier=Math.floor(enhanceMultiplier*100.0)/100.0;
		System.out.println(enhanceMultiplier);
	}
	
	public static void testRegexes(){
		//(?<expBoost>[0-9.]+) 
		String regex = "Can no longer clear (?<restriction>\\d+)(?: | or less )connected orbs";
		String effect = "Can no longer clear 3 connected orbs. All attribute cards All Stats x1.5. ATK x4 at 3 combos. ATK x1 for each additional combo, up to ATK x7 at 6 combos.";
		Matcher m1 = Pattern.compile(regex).matcher(effect);
		while(m1.find()){
			for(int i = 0; i<=m1.groupCount(); i++){
				System.out.println("Found match " + i + " with value: " + m1.group(i));
			}
		}
	}
}
