package assign9;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class BinaryTreeTester {

	private BinarySearchTree<Integer> tree;
	
	@Test(expected = NullPointerException.class)
	public void testAdd() {
		tree = new BinarySearchTree<Integer>();
		tree.add(null);
	}
	
	@Test
	public void testAdd1() {
		tree = new BinarySearchTree<Integer>();
		assertTrue(tree.add(1));
	}
	
	@Test
	public void testAdd2() {
		tree = new BinarySearchTree<Integer>();
		assertTrue(tree.add(1));
		assertTrue(tree.add(11));
		assertTrue(tree.add(111));
		assertTrue(tree.add(123));
		assertTrue(tree.add(10));
		
	}
	
	@Test
	public void testAdd3() {
		tree = new BinarySearchTree<Integer>();
		assertTrue(tree.add(1));
		assertTrue(tree.add(11));
		assertTrue(tree.add(111));
		assertTrue(tree.add(123));
		//Duplicate
		assertFalse(tree.add(111));
		
	}
	
	@Test
	public void testAddAll() {
		tree = new BinarySearchTree<Integer>();
		ArrayList<Integer> toBeAdded = new ArrayList<Integer>();
		toBeAdded.add(1);
		toBeAdded.add(2);
		toBeAdded.add(3);
		toBeAdded.add(4);
		toBeAdded.add(5);
		assertTrue(tree.addAll(toBeAdded));
	}
	
	@Test
	public void testAddAll1() {
		tree = new BinarySearchTree<Integer>();
		ArrayList<Integer> toBeAdded = new ArrayList<Integer>();
		toBeAdded.add(1);
		toBeAdded.add(2);
		toBeAdded.add(3);
		toBeAdded.add(4);
		toBeAdded.add(1);
		//Duplicate in list
		assertFalse(tree.addAll(toBeAdded));
	}
	
	@Test(expected = NullPointerException.class)
	public void testAddAll2() {
		tree = new BinarySearchTree<Integer>();
		ArrayList<Integer> toBeAdded = new ArrayList<Integer>();
		toBeAdded.add(1);
		toBeAdded.add(2);
		toBeAdded.add(3);
		toBeAdded.add(4);
		toBeAdded.add(null);
		//Null in list
		tree.addAll(toBeAdded);
	}
	
	@Test
	public void testSize() {
		tree = new BinarySearchTree<Integer>();
		assertEquals(0, tree.size());
	}
	
	@Test
	public void testSize1() {
		tree = new BinarySearchTree<Integer>();
		tree.add(1);
		tree.add(22);
		tree.add(11);
		assertEquals(3, tree.size());
	}
	
	@Test
	public void testSize2() {
		tree = new BinarySearchTree<Integer>();
		ArrayList<Integer> toBeAdded = new ArrayList<Integer>();
		toBeAdded.add(1);
		toBeAdded.add(2);
		toBeAdded.add(3);
		toBeAdded.add(4);
		toBeAdded.add(5);
		tree.addAll(toBeAdded);
		assertEquals(toBeAdded.size(), tree.size());
	}
	
	@Test
	public void testSize3() {
		tree = new BinarySearchTree<Integer>();
		tree.add(1);
		tree.add(22);
		tree.add(11);
		tree.clear();
		assertEquals(0, tree.size());
	}
	
	@Test
	public void testSize4() {
		tree = new BinarySearchTree<Integer>();
		tree.add(1);
		tree.add(22);
		tree.add(11);
		tree.add(11);
		//Duplicate
		assertEquals(3, tree.size());
	}
	
	@Test
	public void testSize5() {
		tree = new BinarySearchTree<Integer>();
		tree.add(1);
		tree.add(22);
		tree.add(11);
		tree.remove(22);
		assertEquals(2, tree.size());
	}

}
