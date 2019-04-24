package com.PintsizedSix40.CustomCrypt;

public interface CryptHandler {

	public String encrypt(String key, String in);
	
	public String decrypt(String key, String in);
}
