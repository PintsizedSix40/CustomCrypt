package com.PintsizedSix40.CustomCrypt;

import net.objecthunter.exp4j.operator.Operator;

public class Data {

	public static int iteration = 1;
	
	public static Operator a = new Operator("$", 2, true, Operator.PRECEDENCE_DIVISION-1) {
		@Override
        public double apply(double... values) {
		return values[1]+values[0];
			
		}
		
	};
	
	public static Operator s = new Operator("#", 2, true, Operator.PRECEDENCE_MULTIPLICATION-1) {
		@Override
        public double apply(double... values) {
			if(values[1] > values[0]) {
		return values[1]-values[0];
			}
			return values[0]-values[1];
		}
		
	};
	
	public static Operator d = new Operator(">", 2, true, Operator.PRECEDENCE_ADDITION-1) {
		@Override
        public double apply(double... values) {
			if(values[1] > values[0]) {
		return values[1]/values[0];
			}
			return values[0]/values[1];
			
		}
		
	};
	public static Operator m = new Operator("<", 2, true, Operator.PRECEDENCE_SUBTRACTION-1) {
		@Override
        public double apply(double... values) {
		return values[1]*values[0];
			
		}
		
	};

}
