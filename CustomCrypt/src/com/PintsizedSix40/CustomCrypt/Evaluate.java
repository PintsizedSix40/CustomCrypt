package com.PintsizedSix40.CustomCrypt;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

public class Evaluate {

	String exp;
	String[] keys;
	int in;
	String oldexp;
	public Evaluate(int n, String[] k, String e) {
		in = n;
		keys = k;
		exp = e;
		oldexp = e;
	}
	
	public void changeInput(int inp) {
		in = inp;
		exp = oldexp;
	}
	private void replace() {
		exp = exp.replaceAll("%t%", Integer.toString(Data.iteration)).replaceAll("%i%", Integer.toString(in));
		for(int i = 0; i < keys.length; i++) {
			exp = exp.replaceAll("%k"+(i+1)+"%", keys[i]);
		}
	}
	public int Evaluate() {
		replace();
		Expression e = new ExpressionBuilder(exp)
	            .build();
		int result = (int) e.evaluate();
		Data.iteration++;
		return result;
	}
	
	public int DeEvaluate() {
		exp = exp.replaceAll("\\*", ">").replaceAll("/", "<").replaceAll("-", "\\$").replaceAll("\\+", "#").replaceAll("\\^", "&");
		replace();
		Expression e = new ExpressionBuilder(exp).operator(Data.m, Data.d, Data.s, Data.a)
        .build();
		int result = (int) e.evaluate();
		Data.iteration++;
		return result;
	}
	
}
