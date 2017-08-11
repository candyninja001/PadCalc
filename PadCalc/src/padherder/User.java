package padherder;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import api.HTTP;

public class User {
	public String username;
	public ArrayList<BoxEntry> box;
	
	public User(String name){
		username = name;
		box = new ArrayList<BoxEntry>();
	}
	
	public void loadMonsters(){
		try {
			String read = HTTP.getHTML("https://www.padherder.com/user-api/user/"+username+"/");
			JSONObject jsonUser = new JSONObject(read);
			JSONArray monsterArray = jsonUser.getJSONArray("monsters");
			for (int i = 0; i < monsterArray.length(); i++)
			{
			    addMonster(monsterArray.getJSONObject(i));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void addMonster(JSONObject json){
		try {
			BoxEntry boxEntry = new BoxEntry(json);
			box.add(boxEntry);
			System.out.println("Added "+boxEntry.card);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void sortBox()
}
