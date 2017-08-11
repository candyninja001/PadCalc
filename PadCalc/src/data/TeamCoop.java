package data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import javax.xml.bind.DatatypeConverter;

import org.json.JSONObject;

public class TeamCoop extends Team{
	public TeamCoop startTeam; // Cards get modified and moved in dungeons from time to time, this acts as a reset option.
	public String teamName;
	public Card p1Lead;
    public Card[] p1Subs;
    public Card p2Lead;
    public Card[] p2Subs;
    public int turn;
    public boolean p1Pass;
    public boolean p2Pass;
    public int p1PreviousSwap;
    public int p2PreviousSwap;
    
    public TeamCoop(){
        turn = 1;
        p1Pass = true;
        p2Pass = true;
        p1Lead = new Card((Monster)null); 
        p1Lead.inherit = new Card((Monster)null);
        p1Subs = new Card[4];
        p1Subs[0] = new Card((Monster)null);
        p1Subs[0].inherit = new Card((Monster)null);
        p1Subs[1] = new Card((Monster)null);
        p1Subs[1].inherit = new Card((Monster)null);
        p1Subs[2] = new Card((Monster)null);
        p1Subs[2].inherit = new Card((Monster)null);
        p1Subs[3] = new Card((Monster)null);
        p1Subs[3].inherit = new Card((Monster)null);
        p2Lead = new Card((Monster)null);
        p2Lead.inherit = new Card((Monster)null);
        p2Subs = new Card[4];
        p2Subs[0] = new Card((Monster)null);
        p2Subs[0].inherit = new Card((Monster)null);
        p2Subs[1] = new Card((Monster)null);
        p2Subs[1].inherit = new Card((Monster)null);
        p2Subs[2] = new Card((Monster)null);
        p2Subs[2].inherit = new Card((Monster)null);
        p2Subs[3] = new Card((Monster)null);
        p2Subs[3].inherit = new Card((Monster)null);
        p1PreviousSwap = -1;
        p2PreviousSwap = -1;
    }
    
    @Override 
    public void copy(Team team){
    	if (team instanceof TeamCoop){
	        turn = ((TeamCoop)team).turn;
	        p1Pass = ((TeamCoop)team).p1Pass;
	        p2Pass = ((TeamCoop)team).p2Pass;
	        p1Lead = ((TeamCoop)team).p1Lead; 
	        // shouldnt be needed but here just in case,
	        // p1Lead.inherit = ((TeamCoop)team).p1Lead.inherit
	        p1Subs = ((TeamCoop)team).p1Subs;
	        p2Lead = ((TeamCoop)team).p2Lead;
	        p2Subs = ((TeamCoop)team).p2Subs;
	        p1PreviousSwap = ((TeamCoop)team).p1PreviousSwap;
	        p2PreviousSwap = ((TeamCoop)team).p2PreviousSwap;
    	}
    }
    
    @Override 
    public void editTeam(){
    	if(startTeam!=null)
    		this.copy(startTeam);
    	startTeam = null; //MUST RESET TO NULL OTHERWISE STARTTEAM WILL INFINITELY NEST UPON ENTERING
    	turn = 1;
        p1Pass = true;
        p2Pass = true;
    	p1PreviousSwap = -1;
        p2PreviousSwap = -1;
    }
    
    @Override 
    public void enterDungeon(){
    	startTeam = null; //MUST RESET TO NULL OTHERWISE STARTTEAM WILL INFINITELY NEST UPON ENTERING
    	startTeam = this;
    	turn = 1;
        p1Pass = true;
        p2Pass = true;
    	p1PreviousSwap = -1;
        p2PreviousSwap = -1;
    }
    
    @Override
    public void nextTurn(){
        if(turn == 2)
            turn = 1;
        else
            turn = 2;
    }
    
    @Override
	public boolean isMultiplayer(){
		return false;
    	//TODO return true;
	}
    
