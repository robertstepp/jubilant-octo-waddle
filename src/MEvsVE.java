import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

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
	static Map<String, Integer> tempPreviousParent = new HashMap<String, Integer>();
	static ArrayList<String> testCasesReciprocol = new ArrayList<String>();
	static PrintWriter output;
	static char direction = 'n';
	static TreeNode<String> root = new TreeNode<String>();

	public static void fillReciprocol() {
		Stack<String> reversal = new Stack<String>();
		for (int i = 0; i < testCases.size(); i++) {
			reversal.push(testCases.get(i));
		}
		int size = reversal.size();
		for (int j = 0; j < size; j++) {
			testCasesReciprocol.add((String) reversal.pop());
		}
		removeDisparities();
	}

	public static void buildTestCases(int total) {
		for (int i = 0; i <= total; i++) {
			for (int j = total; j >= 0; j--) {
				testCases.add("M" + i + "V" + j);
			}
		}
		fillReciprocol();
	}

	public static void removeDisparities() {
		for (int i = 0; i < testCases.size(); i++) {
			parseTestCases(testCases.get(i));
			if (tempForTest.get("M") > tempForTest.get("V")) {
				int position = testCases.indexOf(testCases.get(i));
				testCases.remove(position);
				testCasesReciprocol.remove(position);
			}
		}
	}

	public static void parseTestCases(String testCase) {
		String[] temp = testCase.split("");
		tempForTest.put(temp[0], Integer.parseInt(temp[1]));
		tempForTest.put(temp[2], Integer.parseInt(temp[3]));
	}

	public static void startTree(int total) {
		root.data = "M" + total + "V" + total;
		findMoveNorth(root);
	}

	public static void findMoveNorth(TreeNode<String> parent) {
		String[] temp = ((String) parent.data).split("");
		tempParent.put(temp[0], Integer.parseInt(temp[1]));
		tempParent.put(temp[2], Integer.parseInt(temp[3]));
		int parentTotal = tempParent.get("M") + tempParent.get("V");
		for (int tC = 0; tC < testCases.size(); tC++) {
			if (testCases.get(tC) != parent.data) {
				parseTestCases(testCases.get(tC));
				int testCaseTotal = tempForTest.get("M") + tempForTest.get("V");
				int difference = parentTotal - testCaseTotal;
				if (tempForTest.get("M") <= tempForTest.get("V") && difference <= 2 && difference > 0 && direction == 'n') {
					TreeNode<String> child = parent.addChild(testCases.get(tC));
					// System.out.println(testCases.get(tC));
					direction = 's';
					findMoveNorth(child);
				} else if (tempForTest.get("V") != 0 && difference < 0 && direction == 's') {
					if (tempForTest.get("M") <= tempForTest.get("V") && difference >= -2) {
						TreeNode<String> child = parent.addChild(testCasesReciprocol.get(tC));
						direction = 'n';
						findMoveNorth(child);
					}
				}
			} else if (testCases.get(tC) == parent.data) {
				System.out.println("Failure");
			}

		}

	}

	public static <T> void outputTree(TreeNode<T> t) {

		// if (t.getParent() == null)
		// System.out.print("Root node: ");

		// This recurses repeatedly through the parents of each branch of the
		// tree
		// This is O(n^2) and could be dramatically improved.
		TreeNode<T> parent = t.getParent();
		// System.out.print("\"");
		while (parent != null) {
			System.out.print("-");
			parent = parent.getParent();
		}
		System.out.println(t.data);
		// System.out.println(t.data + "\"");

		for (TreeNode<T> a : t.getChildren())
			outputTree(a);
	}

	public static void openFile() throws IOException {
		output = new PrintWriter(new FileWriter("./output.txt"));
	}

	public static void closeFile() {
		output.close();
	}

	public static void outputToFile(String message) {
		output.println(message);
	}

	public static void outputToConsole(String message) {
		System.out.println(message);
	}

	public static void main(String[] args) throws IOException {
		openFile();
		int input = Integer.parseInt(JOptionPane.showInputDialog(null, "How many of each to make?\nVegetarians\\MeatEaters\n(They will be even)", 3));
		buildTestCases(input);
		startTree(input);
		closeFile();
		outputTree(root);
	}

}
