package com.revature.driver;

import java.util.Scanner;

// import com.revature.services.OopServices;

public class Driver {
	
	private static Scanner sc = new Scanner(System.in);
	
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		
		int i; //declaring a variable
		i = 0; // assigning the value 0
		i = 2; // reassigning the value to be 2
		
		OopServices oop = new OopServices();
		System.out.println("What would like to do?");
		String choice = sc.nextLine();
		switch(choice) {
			case "encapsulation":
				oop.doEncapsulation();
				break;
			case "inheritance":
				oop.doInheritance();
				break;
			case "polymorphism":
				oop.doPolymorphism();
				break;
			case "completeTask":
				oop.doCompletedTasks();
				// Example of upcasting.
			case "upcasting":
				oop.upCasting();
				
			case "abstraction":
				oop.doJumpOrStep();

			case "exception":
				oop.throwException(2,4);
				
			default:
				System.out.println("Option not available.");
		}
		sc.close();

		
	}

//	- Abstraction
//	- abstract keyword
//	- interface keyword
//- Create a custom exception, throw it, and handle it!
	
}