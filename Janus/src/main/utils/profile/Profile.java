package main.utils.profile;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import main.debug.Debugger;
import main.utils.SaveData;
import main.utils.SaveLoad;

public class Profile {

	private String username, password, pcrypted;
	private String strKey;
	
	public boolean active = false;
	
	
	public Profile(String username) {
		strKey = "easy";
		loadProfile(username);
	}
	
	public Profile(String username, String password) {
		this.username = username;
		this.password = password;
		strKey = "easy";
		active = true;
		encryptPass();
	}
	
	private void encryptPass() {
		try {
			SecretKeySpec skeyspec = new SecretKeySpec(strKey.getBytes(), "Blowfish");
			Cipher cipher = Cipher.getInstance("Blowfish");
			cipher.init(Cipher.ENCRYPT_MODE, skeyspec);
			byte[] encrypted = cipher.doFinal(password.getBytes());
			pcrypted = new String(encrypted);
		} catch (Exception e) {
			Debugger.printError(e.getLocalizedMessage());
		}
	}
	
	public void loadProfile(String fileName) {
		SaveData sd = SaveLoad.readFromFile("profiles/" + fileName);
		String[] split = sd.getData().split(":");
		username = split[0];
		pcrypted = split[1];
		decryptPass();
	}
	
	public void saveProfile() {
		SaveData data = new SaveData(username + ":" + pcrypted);
		SaveLoad.writeToFile(data, "profiles/" + username);
	}
	
	private void decryptPass() {
		try {
			SecretKeySpec skeyspec = new SecretKeySpec(strKey.getBytes(), "Blowfish");
			Cipher cipher = Cipher.getInstance("Blowfish");
			cipher.init(Cipher.DECRYPT_MODE, skeyspec);
			byte[] decrypted = cipher.doFinal(pcrypted.getBytes());
			password = new String(decrypted);
		} catch (Exception e) {
			Debugger.printError(e.getLocalizedMessage());
		}
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
