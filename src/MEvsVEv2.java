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

	public static void buildTestCases(int total) {
		for (int i = 0; i <= total; i++) {
			for (int j = total; j >= 0; j--) {
				testCases.add("M" + i + "V" + j);
			}
		}
		removeDisparities();
	}

	public static void removeDisparities() {
		for (int i = testCases.size() - 1; i > 0; i--) {
			parseTestCases(testCases.get(i));
			if (tempForTest.get("M") > tempForTest.get("V")) {
				int position = testCases.indexOf(testCases.get(i));
				testCases.remove(position);
			}
		}
	}

	public static void parseTestCases(String testCase) {
		String[] temp = testCase.split("");
		tempForTest.put(temp[0], Integer.parseInt(temp[1]));
		tempForTest.put(temp[2], Integer.parseInt(temp[3]));
	}

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
				if (direction == 'd') {
					if (difference == -2) {
						TreeNode<String> child = parent.addChild(testCases.get(i));
						direction = 'u';
						if (!child.data.equals(endCase))
							buildTree(child);
					}

				} else if (direction == 'u') {
					System.out.println(difference);
					if (difference > 0 && difference < 2) {
						TreeNode<String> child = parent.addChild(testCases.get(i));
						direction = 'd';
						if (!child.data.equals(endCase))
							System.out.println(parent.data + " " + child.data);
						// buildTree(child);
					}

				}
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
