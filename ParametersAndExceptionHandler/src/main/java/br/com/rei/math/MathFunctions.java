package br.com.rei.math;

public class MathFunctions {
	
	public Double sum(Double numberOne, Double numberTwo) {
		return numberOne + numberTwo;
	}

	public Double sub(Double numberOne, Double numberTwo) {
		return numberOne - numberTwo;
	}

	public Double multi(Double numberOne, Double numberTwo) {
		return numberOne * numberTwo;
	}

	public Double div(Double numberOne, Double numberTwo) {
		return numberOne / numberTwo;
	}

	public Double avg(Double numberOne, Double numberTwo) {
		return (numberOne + numberTwo) / 2;
	}

	public Double sqr(Double numberOne) {
		return Math.sqrt(numberOne);
	}
}
