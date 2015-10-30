package assign9;

import java.util.ArrayList;

public class BinaryNode<Type extends Comparable<? super Type>> {
	
	private BinaryNode<Type> leftChild;
	private BinaryNode<Type> rightChild;
	private BinaryNode<Type> parent;
	private Type data;
	
	public BinaryNode(Type data, BinaryNode<Type> leftChild, BinaryNode<Type> rightChild, BinaryNode<Type> parent) {
		this.data = data;
		this.leftChild = leftChild;
		this.rightChild = rightChild;
		this.parent = parent;
	}
	
	public BinaryNode(Type data) {
		this(data, null, null, null);
	}
	
	public BinaryNode<Type> getLeftmostNode() {
		if(leftChild == null)
			return this;
		return leftChild.getLeftmostNode();
	}
	
	public BinaryNode<Type> getRightmostNode() {
		if(rightChild == null)
			return this;
		return rightChild.getRightmostNode();
	}
	
	public int height() {
		int leftCount = 0;
		int rightCount = 0;
		if(leftChild == null && rightChild == null) {
			return 0;
		}
		if(leftChild != null) {
			leftCount = 1 + leftChild.height();
		}
		if(rightChild != null) {
			rightCount = 1 + rightChild.height();
		}
		return Math.max(leftCount, rightCount);
	}
	
	public int size() {
		int leftCount = 0;
		int rightCount = 0;
		if(leftChild == null && rightChild == null) {
			return 0;
		}
		if(leftChild != null) {
			leftCount = 1 + leftChild.height();
		}
		if(rightChild != null) {
			rightCount = 1 + rightChild.height();
		}
		return leftCount + rightCount;
	}
	
	public ArrayList<Type> toArrayList(ArrayList<Type> arr) {
		if(leftChild != null)
			leftChild.toArrayList(arr);
		arr.add(this.getData());
		if(rightChild != null)
			rightChild.toArrayList(arr);
		return arr;
	}
	
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
	
	public BinaryNode<Type> getLeftChild() {
		return leftChild;
	}
	
	public void setLeftChild(BinaryNode<Type> leftChild) {
		this.leftChild = leftChild;
	}
	
	public BinaryNode<Type> getRightChild() {
		return rightChild;
	}
	
	public void setRightChild(BinaryNode<Type> rightChild) {
		this.rightChild = rightChild;
	}
	
	public BinaryNode<Type> getParent() {
		return parent;
	}
	
	public void setParent(BinaryNode<Type> parent) {
		this.parent = parent;
	}
	
	public Type getData() {
		return data;
	}
	
	public void setData(Type data) {
		this.data = data;
	}
}
