package assign9;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.NoSuchElementException;

/**
 * Class representation of a Binary Search Tree with a root node.
 * 
 * @author Erik Martin and Nick Porter
 */

public class BinarySearchTree<Type extends Comparable<? super Type>> implements SortedSet<Type> {
	
	private BinaryNode<Type> root;
	private int size;
	
	public BinarySearchTree() {
		size = 0;
		root = new BinaryNode<Type>(null);
	}

	@Override
	/**
	 * Ensures that this set contains the specified item.
	 * 
	 * @param item
	 *          - the item whose presence is ensured in this set
	 * @return true if this set changed as a result of this method call (that is,
	 *         if the input item was actually inserted); otherwise, returns false
	 * @throws NullPointerException
	 *           if the item is null
	 */
	public boolean add(Type item) {
		if(item == null)
			throw new NullPointerException("Item is null.");
		if(size == 0) {
			root = new BinaryNode<Type>(item);
			size++;
			return true;
		}
		
		if (root.add(item)) {
			size++;
			return true;
		} 
		return false;
	}

	@Override
	/**
	 * Ensures that this set contains all items in the specified collection.
	 * 
	 * @param items
	 *          - the collection of items whose presence is ensured in this set
	 * @return true if this set changed as a result of this method call (that is,
	 *         if any item in the input collection was actually inserted);
	 *         otherwise, returns false
	 * @throws NullPointerException
	 *           if any of the items is null
	 */
	public boolean addAll(Collection<? extends Type> items) {
		for(Type t : items) {
			if(!add(t))
				return false;
		}
		return true;
	}

	@Override
	/**
	 * Removes all items from this set. The set will be empty after this method
	 * call.
	 */
	public void clear() {
		root = null;
		size = 0;
	}

	@Override
	/**
	 * Determines if there is an item in this set that is equal to the specified
	 * item.
	 * 
	 * @param item
	 *          - the item sought in this set
	 * @return true if there is an item in this set that is equal to the input
	 *         item; otherwise, returns false
	 * @throws NullPointerException
	 *           if the item is null
	 */
	public boolean contains(Type item) {
		if(item == null)
			throw new NullPointerException("Item is null.");
		return root.contains(item);
	}

	@Override
	/**
	 * Determines if for each item in the specified collection, there is an item
	 * in this set that is equal to it.
	 * 
	 * @param items
	 *          - the collection of items sought in this set
	 * @return true if for each item in the specified collection, there is an item
	 *         in this set that is equal to it; otherwise, returns false
	 * @throws NullPointerException
	 *           if any of the items is null
	 */
	public boolean containsAll(Collection<? extends Type> items) {
		for(Type t : items) {
			if(!contains(t))
				return false;
		}
		return true;
	}

	@Override
	/**
	 * Returns the first (i.e., smallest) item in this set.
	 * 
	 * @throws NoSuchElementException
	 *           if the set is empty
	 */
	public Type first() throws NoSuchElementException {
		if(isEmpty()) {
			throw new NoSuchElementException("Set is empty.");
		}
		return root.getLeftmostNode().getData();
	}

	@Override
	/**
	 * Returns true if this set contains no items.
	 */
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	/**
	 * Returns the last (i.e., largest) item in this set.
	 * 
	 * @throws NoSuchElementException
	 *           if the set is empty
	 */
	public Type last() throws NoSuchElementException {
		if(isEmpty()) {
			throw new NoSuchElementException("Set is empty.");
		}
		return root.getRightmostNode().getData();
	}

