package ec.lab;


/** 
* ICalculator
* This is the interface for Calculator class
* file Calculator.java
*/ 
public interface Calculator {
		
	/**
	  * Compute and return the sum of two integers. 
	  * @param a This is the first operand
	  * @param b This is the second operand
	  * @return a+b  This is result of the sum operation
	  *  	  
	  */ 
	int sum(int a, int b);
	
	 	
	/**
	  * Compute and return the difference of two operands. 
	  * @param a This is the first operand
	  * @param b This is the second operand
	  * @return a-b 
	  */ 
	int subtraction(int a, int b);
	
	 
	/**
	  * Compute and return the multiplication of two integers. 
	  * 
	  * @param a This is the first operand
	  * @param b This is the second operand
	  * @return a*b
	  */ 
	int multiplication(int a, int b);
	
	
	 /**
	  * Compute and return the devision of two integers. 
	  * 
	  * @param a This is the first operand
	  * @param b This is the second operand
	  * @return a/b
	  * @throws Exception if  b == 0 
	  */ 
	int divison(int a, int b) throws Exception;
	
	 
	/**
	  * Check if two integer operands are equal. 
	  * 
	  * @param a This is the first operand
	  * @param b This is the second operand
	  * 
	  * @return a==b
	  */ 
	boolean equalIntegers(int a, int b);
	
}
