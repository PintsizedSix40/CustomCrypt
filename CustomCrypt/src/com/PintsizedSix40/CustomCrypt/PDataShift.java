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
		int ran = 0;
		for(int i = 0; i < time; i++) {
			if(ran % 2 == 0) {
				poly.setValues(new ArrayList<String>(Arrays.asList(val.split(""))));
				poly.shift(Polygon.DIRECTION_LEFT);
				val = String.join("", poly.get().toArray(new String[0]));
			}
			if(ran % 2 == 1) {
				poly.setValues(new ArrayList<String>(Arrays.asList(val.split("(?<=\\G....)"))));
				poly.shift(Polygon.DIRECTION_LEFT);
				val = String.join("", poly.get().toArray(new String[0]));
			}
			/*if(ran % 3 == 2) {
				poly.setValues(new ArrayList<String>(Arrays.asList(val.split("(?<=\\G...)"))));
				poly.shift(Polygon.DIRECTION_LEFT);
				val = String.join("", poly.get().toArray(new String[0]));
			}*/
			ran++;
		}
	}
	
	public String get() {
		return val;
	}
	
	public void decrypt() {
		int ran = time-1;
		for(int i = time-1; i > -1; i--) {
			poly.setShifted(1);
			if(ran % 2 == 0) {
				poly.setValues(new ArrayList<String>(Arrays.asList(val.split(""))));
				poly.shift(Polygon.DIRECTION_RIGHT);
				val = String.join("", poly.get().toArray(new String[0]));
			}
			if(ran % 2 == 1) {
				poly.setValues(new ArrayList<String>(Arrays.asList(val.split("(?<=\\G....)"))));
				poly.shift(Polygon.DIRECTION_RIGHT);
				val = String.join("", poly.get().toArray(new String[0]));
			}
			/*if(ran % 3 == 2) {
				poly.setValues(new ArrayList<String>(Arrays.asList(val.split("(?<=\\G...)"))));
				poly.shift(Polygon.DIRECTION_RIGHT);
				val = String.join("", poly.get().toArray(new String[0]));
			}*/
			ran--;
		}
	}
	
}