    public boolean canPass(){
        if(turn==1)
            return p1Pass;
        else
            return p2Pass;
    }
    
    public void pass(){
        if(turn==1){
            p1Pass=false;
            nextTurn();
        }else{
            p2Pass=false;
            nextTurn();
        }
    }
    
    @Override
    public int getTeamCost(int player){
    	int cost = 0;
    	if(player == 1){
    		if(this.p1Lead != null && this.p1Lead.monster != null)
        		cost += this.p1Lead.monster.teamCost;
    		for(int i = 0; i < p1Subs.length; i++)
    			if(this.p1Subs[i] != null && this.p1Subs[i].monster != null)
    	    		cost += this.p1Subs[i].monster.teamCost;
    	}else if(player == 2){
    		if(this.p2Lead != null && this.p2Lead.monster != null)
        		cost += this.p2Lead.monster.teamCost;
    		for(int i = 0; i < p2Subs.length; i++)
    			if(this.p2Subs[i] != null && this.p2Subs[i].monster != null)
    	    		cost += this.p2Subs[i].monster.teamCost;
    	}
    	return cost;
    }
    
    @Override
    public int getTeamCostWithBadge(int player){
    	int cost = getTeamCost(player);
    	if(cost>100)
    		return cost-100;
    	return 0;
    }
    
    /**
     * Return the card for the team with leads at 0, 5 and subs at 1-4, 6-9
     */
    public Card getCard(int i){
    	if(i == 0)
    		return p1Lead;
    	if(i > 0 && i < 5)
    		return p1Subs[i-1];
    	if(i == 5)
    		return p2Lead;
    	if(i > 5 && i < 10)
    		return p2Subs[i-6];
    	if(i == 10)
    		return p1Lead.inherit;
    	if(i > 10 && i < 15)
    		return p1Subs[i-11].inherit;
    	if(i == 15)
    		return p2Lead.inherit;
    	if(i > 15 && i < 20)
    		return p2Subs[i-16].inherit;
    	return null;
    }
    
    /**
     * Sets the card for the team with leads at 0, 5 and subs at 1-4, 6-9
     */
    public void setCard(int i, Card c){
    	if(i == 0)
    		p1Lead = c;
    	else if(i > 0 && i < 5)
    		p1Subs[i-1] = c;
    	else if(i == 5)
    		p2Lead = c;
    	else if(i > 5 && i < 10)
    		p2Subs[i-6] = c;
    	else if(i == 10)
    		p1Lead.inherit = c;
    	else if(i > 10 && i < 15)
    		p1Subs[i-11].inherit = c;
    	else if(i == 15)
    		p2Lead.inherit = c;
    	else if(i > 15 && i < 20)
    		p2Subs[i-16].inherit = c;
    }
    
    @Override
    public Card getLeader(){
    	if(turn==1)
    		return p1Lead;
    	else
    		return p2Lead;
    }
    
    @Override
    public Card getSub(int i){
        if(i < 0 || i > 3)
            return null;
        if(turn==1)
        	return p1Subs[i];
        else
        	return p2Subs[i];
    }
    
    @Override
    public Card getFriendLeader(){
    	if(turn==1)
        	return p2Lead;
        else
    		return p1Lead;
    }
    
    @Override
    public Card[] getAllCards(){
    	Card[] cards = new Card[10];
    	cards[0] = p1Lead;
    	cards[1] = p1Subs[0];
    	cards[2] = p1Subs[1];
    	cards[3] = p1Subs[2];
    	cards[4] = p1Subs[3];
    	cards[5] = p2Lead;
    	cards[6] = p2Subs[0];
    	cards[7] = p2Subs[1];
    	cards[8] = p2Subs[2];
    	cards[9] = p2Subs[3];
    	return cards;
    }
    
