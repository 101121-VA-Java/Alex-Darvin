public static void main(String[] args) {
// main method
	String[] foodTypes = getFoodTypes();
    // declare foodTypes as a reference variable type String assigned a value that is the returned value of the method getFoodTypes
	String myType = foodTypes[2];
    // variable myType has a value of the index 2 from the array foodTypes ("dinner"). ("stir fry")

	String[] foodsOfMyType = getFoodsByType(myType);
    // variable foodsOfMyType has a value of the return value of the method getFoodsByType with an argument of myType
	
	for (String food : foodsOfMyType) {
        // enhanced for loop = for each type of food that is included in the array foodsOfMyType ("dinner"), print that food ("curry", "stir fry", "burger")
		System.out.println(food);
        // print each food that meets the condition of the for loop
	}
}

public static String[] getFoodTypes() {
	String[] foodTypes = {"breakfast","lunch","dinner","dessert","snack"};
    // assign foodTypes a value of an array of strings
	return foodTypes;
    // return the array
}

public static String[] getFoodsByType(String type) {
    // declare variable type as the argument for getFoodsByType method
	String[] foods = new String[3];
    // declare variable foods with an assigned value of an array with 3 Strings

	switch(type) {
        // switch-case statement depending on the type of food
	case "breakfast":
		foods[0] = "pancakes";
		foods[1] = "cereal";
		foods[2] = "omelette";
        // if the food type is "breakfast", create an array with 3 Strings assigned values of "pancakes" "cereal" and "omelette"
		break;
	case "lunch":
		foods[0] = "sandwich";
		foods[1] = "salad";
		foods[2] = "soup";
        // if the food type is "lunch", create an array with 3 Strings assigned values of "sandwich" "salad" and "soup"
		break;
	case "dinner":
		foods[0] = "curry";
		foods[1] = "stir fry";
		foods[2] = "burger";
        // if the food type is "dinner", create an array with 3 Strings assigned values of "curry" "stir fry" and "burger"
		break;
	case "dessert":
		foods[0] = "cake";
		foods[1] = "ice cream";
		foods[2] = "candy";
        // if the food type is "dessert", create an array with 3 Strings assigned values of "cake" "ice cream" and "candy"
		break;
	case "snack":
		foods[0] = "chips";
		foods[1] = "apple";
		foods[2] = "samosa";
        // if the food type is "snack", instantiates the create an array with 3 Strings assigned values of "chips" "apple" and "samosa"
		break;
	default:
		// add your own
		break;
	}

	return foods;
    // return an array based on the type of food selected
}