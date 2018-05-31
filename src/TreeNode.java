import java.util.ArrayList;
import java.util.List;

// Thanks to http://stackoverflow.com/questions/19330731/tree-implementation-in-java-root-parents-and-children
// Edited by MCH, 20170503

public class TreeNode<T>{
    public T data = null;
    private List<TreeNode> children = new ArrayList<>();
    private TreeNode parent = null;

    public TreeNode() {
    }
    
    public TreeNode(T data) {
        this.data = data;
    }

    public TreeNode<T> addChild(TreeNode child) {
        child.setParent(this);
        this.children.add(child);
        return child;
    }

    public TreeNode<T> addChild(T data) {
        TreeNode<T> newChild = new TreeNode<>(data);
        newChild.setParent(this);
        children.add(newChild);
        return newChild;
    }

    public void addChildren(List<TreeNode> children) {
        for(TreeNode t : children) {
            t.setParent(this);
        }
        this.children.addAll(children);
    }

    public List<TreeNode> getChildren() {
        return children;
    }

    private void setParent(TreeNode parent) {
        this.parent = parent;
    }

    public TreeNode getParent() {
        return parent;
    }
}