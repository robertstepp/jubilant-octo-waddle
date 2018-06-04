// Java Program by Robert Stepp 05/30/2018
/**
 * 
 * @author Robert Stepp
 * @title Lab 10 Comprehensive
 * @description Write a program to solve the"Vegetarians and Meat Eaters"
 *              problem. Three vegetarians and three hungry meat-eaters need to
 *              cross a river. Unfortunately, the boat only holds two people. If
 *              the meat-eaters outnumber the vegetarians on either bank, the
 *              vegetarians will be eaten! Please note: nobody gets to stay on
 *              the boat. When there's three meat-eaters on one side, it doesn't
 *              matter if one just came over on the boat or not. Your challenge
 *              is to find a series of moves that gets all three vegetarians and
 *              all three meat-eaters across the river safely. Write a program
 *              that solves the vegetarians and meat-eaters problem. You are
 *              given broad leeway on how to represent this problem. Recursion
 *              and backtracking (depth-first search) is one way to solve this.
 *
 * 
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JOptionPane;

public class MEvsVEv2 {
	static ArrayList<String> testCases = new ArrayList<String>();
	static String endCase = "M0V0";
	static Map<String, Integer> tempForTest = new HashMap<String, Integer>();
	static Map<String, Integer> hashParent = new HashMap<String, Integer>();
	static TreeNode<String> root = new TreeNode<String>();
	static char direction = 'd';

	/**
	 * Build the test cases which will allow it to make a tree.
	 * 
	 * @param total
	 *            Number of each type to generate (defaults to 3)
	 */
	public static void buildTestCases(int total) {
		for (int i = 0; i <= total; i++) {
			for (int j = total; j >= 0; j--) {
				testCases.add("M" + i + "V" + j);
			}
		}
		// System.out.println(testCases);
		// removeDisparities();
	}

	/**
	 * Remove any values from the testCases that will cause there to be more Meat
	 * Eaters than Vegetarians.
	 */
	public static void removeDisparities() {
		for (int i = testCases.size() - 1; i > 0; i--) {
			parseTestCases(testCases.get(i));
			if (tempForTest.get("V") != 0 && tempForTest.get("M") > tempForTest.get("V")) {
				int position = testCases.indexOf(testCases.get(i));
				testCases.remove(position);
			}
		}

		// System.out.println(testCases);
	}

	/**
	 * Puts the testCase into a HashMap for various functions. Allow math to be
	 * performed on the value of each.
	 * 
	 * @param testCase
	 *            Case to be parsed.
	 */
	public static void parseTestCases(String testCase) {
		String[] temp = testCase.split("");
		tempForTest.put(temp[0], Integer.parseInt(temp[1]));
		tempForTest.put(temp[2], Integer.parseInt(temp[3]));
	}

	/**
	 * Builds the tree to search.
	 * 
	 * @param parent
	 *            The TreeNode that it is working on.
	 */
	public static void buildTree(TreeNode<String> parent) {
		String[] temp = ((String) parent.data).split("");
		hashParent.put(temp[0], Integer.parseInt(temp[1]));
		hashParent.put(temp[2], Integer.parseInt(temp[3]));
		int parentTotal = hashParent.get("M") + hashParent.get("V");
		for (int i = 0; i < testCases.size(); i++) {
			if (!testCases.get(i).equals(parent.data)) {
				parseTestCases(testCases.get(i));
				int testCaseTotal = tempForTest.get("M") + tempForTest.get("V");
				int difference = testCaseTotal - parentTotal;
				if (direction == 'd') { // This is to move them from the start to the finish.
					if (difference == -2) {
						TreeNode<String> child = parent.addChild(testCases.get(i));
						direction = 'u';
						if (!child.data.equals(endCase))
							buildTree(child);
					}

				} else if (direction == 'u') { // This is to move them back to the start.
					if (difference == 1 || difference == 2) {
						TreeNode<String> child = parent.addChild(testCases.get(i));
						direction = 'd';
						if (!child.data.equals(endCase)) {
							System.out.println(parent.data + " " + child.data);
							// buildTree(child); // Where everything breaks down.
						}
					}

				}
			}
		}

	}

	/**
	 * Outputs the tree from the top down.
	 * 
	 * @param <T>
	 *            Type of data to return.
	 * @param t
	 *            The treenode it is working on
	 */
	public static <T> void outputTree(TreeNode<T> t) {

		// if (t.getParent() == null)
		// System.out.print("Root node: ");

		// This recurses repeatedly through the parents of each branch of the
		// tree
		// This is O(n^2) and could be dramatically improved.
		TreeNode<T> parent = t.getParent();
		System.out.print("\"");
		while (parent != null) {
			System.out.print("-");
			parent = parent.getParent();
		}
		System.out.println(t.data);

		for (TreeNode<T> a : t.getChildren())
			outputTree(a);
	}

	public static void main(String[] args) {
		int input = Integer.parseInt(JOptionPane.showInputDialog(null, "How many of each to make?\nVegetarians\\MeatEaters\n(They will be even)", 3));
		buildTestCases(input);
		root.data = "M" + input + "V" + input;
		buildTree(root);
		outputTree(root);
	}

}
