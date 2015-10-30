package assign9;

import java.util.ArrayList;

/**
 * Class representation of a Binary Node to be used in a Binary Search Tree.
 * 
 * @author Erik Martin and Nick Porter
 */

public class BinaryNode<Type extends Comparable<? super Type>> {
	
	private BinaryNode<Type> leftChild;
	private BinaryNode<Type> rightChild;
	private BinaryNode<Type> parent;
	private Type data;
	
	/**
	 * Constructor to initialize it to a state with children, data, and a parent.
	 * @param data
	 */
	public BinaryNode(Type data, BinaryNode<Type> leftChild, BinaryNode<Type> rightChild, BinaryNode<Type> parent) {
		this.data = data;
		this.leftChild = leftChild;
		this.rightChild = rightChild;
		this.parent = parent;
	}
	
	/**
	 * Constructor used when creating a "root" node
	 * @param data the data that this node will reference
	 */
	public BinaryNode(Type data) {
		this(data, null, null, null);
	}
	
	/**
	 * @return returns the left most node in the BST, which is also the minimum element.
	 */
	public BinaryNode<Type> getLeftmostNode() {
		if(leftChild == null)
			return this;
		return leftChild.getLeftmostNode();
	}
	
	/**
	 * @return returns the right most node in the BST, which is also the max element.
	 */
	public BinaryNode<Type> getRightmostNode() {
		if(rightChild == null)
			return this;
		return rightChild.getRightmostNode();
	}
	
	/**
	 * Returns the height of the binary tree rooted at this node. The height of
	 * a tree is the length of the longest path to a leaf node. Consider a tree
	 * with a single node to have a height of zero.
	 */
	public int size() {
		int leftCount = 0;
		int rightCount = 0;
		if(leftChild == null && rightChild == null) {
			return 0;
		}
		if(leftChild != null) {
			leftCount = 1 + leftChild.size();
		}
		if(rightChild != null) {
			rightCount = 1 + rightChild.size();
		}
		return leftCount + rightCount;
	}
	
	/**
	 * @param arr a new empty ArrayList
	 * @return A in-order representation of the BST
	 */
	public ArrayList<Type> toArrayList(ArrayList<Type> arr) {
		if(leftChild != null)
			leftChild.toArrayList(arr);
		arr.add(this.getData());
		if(rightChild != null)
			rightChild.toArrayList(arr);
		return arr;
	}
	
	/**
	 * @param item Item to be added
	 * @return true if the item was added, false otherwise
	 */
	public boolean add(Type item) {
		if(this.getData().compareTo(item) == 0) {
			return false;
		}
		if(this.getData().compareTo(item) < 0) {
			if(leftChild == null) {
				leftChild = new BinaryNode<Type>(item);
				leftChild.setParent(this);
				return true;
			}
			else {
				return leftChild.add(item);
			}
		}
		if(this.getData().compareTo(item) > 0) {
			if(rightChild == null) {
				rightChild = new BinaryNode<Type>(item);
				rightChild.setParent(this);
				return true;
			}
			else {
				return rightChild.add(item);
			}
		}
		return false;
	}
	
	/**
	 * @param item Item to be checked
	 * @return true if the item exists, false otherwise
	 */
	public boolean contains(Type item) {
		if(this.getData().compareTo(item) == 0) {
			return true;
		}
		if(this.getData().compareTo(item) < 0 && leftChild != null) {
			return leftChild.contains(item);
		}
		if(this.getData().compareTo(item) > 0 && rightChild != null) {
			return rightChild.contains(item);
		}
		return false;
	}
	
	/**
	 * @return The left child of this
	 */
	public BinaryNode<Type> getLeftChild() {
		return leftChild;
	}
	
	/**
	 * @param leftChild Value for leftChild to be assigned to.
	 */
	public void setLeftChild(BinaryNode<Type> leftChild) {
		this.leftChild = leftChild;
	}
	
	/**
	 * @return The right child of this
	 */
	public BinaryNode<Type> getRightChild() {
		return rightChild;
	}
	
	/**
	 * @param rightChild Value for rightChild to be assigned to.
	 */
	public void setRightChild(BinaryNode<Type> rightChild) {
		this.rightChild = rightChild;
	}
	
	/**
	 * @return The parent of this
	 */
	public BinaryNode<Type> getParent() {
		return parent;
	}
	
	/**
	 * @param parent Value for parent to be assigned to.
	 */
	public void setParent(BinaryNode<Type> parent) {
		this.parent = parent;
	}
	
	/**
	 * @return Data associated with this node
	 */
	public Type getData() {
		return data;
	}
	
	/**
	 * @param data Value for data to be assigned to.
	 */
	public void setData(Type data) {
		this.data = data;
	}
}
