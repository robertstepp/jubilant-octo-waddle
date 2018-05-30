// Java Program by Robert Stepp 05/30/2018
/**
 * 
 * @author Robert Stepp 
 * @title Lab 10 Comprehensive 
 * @description Write a program to solve the
 *         “Vegetarians and Meat Eaters” problem. Three vegetarians and three
 *         hungry meat-eaters need to cross a river. Unfortunately, the boat
 *         only holds two people. If the meat-eaters outnumber the vegetarians
 *         on either bank, the vegetarians will be eaten! Please note: nobody
 *         gets to stay on the boat. When there’s three meat-eaters on one side,
 *         it doesn’t matter if one just came over on the boat or not. Your
 *         challenge is to find a series of moves that gets all three
 *         vegetarians and all three meat-eaters across the river safely. Write
 *         a program that solves the vegetarians and meat-eaters problem. You
 *         are given broad leeway on how to represent this problem. Recursion
 *         and backtracking (depth-first search) is one way to solve this.
 *
 * 
 */
class builder {

	private String name;
	private String type;
	private String location;

	public void setLocation(String location) {
		this.location = location;
	}

	public String getLocation() {
		return this.location;
	}

	public String getName() {
		return this.name;
	}
	public String getType() {
		return this.type;
	}

	public builder(String name, String type) {
		this.name = name;
		this.type = type;
		this.location = "south";
	}
}

public class MEvsVE {
	static String[] names = { "Robert", "George", "Jane", "Angelus", "Buffy", "Lestat" }; // List of names to use
	static String[] types = { "Meat Eater", "Vegetarian" }; // Types of people

	public static void main(String[] args) {
		builder me1 = new builder(names[0], types[0]);
		builder me2 = new builder(names[1], types[0]);
		builder me3 = new builder(names[2], types[0]);
		builder v1 = new builder(names[3], types[1]);
		builder v2 = new builder(names[4], types[1]);
		builder v3 = new builder(names[5], types[1]);
	}

}
