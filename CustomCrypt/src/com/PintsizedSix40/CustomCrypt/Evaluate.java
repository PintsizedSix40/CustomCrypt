package com.PintsizedSix40.CustomCrypt;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
import net.objecthunter.exp4j.operator.Operator;

public class Evaluate {

	String exp;
	String[] keys;
	String in;
	String oldexp;
	public Evaluate(String n, String[] k, String e) {
		in = n;
		keys = k;
		exp = e;
		oldexp = e;
	}
	
	private void replaceKeys() {
		for(int i = 0; i < keys.length; i++) {
			exp = exp.replaceAll("%k"+(i+1)+"%", keys[i]);
		}
	}
	
	public void changeInput(String inp) {
		in = inp;
		exp = oldexp;
	}
	
	private void replaceInput() {
		//in = String.format("d%04", Integer.parseInt(in));
		//Data.prevOutput = String.format("d%04", Integer.parseInt(Data.prevOutput));
		/*try{exp = exp.replaceAll("%i1%", Character.toString(in.charAt(0)));} catch(Exception e) {exp = exp.replaceAll("%i1%", "0");}
		try{exp = exp.replaceAll("%i2%", Character.toString(in.charAt(1)));} catch(Exception e) {exp = exp.replaceAll("%i2%", "0");}
		try{exp = exp.replaceAll("%i3%", Character.toString(in.charAt(2)));} catch(Exception e) {exp = exp.replaceAll("%i3%", "0");}
		try{exp = exp.replaceAll("%i4%", Character.toString(in.charAt(3)));} catch(Exception e) {exp = exp.replaceAll("%i4%", "0");}*/
		exp = exp.replaceAll("%i%", in); 
		
		////
		/*try{exp = exp.replaceAll("%p1%", Character.toString(Data.prevOutput.charAt(0)));} catch(Exception e) {exp = exp.replaceAll("%p1%", "0");}
		try{exp = exp.replaceAll("%p2%", Character.toString(Data.prevOutput.charAt(1)));} catch(Exception e) {exp = exp.replaceAll("%p2%", "0");}
		try{exp = exp.replaceAll("%p3%", Character.toString(Data.prevOutput.charAt(2)));} catch(Exception e) {exp = exp.replaceAll("%p3%", "0");}
		try{exp = exp.replaceAll("%p4%", Character.toString(Data.prevOutput.charAt(3)));} catch(Exception e) {exp = exp.replaceAll("%p4%", "0");}*/
		exp = exp.replaceAll("%p%", Data.prevOutput);
		
	}
	private void replace() {
		replaceInput();
		replaceKeys();
	}
	public int Evaluate() {
		replace();
		Expression e = new ExpressionBuilder(exp).operator(Data.p)
	            .build();
		int result = (int) e.evaluate();
		Data.prevOutput = Integer.toString(result);
		Data.iteration++;
		return result;
	}
	
	public int DeEvaluate() {
		exp = exp.replaceAll("\\*", ">");
		exp = exp.replaceAll("/", "<");
		exp = exp.replaceAll("-", "\\$");
		exp = exp.replaceAll("\\+", "#");
		exp = exp.replaceAll("\\^", "&");
		replace();
		Expression e = new ExpressionBuilder(exp).operator(Data.p, Data.m, Data.d, Data.s, Data.a)
        .build();
		int result = (int) e.evaluate();
		Data.prevOutput = Integer.toString(result);
		Data.iteration++;
		return result;
	}
	
}
