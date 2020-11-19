package main.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import main.debug.Debugger;
import main.utils.profile.Profile;

public class SaveLoad {
	
	public static void writeToFile(SaveData data, String fn) {
		try {
			String fileName = System.getProperty("user.dir") + "/data/" + fn + ".sav";
			File sf = new File(fileName);
			if (!sf.exists()) {
				File directory = sf.getParentFile();
				if (!directory.exists()) {
					directory.mkdirs();
				}
				
			}
			FileOutputStream saveFile = new FileOutputStream(fileName);

			ObjectOutputStream save = new ObjectOutputStream(saveFile);
			
			save.writeObject(data);
			
			save.flush();
			
			save.close(); 
		} catch (Exception exc) {
			Debugger.printError(exc.toString());
		}
	}
	
	public static SaveData readFromFile(String fn) {
		SaveData data = null;
		try {
			String fileName = System.getProperty("user.dir") + "/data/" + fn + ".sav";
			FileInputStream saveFile = new FileInputStream(fileName);

			ObjectInputStream save = new ObjectInputStream(saveFile);

			data = (SaveData) save.readObject();
			
			save.close();
			
			return data;
		} catch (Exception exc) {
			Debugger.printError(exc.toString());
		}
		
		if (data == null) {
			data = new SaveData("-1");
		}
		
		return data;
	}
	
	public static ArrayList<Profile> readAnyExistingProfiles() {
		ArrayList<Profile> profiles = new ArrayList<Profile>();
		
		String fileName = System.getProperty("user.dir") + "/data/profiles/";		
		File folder = new File(fileName);
		File[] listOfFiles = folder.listFiles();

		for (File file : listOfFiles) {
		    if (file.isFile()) {
		    	String fn = file.getName();
		    	String[] split = fn.split("sav");
		    	String pn = split[0].substring(0, split[0].length() - 1);
		        profiles.add(new Profile(pn));
		    }
		}
		
		return profiles;
	}

}
