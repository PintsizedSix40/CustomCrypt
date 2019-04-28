package com.PintsizedSix40.CustomCrypt;

import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.security.spec.KeySpec;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class Algorithms {
public static byte[] salt = new byte[8];

public static void init() {
	salt[0] = (byte)11;
	salt[1] = (byte)117;
	salt[2] = (byte)123;
	salt[3] = (byte)46;
	salt[4] = (byte)18;
	salt[5] = (byte)88;
	salt[6] = (byte)26;
	salt[7] = (byte)38;
}

public static CryptHandler Pass = new CryptHandler() {

	@Override
	public String encrypt(String key, String in) {
		return in;
	}

	@Override
	public String decrypt(String key, String in) {
		return in;
	}
	
	
};

public static CryptHandler Custom = new CryptHandler() {

	@Override
	public String encrypt(String key, String in) {
		String exp = key.split("\\\\", 2)[0];
		String[] keys = null;
		try {
		keys = key.split("\\\\", 2)[1].split("|");
		} catch(Exception e) {
			String[] tmparr = new String[2];
			tmparr[0] = "1";
			tmparr[1] = "2";
			keys = tmparr;
		}
		Evaluate ev = new Evaluate((byte)'t', keys, exp);
		String out = "";
		Data.iteration = 1;
		for(int i = 0; i < in.length(); i++) {
			ev.changeInput((int)in.charAt(i));
			out+=(char)ev.Evaluate();
		}
			return Base64.getEncoder().encodeToString(out.getBytes(StandardCharsets.UTF_8));
	}

	@Override
	public String decrypt(String key, String in) {
		String exp = key.split("\\\\", 2)[0];
		String[] keys = null;
		String inp = new String(Base64.getDecoder().decode(in), StandardCharsets.UTF_8);
		try {
		keys = key.split("\\\\", 2)[1].split("|");
	} catch(Exception e) {
		String[] tmparr = new String[2];
		tmparr[0] = "1";
		tmparr[1] = "2";
		keys = tmparr;
	}
		Evaluate ev = new Evaluate((byte)'t', keys, exp);
		Data.iteration = 1;
		String out = "";
		for(int i = 0; i < inp.length(); i++) {
			ev.changeInput((int)inp.charAt(i));
			out+=((char)ev.DeEvaluate());
		}
		return out;
	}
	
	
};

public static CryptHandler AES = new CryptHandler() {

	@Override
	public String encrypt(String key, String in) {
		try {
			SecretKeyFactory factory;
			factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
			SecureRandom sr = new SecureRandom();
			byte[] iv = new byte[16];
			sr.nextBytes(iv);
			KeySpec spec = new PBEKeySpec(key.toCharArray(), salt, 65536, 256);
			SecretKey tmp = factory.generateSecret(spec);
			SecretKey secret = new SecretKeySpec(tmp.getEncoded(), "AES");
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, secret, new IvParameterSpec(iv));
			return Base64.getEncoder().encodeToString(iv)+java.util.Base64.getEncoder().encodeToString(cipher.doFinal(in.getBytes("UTF-8")));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String decrypt(String key, String in) {
		try {
			SecretKeyFactory factory;
			factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
			byte[] iv = Base64.getDecoder().decode(in.substring(0, 24));
			KeySpec spec = new PBEKeySpec(key.toCharArray(), salt, 65536, 256);
			SecretKey tmp = factory.generateSecret(spec);
			SecretKey secret = new SecretKeySpec(tmp.getEncoded(), "AES");
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			cipher.init(Cipher.DECRYPT_MODE, secret, new IvParameterSpec(iv));
			return new String(cipher.doFinal(java.util.Base64.getDecoder().decode(in.substring(24))), "UTF-8");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
};

public static CryptHandler DES = new CryptHandler() {

	@Override
	public String encrypt(String key, String in) {
		try {
			SecretKeyFactory factory;
			factory = SecretKeyFactory.getInstance("PBEWithMD5AndTripleDES");
			KeySpec spec = new PBEKeySpec(key.toCharArray(), salt, 65536, 256);
			SecretKey secret = factory.generateSecret(spec);
			Cipher cipher = Cipher.getInstance("PBEWithMD5AndTripleDES");
			cipher.init(Cipher.ENCRYPT_MODE, secret, new PBEParameterSpec(salt, 100));
			return java.util.Base64.getEncoder().encodeToString(cipher.doFinal(in.getBytes("UTF-8")));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String decrypt(String key, String in) {
		try {
			SecretKeyFactory factory;
			factory = SecretKeyFactory.getInstance("PBEWithMD5AndTripleDES");
			KeySpec spec = new PBEKeySpec(key.toCharArray(), salt, 65536, 256);
			SecretKey secret = factory.generateSecret(spec);
			Cipher cipher = Cipher.getInstance("PBEWithMD5AndTripleDES");
			cipher.init(Cipher.DECRYPT_MODE, secret, new PBEParameterSpec(salt, 100));
			return new String(cipher.doFinal(java.util.Base64.getDecoder().decode(in)), "UTF-8");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
};


public static CryptHandler Blowfish = new CryptHandler() {

	@Override
	public String encrypt(String key, String in) {
		try {
			SecretKeySpec skey = new SecretKeySpec(key.getBytes(), "Blowfish");
		     Cipher cipher = Cipher.getInstance("Blowfish");
		     cipher.init(Cipher.ENCRYPT_MODE, skey);
			return java.util.Base64.getEncoder().encodeToString(cipher.doFinal(in.getBytes("UTF-8")));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String decrypt(String key, String in) {
		try {
			SecretKeySpec skey = new SecretKeySpec(key.getBytes(), "Blowfish");
		     Cipher cipher = Cipher.getInstance("Blowfish");
		     cipher.init(Cipher.DECRYPT_MODE, skey);
			return new String(cipher.doFinal(java.util.Base64.getDecoder().decode(in)), "UTF-8");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
};

public static CryptHandler BDS = new CryptHandler() {

	@Override
	public String encrypt(String key, String in) {
		PDataShift ds = new PDataShift(in, Integer.parseInt(key));
		ds.encrypt();
		return ds.get();
	}

	@Override
	public String decrypt(String key, String in) {
		PDataShift ds = new PDataShift(in, Integer.parseInt(key));
		ds.decrypt();
		return ds.get();
	}
	
	
};

public static CryptHandler BAS = new CryptHandler() {

	@Override
	public String encrypt(String key, String in) {
		ArrayList<String> unique = new ArrayList<String>();
		
		for(int i = 0; i < in.length(); i++) {
			if(!unique.contains(Character.toString(in.charAt(i)))) {
				unique.add(Character.toString(in.charAt(i)));
			}
			}
			
			PDataShift ds = new PDataShift(String.join("", unique.toArray(new String[0])), Integer.parseInt(key));
			ds.encrypt();
			ArrayList<String> newa = new ArrayList<String>(Arrays.asList(ds.get().split("")));
			
		ArrayList<String> data = new ArrayList<String>(Arrays.asList(in.split("")));
			for(int y = 0; y < data.size(); y++) {
				data.set(y, newa.get(unique.indexOf(data.get(y))));
			}
			return String.join("", data.toArray(new String[0]));
		}

	@Override
	public String decrypt(String key, String in) {
		ArrayList<String> unique = new ArrayList<String>();
		
		for(int i = 0; i < in.length(); i++) {
			if(!unique.contains(Character.toString(in.charAt(i)))) {
				unique.add(Character.toString(in.charAt(i)));
			}
			}
			
			PDataShift ds = new PDataShift(String.join("", unique.toArray(new String[0])), Integer.parseInt(key));
			ds.decrypt();
			ArrayList<String> newa = new ArrayList<String>(Arrays.asList(ds.get().split("")));
			
			ArrayList<String> data = new ArrayList<String>(Arrays.asList(in.split("")));
			for(int y = 0; y < data.size(); y++) {
				data.set(y, newa.get(unique.indexOf(data.get(y))));
			}
			return String.join("", data.toArray(new String[0]));
	}
	
};


}
