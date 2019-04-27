package com.PintsizedSix40.pcipher;

import java.util.ArrayList;

public class Polygon {

	private ArrayList<String> str;
	public static int DIRECTION_LEFT = 0;
	public static int DIRECTION_RIGHT = 1;
	private int shifted = 0;
	
	
	public Polygon(ArrayList<String> vals) {
		str = vals;
	}
	
	public void setValues(ArrayList<String> vals) {
		str = vals;
	}
	
	public void shift(int dir) {
		if(shifted > str.size()-1) {
			shifted = 0;
		}
		if(shifted < 0) {
			shifted = str.size()-1;
		}
		if(dir == DIRECTION_LEFT) {
			int next = shifted+1;
			if(shifted == str.size()-1) {
				next = 0;
			}
			String lx = str.get(shifted);
			String ly = str.get(next);
			str.set(shifted, ly);
			str.set(next, lx);
			shifted=next;
			return;
		}
		int next = shifted-1;
		if(shifted == 0) {
			next = str.size()-1;
		}
		String lx = str.get(shifted);
		String ly = str.get(next);
		str.set(shifted, ly);
		str.set(next, lx);
		shifted=next;
		return;
	}
	
	public void setShifted(int s) {
		shifted = s;
	}
	
	public ArrayList<String> get() {
		return str;
	}
	public void encrypt(int time) {
		setShifted(0);
		for(int i = 0; i < time; i++) {
			shift(DIRECTION_LEFT);
		}
	}
	public void decrypt(int time) {
	
		setShifted(time % str.size());
		for(int i = 0; i < time; i++) {
			shift(DIRECTION_RIGHT);
		}
	}
	
	public void setDecryptShift(int time) {
		setShifted(time % str.size());
	}
}
