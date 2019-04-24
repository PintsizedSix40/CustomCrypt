package com.PintsizedSix40.CustomCrypt;


import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

public class main {

	public static void main(String[] args) {
		String[] k = new String[3];
		k[0] = "12";
		k[1] = "16";
		k[2] = "6";
		Evaluate e = new Evaluate("1234", k, "%k1%-%i%*%k2%+%p%");
		System.out.println((int)(char)Character.toChars(Math.abs(e.Evaluate()))[0]);
		Evaluate e2 = new Evaluate("19732", k, "%k1%-%i%*%k2%+%p%");
		System.out.println(e2.DeEvaluate());
			}
}
