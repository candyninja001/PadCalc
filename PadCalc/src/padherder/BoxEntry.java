package padherder;

import org.json.JSONObject;

import data.Card;
import data.EnumLatentAwakening;
import data.Monster;

public class BoxEntry {
	public int padherderID = 0; // The chronological id of the monster between all users according to padherder
	public int padID = 0; // The chronological id per user according to pad
	public String url = "";
	public int priority = 0;
	public String note = "";
	public int targetLevel = 1;
	public int targetEvolution = 0;
	public Card card = null;
	
	public BoxEntry(JSONObject json){
		if(json.has("id"))
			this.padherderID = json.getInt("id");
		if(json.has("url"))
			this.url = json.getString("url");
		if(json.has("pad_id"))
			this.padID = json.getInt("pad_id");
		if(json.has("note"))
			this.note = json.getString("note");
		if(json.has("priority"))
			this.priority = json.getInt("priority");
		if(json.has("target_level"))
			this.targetLevel = json.getInt("target_level");
		if(json.has("target_evolution"))
			if(!json.isNull("target_evolution"))
				this.targetEvolution = json.getInt("target_evolution");
		
		if(json.has("monster")){
			Card c = new Card(Monster.monsters.get(json.getInt("monster")));
			if(json.has("current_xp"))
				c.setXP(json.getInt("current_xp"));
			if(json.has("current_skill"))
				c.skillLevel = json.getInt("current_skill");
			if(json.has("current_awakening"))
				c.awakenings = json.getInt("current_awakening");
			if(json.has("plus_hp"))
				c.plusHP = json.getInt("plus_hp");
			if(json.has("plus_atk"))
				c.plusATK = json.getInt("plus_atk");
			if(json.has("plus_rcv"))
				c.plusRCV = json.getInt("plus_rcv");
			
			EnumLatentAwakening l1 = null;
			if(json.has("latent1"))
				l1 = EnumLatentAwakening.fromInt(json.getInt("latent1"));
			EnumLatentAwakening l2 = null;
			if(json.has("latent1"))
				l2 = EnumLatentAwakening.fromInt(json.getInt("latent2"));
			EnumLatentAwakening l3 = null;
			if(json.has("latent1"))
				l3 = EnumLatentAwakening.fromInt(json.getInt("latent3"));
			EnumLatentAwakening l4 = null;
			if(json.has("latent1"))
				l4 = EnumLatentAwakening.fromInt(json.getInt("latent4"));
			EnumLatentAwakening l5 = null;
			if(json.has("latent1"))
				l5 = EnumLatentAwakening.fromInt(json.getInt("latent5"));
			EnumLatentAwakening l6 = null; 
			// Padherder does not currently support latent6, but should soon
			if(json.has("latent6"))
				l6 = EnumLatentAwakening.fromInt(json.getInt("latent6"));
			
			c.latents = new EnumLatentAwakening[]{l1,l2,l3,l4,l5,l6};
			
			this.card = c;
		}
	}
}
