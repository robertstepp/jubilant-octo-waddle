import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JOptionPane;

// Java Program by Robert Stepp 05/30/2018
/**
 * 
 * @author Robert Stepp
 * @title Lab 10 Comprehensive
 * @description Write a program to solve the “Vegetarians and Meat Eaters”
 *              problem. Three vegetarians and three hungry meat-eaters need to
 *              cross a river. Unfortunately, the boat only holds two people. If
 *              the meat-eaters outnumber the vegetarians on either bank, the
 *              vegetarians will be eaten! Please note: nobody gets to stay on
 *              the boat. When there’s three meat-eaters on one side, it doesn’t
 *              matter if one just came over on the boat or not. Your challenge
 *              is to find a series of moves that gets all three vegetarians and
 *              all three meat-eaters across the river safely. Write a program
 *              that solves the vegetarians and meat-eaters problem. You are
 *              given broad leeway on how to represent this problem. Recursion
 *              and backtracking (depth-first search) is one way to solve this.
 *
 * 
 */

public class MEvsVE {
	static ArrayList<String> testCases = new ArrayList<String>();
	static Map<String, Integer> tempForTest = new HashMap<String, Integer>();
	static Map<String, Integer> tempParent = new HashMap<String, Integer>();
	static ArrayList<String> testCasesReciprocol = new ArrayList<String>();
	static HashMap<String, Integer> south = new HashMap<String, Integer>() { // Start
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		{
			put("Meat Eaters", 3);
			put("Vegetarians", 3);
		}
	};
	static HashMap<String, Integer> north = new HashMap<String, Integer>() { // End
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		{
			put("Meat Eaters", 0);
			put("Vegetarians", 0);
		}
	};

	public static void fillReciprocol() {
		for (int r = 0; r < testCases.size(); r++) {
			testCasesReciprocol.add(testCases.get(testCases.size() - (r + 1)));
		}
		System.out.println(testCases + "\n" + testCasesReciprocol);
	}

	public static void buildTestCases(int total) {
		for (int i = 0; i <= total; i++) {
			for (int j = total; j >= 0; j--) {
				testCases.add("M" + i + "V" + j);
			}
		}
		for (int l = 0; l < testCases.size(); l++) { // This is what will parse the test case to test against the next values.
			parseTestCases(testCases.get(l));
		}
		fillReciprocol();
	}

	public static void parseTestCases(String testCase) {
		String[] temp = testCase.split("");
		tempForTest.put(temp[0], Integer.parseInt(temp[1]));
		tempForTest.put(temp[2], Integer.parseInt(temp[3]));
		// checkNext();
	}

	public static void startTree(int total) {
		TreeNode<String> root = new TreeNode<String>();
		root.data = "M" + total + "V" + total;
		findNext(root.data);
	}

	public static void findNext(String parent) {
		String[] temp = parent.split("");
		tempParent.put(temp[0], Integer.parseInt(temp[1]));
		tempParent.put(temp[2], Integer.parseInt(temp[3]));

	}

	public static void main(String[] args) {
		int input = Integer.parseInt(JOptionPane.showInputDialog(null, "How many of each to make?\nVegetarians\\MeatEaters\n(They will be even)", 3));
		buildTestCases(input);
		startTree(input);
	}
}