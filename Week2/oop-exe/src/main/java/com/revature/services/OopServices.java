package com.revature.services;


public class OopServices {

	// examples of access modifiers
	@SuppressWarnings("unused")
	private int i;
	protected String s;
	String something; // default access modifier
	
	public void doEncapsulation() {
	
	}

	public void doInheritance() {
	
	}

	public void doPolymorphism() {
		
		
	}
	
	public void upCasting() {
		
		
	}

	public void doAbstraction() {
			System.out.println("abstract method");
		//abstract method implemented
		
	}

	public int throwException(int a, int b) {//throws arithmetic exception 
		int result = a + b;
		if(result == 23) {
			throw new ArithmeticException();
		}
		return result;
	}
	
}