package data;

public class TeamSolo {
	public Card playerLead;
	public Card[] subs;
	public Card friendLead;
	
	public EnumBadge badge;
	
	public int getTeamCost(int player){
    	int cost = 0;
    	if(player == 1){
    		if(this.playerLead != null && this.playerLead.monster != null)
        		cost += this.playerLead.monster.teamCost;
    		for(int i = 0; i < subs.length; i++)
    			if(this.subs[i] != null && this.subs[i].monster != null)
    	    		cost += this.subs[i].monster.teamCost;
    	}
    	return cost;
    }
    
    public int getTeamCostWithBadge(int player){
    	int cost = getTeamCost(player);
    	switch(badge){
    		case teamCost: if(cost>100)
    			return cost-100;
    	    	return 0;
    		case hp15: return cost + 300;
    		case extendTime2: return cost + 400;
    		case rcv35: return cost + 300;
    		case atk15: return cost + 300;
    		default: return cost;
    	}
    }
}
