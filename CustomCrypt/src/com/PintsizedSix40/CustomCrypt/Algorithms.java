package com.PintsizedSix40.CustomCrypt;

import java.security.spec.KeySpec;
import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class Algorithms {

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
		String[] keys = key.split("\\\\", 2)[1].split("|");
		Evaluate ev = new Evaluate((byte)'t', keys, exp);
		String out = "";
		Data.iteration = 1;
		for(int i = 0; i < in.length(); i++) {
			ev.changeInput((int)in.charAt(i));
			out+=(char)ev.Evaluate();
		}
		return out;
	}

	@Override
	public String decrypt(String key, String in) {
		String exp = key.split("\\\\", 2)[0];
		String[] keys = key.split("\\\\", 2)[1].split("|");
		Evaluate ev = new Evaluate((byte)'t', keys, exp);
		Data.iteration = 1;
		String out = "";
		for(int i = 0; i < in.length(); i++) {
			ev.changeInput((int)in.charAt(i));
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
			byte[] salt = new byte[8];
			byte[] iv = new byte[16];
			KeySpec spec = new PBEKeySpec(key.toCharArray(), salt, 65536, 256);
			SecretKey tmp = factory.generateSecret(spec);
			SecretKey secret = new SecretKeySpec(tmp.getEncoded(), "AES");
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, secret, new IvParameterSpec(iv));
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
			factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
			byte[] salt = new byte[8];
			byte[] iv = new byte[16];
			KeySpec spec = new PBEKeySpec(key.toCharArray(), salt, 65536, 256);
			SecretKey tmp = factory.generateSecret(spec);
			SecretKey secret = new SecretKeySpec(tmp.getEncoded(), "AES");
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			cipher.init(Cipher.DECRYPT_MODE, secret, new IvParameterSpec(iv));
			return new String(cipher.doFinal(java.util.Base64.getDecoder().decode(in)), "UTF-8");
			
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
			byte[] salt = new byte[8];
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
			byte[] salt = new byte[8];
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


}
