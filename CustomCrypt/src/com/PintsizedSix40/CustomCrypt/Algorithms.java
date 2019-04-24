package com.PintsizedSix40.CustomCrypt;

import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class Algorithms {

public static CryptHandler Pass = new CryptHandler() {

	@Override
	public String encrypt(String key, String in) {
		// TODO Auto-generated method stub
		return in;
	}

	@Override
	public String decrypt(String key, String in) {
		// TODO Auto-generated method stub
		return in;
	}
	
	
};

public static CryptHandler Custom = new CryptHandler() {

	@Override
	public String encrypt(String key, String in) {
		String exp = key.split("\\\\", 2)[0];
		String[] keys = key.split("\\\\", 2)[1].split("|");
		Evaluate ev = new Evaluate(in, keys, exp);
		String out = "";
		for(int i = 0; i < in.length(); i++) {
			ev.changeInput(Integer.toString((int)in.charAt(i)));
			out+=((char)ev.Evaluate());
		}
		return out;
	}

	@Override
	public String decrypt(String key, String in) {
		String exp = key.split("\\\\", 2)[0];
		String[] keys = key.split("\\\\", 2)[1].split("|");
		Evaluate ev = new Evaluate(in, keys, exp);
		String out = "";
		for(int i = 0; i < in.length(); i++) {
			ev.changeInput(Integer.toString((int)in.charAt(i)));
			out+=((char)ev.DeEvaluate());
		}
		return out;
	}
	
	
};

}