    @Override
    public Card[] getCurrentCards(){
    	Card[] cards = new Card[6];
    	if(turn==1){
    		cards[0] = p1Lead;
        	cards[1] = p1Subs[0];
        	cards[2] = p1Subs[1];
        	cards[3] = p1Subs[2];
        	cards[4] = p1Subs[3];
        	cards[5] = p2Lead;
    	}else if(turn==2){
    		cards[0] = p2Lead;
        	cards[1] = p2Subs[0];
        	cards[2] = p2Subs[1];
        	cards[3] = p2Subs[2];
        	cards[4] = p2Subs[3];
        	cards[5] = p1Lead;
    	}
    	return cards;
    }
    
    @Override
    public int getCurrentAwakenings(EnumAwakening awakening) {
    	Card[] cards = getCurrentCards();
    	int count = 0;
    	for(int c = 0; c < cards.length; c++){
    		count = count + cards[c].getAwakenings(awakening);
    	}
		return count;
	}

	public void loadTeamData(String text) {
		BinaryData data = new BinaryData(DatatypeConverter.parseHexBinary(text), 952);
		System.out.println(data);
		System.out.println("                                                                           V:+297d (true false)");
		System.out.println("         ID-----------|SkLv--|+HP----|+ATK---|+RCV--|.ID-----------|SkLv--|VLat1--|Lat2-|Lat3--|Lat4--|Lat5-|Lat6--|");
		p1Lead = new Card(data.getNext(95));
		p1Subs[0] = new Card(data.getNext(95));
		p1Subs[1] = new Card(data.getNext(95));
		p1Subs[2] = new Card(data.getNext(95));
		p1Subs[3] = new Card(data.getNext(95));
		p2Lead = new Card(data.getNext(95));
		p2Subs[0] = new Card(data.getNext(95));
		p2Subs[1] = new Card(data.getNext(95));
		p2Subs[2] = new Card(data.getNext(95));
		p2Subs[3] = new Card(data.getNext(95));
		
		//This code used to run a sample test board after loading the team, but this is not supposed to be here.
		
//		System.out.println(this);
//		System.out.println("Loading boards...");
//		Board boardFull = new Board(6, 5);
//		Board boardFullEnhanced = new Board(6, 5);
//		boolean[] full = new boolean[30];
//		for(int i = 0; i < full.length; i++){
//			full[i] = true;
//		}
//		boolean[] empty = new boolean[30];
//		for(int i = 0; i < empty.length; i++){
//			empty[i] = false;
//		}
//		boardFullEnhanced.setBoard("gggggggggggggggggggggggggggggg".toCharArray(), full, empty);
//		boardFull.setBoard("gggggggggggggggggggggggggggggg".toCharArray(), empty, empty);
//		ArrayList<Combo> combosEnhanced = boardFullEnhanced.getCombos();
//		ArrayList<Combo> combosNormal = boardFull.getCombos();
//		System.out.println(combosEnhanced);
//		System.out.println(combosNormal);
//		System.out.println("Done!");
//		System.out.println("Calculating damage for this team: " + this);
//		double multiplier = 12.25;
//		int[] damage = new int[12];
//		//  2C7920810200002000000000B843C3E30E0000400000000185E38400000000800000000280C602000800010000000004AD0A2850B0000200000000058F24000000000400000000000008000000000800000000000010000000001000000000000020000000002000000000000040000000004000000000
//		this.turn = 1;
//		Card[] cards = this.getCurrentCards();
//		for(int i = 0; i < 6; i++){
//			damage[2*i] = (int) Math.round(multiplier*cards[i].calcPrimaryAttack(combosEnhanced, this));
//			damage[2*i+1] = (int) Math.round(multiplier*cards[i].calcSubAttack(combosEnhanced, this));
//		}
//		System.out.println("Full Enhance");
//		System.out.println("Primary: " + damage[0] + " , " + damage[2] + " , " + damage[4] + " , " + damage[6] + " , " + damage[8] + " , " + damage[10]);
//		System.out.println("Sub    : " + damage[1] + " , " + damage[3] + " , " + damage[5] + " , " + damage[7] + " , " + damage[9] + " , " + damage[11]);
//		System.out.println("Total  : " + (damage[0]+damage[1]+damage[2]+damage[3]+damage[4]+damage[5]/*+damage[6]+damage[7]+damage[8]+damage[9]+damage[10]+damage[11]*/));
//		
//		for(int i = 0; i < 6; i++){
//			damage[2*i] = (int) Math.round(multiplier*cards[i].calcPrimaryAttack(combosNormal, this));
//			damage[2*i+1] = (int) Math.round(multiplier*cards[i].calcSubAttack(combosNormal, this));
//		}
//		System.out.println("No Enhance");
//		System.out.println("Primary: " + damage[0] + " , " + damage[2] + " , " + damage[4] + " , " + damage[6] + " , " + damage[8] + " , " + damage[10]);
//		System.out.println("Sub    : " + damage[1] + " , " + damage[3] + " , " + damage[5] + " , " + damage[7] + " , " + damage[9] + " , " + damage[11]);
//		System.out.println("Total  : " + (damage[0]+damage[1]+damage[2]+damage[3]+damage[4]+damage[5]/*+damage[6]+damage[7]+damage[8]+damage[9]+damage[10]+damage[11]*/));
	}
	
