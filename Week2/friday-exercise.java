public class Food {
    //create class Food
	public String name;
    // declare variable name with reference type String as a parameter for the Food class
	public String type;
    // declare variable name with reference type String as a parameter for the Food class

	public Food(String name, String type) {
        // constructor for Food object with arguments of name and type
		this.name = name;
        // setter method for Food name so that the instantiated Food object has a name
		this.type = type;
        // setter method for Food type so that the instantiated Food object has a type
	}

	public void cook() {
		if (!name.contains("cooked")) {
            // if the name of the food does not contain the string "cooked"
			name = "cooked " + name;
            // add "cooked" before the name so the entire string becomes "cooked [name]"
		}
	}
}

public class Main {
	public static void main(String[] args) {
		Food myFood = new Food("potato", "vegetable");
        //create class object myFood instantiated with a name of "potato" and type of "vegetable"
		myFood.cook();
        // call cook method so that myFood is returned as "cooked potato"

		System.out.println(myFood.name);
        // print out "cooked potato"
	}
}