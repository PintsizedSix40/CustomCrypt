package com.PintsizedSix40.CustomCrypt;

import java.util.ArrayList;
import java.util.Arrays;

import com.PintsizedSix40.pcipher.Polygon;

public class PDataShift {

	String input;
	int time;
	Polygon poly;
	String val;
	public PDataShift(String in, int t) {
		input = in;
		time = t;
		val = input;
		poly = new Polygon(new ArrayList<String>(Arrays.asList(val.split(""))));
	}
	
	public void encrypt() {
		poly.setValues(new ArrayList<String>(Arrays.asList(val.split("(?<=\\\\G..)"))));
		poly.encrypt(time);
		val = String.join("", poly.get().toArray(new String[0]));
		
		poly.setValues(new ArrayList<String>(Arrays.asList(val.split(""))));
		poly.encrypt(time);
		val = String.join("", poly.get().toArray(new String[0]));
		
		poly.setValues(new ArrayList<String>(Arrays.asList(val.split("(?<=\\\\G..)"))));
		poly.encrypt(time);
		val = String.join("", poly.get().toArray(new String[0]));
	}
	
	public String get() {
		return val;
	}
	
	public void decrypt() {
		poly.setValues(new ArrayList<String>(Arrays.asList(val.split("(?<=\\\\G..)"))));
		poly.decrypt(time);
		val = String.join("", poly.get().toArray(new String[0]));
		
		poly.setValues(new ArrayList<String>(Arrays.asList(val.split(""))));
		poly.decrypt(time);
		val = String.join("", poly.get().toArray(new String[0]));
		
		poly.setValues(new ArrayList<String>(Arrays.asList(val.split("(?<=\\\\G..)"))));
		poly.decrypt(time);
		val = String.join("", poly.get().toArray(new String[0]));
	}
	
}
