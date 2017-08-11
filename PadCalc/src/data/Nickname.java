package data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONObject;

public class Nickname {
	public static ArrayList<String> nickname;
	public static ArrayList<String[]> allNicknames;
	public static HashMap<String, Integer> lookup;
	
	public static void loadNamesFromFile(){
		allNicknames = new ArrayList<String[]>(4000);
		nickname = new ArrayList<String>(4000);
		for(int x = 1; x < 4000; x++){
			allNicknames.add(new String[]{});
			nickname.add(null);
		}
		
//		try(BufferedReader br = new BufferedReader(new FileReader(new File("nicknames.txt")))) {
//		    for(String line; (line = br.readLine()) != null; ) {
//		        String[] parts = line.split(",");
//		        ArrayList<String> names; 
//		        int id = Integer.parseInt(parts[0]);
//		        if((Integer)id != null && id > 0){
//		        	if(nickname.get(id)!=null){
//		        		System.out.println("Found duplicate nickname set for id " + id + ". Skipping second set.");
//		        	}else if(parts.length>1){
//		        		names = new ArrayList<String>();
//		        		nickname.add(id, read(parts[1]));
//			        	for (int x = 1; x < parts.length; x++){
//			        		if(read(parts[x])!=null){
//			        			if(lookup.get(simplify(read(parts[x])))!=null){
//			        				System.out.println("Found duplicate nickname to id mapping for " + simplify(read(parts[x])) + ". Skipping second entry attempt.");
//			        			}else{
//			        				lookup.put(simplify(read(parts[x])), id);
//			        				names.add(read(parts[x]));
//			        			}
//			        		}
//			        	}
//			        	allNicknames.add(id, (String[])names.toArray());
//		        	}
//		        }
//		    }
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		
		try(BufferedReader br = new BufferedReader(new FileReader(new File("nicknames2.txt")))) {
			String replacement = "";
		    for(String line; (line = br.readLine()) != null; ) {
		    	l:{
			        String[] parts = line.split(",");
			        ArrayList<String> names; 
			        if(parts.length == 0 || read(parts[0]) == null){
			        	break l;
			        }
			        if(read(parts[0]).startsWith(";")){
			        	replacement = read(read(parts[0]).substring(1));
			        }
			        int id = Integer.parseInt(read(parts[0]));
			        if((Integer)id != null && id > 0){
			        	if(nickname.get(id)!=null){
			        		System.out.println("Found duplicate nickname set for id " + id + ". Skipping second set.");
			        	}else if(parts.length>1){
			        		names = new ArrayList<String>();
			        		nickname.add(id, read(parts[1]));
				        	for (int x = 1; x < parts.length; x++){
				        		if(read(parts[x])!=null){
				        			if(lookup.get(simplify(read(parts[x])))!=null){
				        				System.out.println("Found duplicate nickname to id mapping for " + simplify(read(parts[x])) + ". Skipping second entry attempt.");
				        			}else{
				        				lookup.put(simplify(read(parts[x])), id);
				        				names.add(read(parts[x]));
				        			}
				        		}
				        	}
				        	allNicknames.add(id, (String[])names.toArray());
			        	}
			        }
		    	}
		    }
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static Integer lookup(String s){
		return lookup.get(simplify(s));
	}
	
	public static String read(String s){
		s = s.trim();
		if(s==null)
			return null;
		if(s=="")
			return null;
		if(s==" ")
			return null;
		return s;
	}
	
	/**
	 * Remove capitalization and all non-letter characters
	 * @return 
	 */
	public static String simplify(String s){
		return s.toLowerCase().replaceAll("[^A-Za-z0-9]", "");
	}
	
	public static String replace(String s, String from, String to){
		// Modification of code from https://stackoverflow.com/a/3472705/8150086
		StringBuilder sb = new StringBuilder(s);
		
		for(int i = sb.indexOf(from); i != -1; i = sb.indexOf(from, i)){
			sb.replace(i, i+from.length(), to);
			i += to.length(); // move the index past the end of the replacement, in case 'to' contains 'from'
		}
		
		return sb.toString();
	}
}