	@Override
	/**
	 * Ensures that this set does not contain the specified item.
	 * 
	 * @param item
	 *          - the item whose absence is ensured in this set
	 * @return true if this set changed as a result of this method call (that is,
	 *         if the input item was actually removed); otherwise, returns false
	 * @throws NullPointerException
	 *           if the item is null
	 */
	public boolean remove(Type item) {
		if(item == null)
			throw new NullPointerException("Item is null.");

		//Get BinaryNode objects to represent the necessary nodes
		BinaryNode<Type> toBeRemoved = get(item);
		
		if (toBeRemoved == root) {
			//Does not have a parent, boolean expression was throwing NullPointer.
			if (root.isLeaf()) {
				clear();
				return true;
			}
			
			if (root.getRightChild() != null) {
				root = root.successor();
			}
			
			if (root.getLeftChild() != null) {
				root = root.getLeftChild();
			}
			//Can't think of a case where removing the root would fail.
			size--;
			return true;
		}
		
		BinaryNode<Type> oldParent = toBeRemoved.getParent();
		boolean isLeftChild = toBeRemoved.getData().compareTo(oldParent.getData()) < 0;
		
		//CASE A
		//If the node to be removed has no children, simply delete it, and adjust its parent's reference.
		if(toBeRemoved.isLeaf()) {
			toBeRemoved.setParent(null);
			size--;
			if(isLeftChild) {
				oldParent.setLeftChild(null);
				return true;
			}
			oldParent.setRightChild(null);
			return true;
		}
		
		//CASE B
		//If the node to be removed has only a right child, adjust its parent to reference its child directly
		if(toBeRemoved.getLeftChild() == null && toBeRemoved.getRightChild() != null) {
			toBeRemoved.setParent(null);
			size--;
			if(isLeftChild) {
				oldParent.setLeftChild(toBeRemoved.getRightChild());
				return true;
			}
			oldParent.setRightChild(toBeRemoved.getRightChild());
			return true;
		}
		
		//If the node to be removed has only a left child, adjust its parent to reference its child directly
		if(toBeRemoved.getLeftChild() != null && toBeRemoved.getRightChild() == null) {
			toBeRemoved.setParent(null);
			size--;
			if(isLeftChild) {
				oldParent.setLeftChild(toBeRemoved.getLeftChild());
				return true;
			}
			oldParent.setRightChild(toBeRemoved.getLeftChild());
			return true;
		}
		
		//CASE C
		//If the node has two children, replace the node with its successor, and adjust pointers as necessary
		if(toBeRemoved.getLeftChild() != null && toBeRemoved.getRightChild() != null) {
			BinaryNode<Type> successor = toBeRemoved.successor();
			BinaryNode<Type> successorParent = successor.getParent();
			toBeRemoved.setParent(null);
			if(isLeftChild) {
				oldParent.setLeftChild(successor);
				successor.setParent(oldParent);
			}
			else {
				oldParent.setRightChild(successor);
				successor.setParent(oldParent);
			}
			if(successor.getRightChild() != null) {
				successorParent.setLeftChild(successor.getRightChild());
				successor.getRightChild().setParent(successorParent);
			}
			else {
				successorParent.setLeftChild(null);
			}
			successor.setLeftChild(toBeRemoved.getLeftChild());
			toBeRemoved.getLeftChild().setParent(successor);
			successor.setRightChild(toBeRemoved.getRightChild());
			toBeRemoved.getRightChild().setParent(successor);
			size--;
			return true;
		}
		return false;
	}

	@Override
	/**
	 * Ensures that this set does not contain any of the items in the specified
	 * collection.
	 * 
	 * @param items
	 *          - the collection of items whose absence is ensured in this set
	 * @return true if this set changed as a result of this method call (that is,
	 *         if any item in the input collection was actually removed);
	 *         otherwise, returns false
	 * @throws NullPointerException
	 *           if any of the items is null
	 */
	public boolean removeAll(Collection<? extends Type> items) {
		for(Type t : items) {
			if(!remove(t))
				return false;
		}
		return true;
	}

	@Override
	/**
	 * Returns the number of items in this set.
	 */
	public int size() {
		return size;
	}

	@Override
	/**
	 * Returns an ArrayList containing all of the items in this set, in sorted
	 * order.
	 */
	public ArrayList<Type> toArrayList() {
		if (isEmpty()) {
			return new ArrayList<Type>();
		}
		//Root could be null
		ArrayList<Type> list = root.toArrayList(new ArrayList<Type>());
		Collections.reverse(list);
		return list;
	}
	
	/**
	 * @param item The data associated with the desired node
	 * @return The BinaryNode associated with item, null if no node in the tree contains item.
	 */
	public BinaryNode<Type> get(Type item) {
		if(item == null)
			throw new NullPointerException("Item is null.");
		return root.get(item);
	}
	
	/**
	 * Generates a dot file from this tree
	 * 
	 * @param filename Name of the file to be saved.
	 */
	public void buildDotFromTree(String filename) {
		PrintWriter out = null;

		try {
			out = new PrintWriter(filename);
		} catch (IOException e) {
			System.out.println(e);
		}

		// Open the graph
		out.println("digraph G {");
		out.println("node [shape=circle, color=black]");

		// Build graph from nodes
		ArrayList<String> nodeList = new ArrayList<String>();
		// Build a list of strings to be printed by the PrintWriter
		recursiveDotBuilder(root, nodeList);

		for (String node : nodeList) {
			out.println(node);
		}

		// Close the graph
		out.println("}");
		out.close();
	}

	/**
	 * Private recursive method to build a dot file from this tree
	 * 
	 * @param node node to be used
	 * @param nodeList list of all nodes
	 */
	private void recursiveDotBuilder(BinaryNode<Type> node, ArrayList<String> nodeList) {
		
		if (node.getLeftChild() != null) {
			
			recursiveDotBuilder(node.getLeftChild(), nodeList);
			nodeList.add("edge [dir=right color=\"black\"]");
			nodeList.add("\t" + node.getData() + "->" + node.getLeftChild().getData());
		}

		if (node.getRightChild() != null) {
			
			recursiveDotBuilder(node.getRightChild(), nodeList);
			nodeList.add("edge [dir=right color=\"black\"]");
			nodeList.add("\t" + node.getData() + "->" + node.getRightChild().getData());
		}

	}
	
}