	public String getTeamData() {
		BinaryData data = new BinaryData();
		data.append(p1Lead.getCardData());
		data.append(p1Subs[0].getCardData());
		data.append(p1Subs[1].getCardData());
		data.append(p1Subs[2].getCardData());
		data.append(p1Subs[3].getCardData());
		data.append(p2Lead.getCardData());
		data.append(p2Subs[0].getCardData());
		data.append(p2Subs[1].getCardData());
		data.append(p2Subs[2].getCardData());
		data.append(p2Subs[3].getCardData());
//		System.out.println(data);
//		System.out.println("        ID-----------|SkLv--|+HP----|+ATK---|+RCV--|.ID-----------|SkLv--|Lat1--|Lat2-|Lat3--|Lat4--|Lat5-|Lat6--|");
		return DatatypeConverter.printHexBinary(data.toByteArray());
	}
	
	@Override
	public boolean canCardSwitch(int index){
		if(index == 5)
			return false;
		if(index > 0 && index < 5)
			return true;
		if(index == 0){
			if(turn==1){
				if(p1PreviousSwap > 0 && p1PreviousSwap < 5)
					return true;
			}else if(turn==2){
				if(p2PreviousSwap > 0 && p2PreviousSwap < 5)
					return true;
			}
		}
		return false;
	}
	
	@Override
	public void switchCard(int index){
		if(turn < 1 || turn > 2){
			System.out.println("Team variable 'turn' broke, reading value " + turn);
			return;
		}
		Card[] cards = getCurrentCards();
		if(!canCardSwitch(index))
			return;
		if(index > 0 && index < 5){
			Card lead = cards[0].copy();
			Card sub = cards[index].copy();
			cards[0] = sub.copy();
			cards[index] = lead.copy();
			if(turn==1){
				p1PreviousSwap = index;
			}else if(turn==2){
				p2PreviousSwap = index;
			}
		}
	}
	
	public String toString(){
		String s = p1Lead.toShortString();
		s = s + " / " + p1Subs[0].toShortString();
		s = s + " / " + p1Subs[1].toShortString();
		s = s + " / " + p1Subs[2].toShortString();
		s = s + " / " + p1Subs[3].toShortString();
		s = s + ", " + p2Lead.toShortString();
		s = s + " / " + p2Subs[0].toShortString();
		s = s + " / " + p2Subs[1].toShortString();
		s = s + " / " + p2Subs[2].toShortString();
		s = s + " / " + p2Subs[3].toShortString();
		return s;
	}
	
	@Override
	public void getCombinedLeaderSkill(){
		Card[] c = this.getCurrentCards();
		c[0].getLeaderSkill(dungeon)
	}
	
	@Override
	public void getMinOrbRequirement(){
		
	}
}
