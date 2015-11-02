package assign9;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.NoSuchElementException;

import org.junit.Test;

/**
 * JUnit test cases for BinarySearchTree.
 * 
 * @author Erik Martin and Nick Porter
 */

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
	public void testClear() {
		tree = new BinarySearchTree<Integer>();
		tree.add(1);
		tree.add(11);
		tree.add(111);
		tree.add(123);
		tree.add(10);
		
		tree.clear();
		assertEquals(0, tree.size());
	}
	
	@Test
	public void testClear1() {
		tree = new BinarySearchTree<Integer>();
		ArrayList<Integer> toBeAdded = new ArrayList<Integer>();
		toBeAdded.add(1);
		toBeAdded.add(2);
		toBeAdded.add(3);
		toBeAdded.add(4);
		tree.addAll(toBeAdded);
		
		tree.clear();
		assertEquals(0, tree.size());
	}
	
	@Test
	public void testContains() {
		tree = new BinarySearchTree<Integer>();
		tree.add(1);
		tree.add(11);
		tree.add(111);
		tree.add(123);
		tree.add(10);
		
		assertTrue(tree.contains(11));
	}
	
	@Test
	public void testContains1() {
		tree = new BinarySearchTree<Integer>();
		tree.add(1);
		tree.add(11);
		tree.add(111);
		tree.add(123);
		tree.add(10);
		
		assertTrue(tree.contains(123));
	}
	
	@Test
	public void testContains2() {
		tree = new BinarySearchTree<Integer>();
		tree.add(1);
		tree.add(11);
		tree.add(111);
		tree.add(123);
		tree.add(10);
		
		assertFalse(tree.contains(-1));
	}
	
	@Test
	public void testContains3() {
		tree = new BinarySearchTree<Integer>();
		tree.add(-1);
		tree.add(11);
		tree.add(111);
		tree.add(123);
		tree.add(10);
		
		assertTrue(tree.contains(-1));
	}
	
	@Test(expected = NullPointerException.class)
	public void testContains4() {
		tree = new BinarySearchTree<Integer>();
		tree.contains(null);
	}
	
	@Test
	public void testContainsAll() {
		tree = new BinarySearchTree<Integer>();
		ArrayList<Integer> toBeAdded = new ArrayList<Integer>();
		toBeAdded.add(1);
		toBeAdded.add(2);
		toBeAdded.add(3);
		toBeAdded.add(4);
		toBeAdded.add(5);
		
		tree.addAll(toBeAdded);
		assertTrue(tree.containsAll(toBeAdded));
	}
	
	@Test
	public void testContainsAll2() {
		tree = new BinarySearchTree<Integer>();
		ArrayList<Integer> toBeAdded = new ArrayList<Integer>();
		toBeAdded.add(1);
		toBeAdded.add(2);
		toBeAdded.add(3);
		toBeAdded.add(4);
		toBeAdded.add(5);
		
		tree.addAll(toBeAdded);
		toBeAdded.add(123);
		assertFalse(tree.containsAll(toBeAdded));
	}
	
	@Test(expected = NullPointerException.class)
	public void testContainsAll3() {
		tree = new BinarySearchTree<Integer>();
		ArrayList<Integer> toBeAdded = new ArrayList<Integer>();
		toBeAdded.add(1);
		toBeAdded.add(2);
		toBeAdded.add(3);
		toBeAdded.add(4);
		toBeAdded.add(5);
		toBeAdded.add(null);
		tree.addAll(toBeAdded);
	}
	
	@Test
	public void testFirst() {
		tree = new BinarySearchTree<Integer>();
		tree.add(1);
		tree.add(11);
		tree.add(111);
		tree.add(123);
		tree.add(10);
		
		assertEquals(new Integer(123), tree.first());
	}
	
	@Test
	public void testFirst1() {
		tree = new BinarySearchTree<Integer>();
		tree.add(-1);
		tree.add(-11);
		tree.add(-111);
		tree.add(-123);
		tree.add(-10);
		
		assertEquals(new Integer(-1), tree.first());
	}
	
	@Test(expected = NoSuchElementException.class)
	public void testFirst2() {
		tree = new BinarySearchTree<Integer>();
		tree.first();
	}
	
	
	@Test
	public void testFirst3() {
		tree = new BinarySearchTree<Integer>();
		tree.add(-1);
		tree.add(-11);
		tree.add(-111);
		tree.add(-123);
		tree.add(-10);
		
		assertEquals(new Integer(-1), tree.first());
	}
	
	@Test
	public void testIsEmpty() {
		tree = new BinarySearchTree<Integer>();
		assertTrue(tree.isEmpty());
	}
	
	@Test
	public void testIsEmpty1() {
		tree = new BinarySearchTree<Integer>();
		tree.add(-1);
		tree.add(-11);
		tree.add(-111);
		tree.add(-123);
		tree.add(-10);
		
		assertFalse(tree.isEmpty());
	}
	
	@Test
	public void testIsEmpty2() {
		tree = new BinarySearchTree<Integer>();
		tree.add(-1);
		tree.add(-11);
		tree.add(-111);
		tree.add(-123);
		tree.add(-10);
		tree.clear();
		assertTrue(tree.isEmpty());
	}
	
	@Test
	public void testIsEmpty3() {
		tree = new BinarySearchTree<Integer>();
		tree.add(-1);
		tree.remove(-1);
		
		assertTrue(tree.isEmpty());
	}
	
	@Test
	public void testLast() {
		tree = new BinarySearchTree<Integer>();
		tree.add(1);
		tree.add(11);
		tree.add(111);
		tree.add(123);
		tree.add(10);
		
		assertEquals(new Integer(1), tree.last());
	}
	
	@Test
	public void testLast1() {
		tree = new BinarySearchTree<Integer>();
		tree.add(-1);
		tree.add(-11);
		tree.add(-111);
		tree.add(-123);
		tree.add(-10);
		
		assertEquals(new Integer(-123), tree.last());
	}
	
	@Test(expected = NoSuchElementException.class)
	public void testLast2() {
		tree = new BinarySearchTree<Integer>();
		tree.last();
	}
	
	@Test
	public void testLast3() {
		tree = new BinarySearchTree<Integer>();
		tree.add(-1);
		tree.add(-11);
		tree.add(-111);
		tree.add(-123);
		tree.add(-10);
		
		assertEquals(new Integer(-123), tree.last());
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
	
	@Test
	public void testSize6() {
		tree = new BinarySearchTree<Integer>();
		tree.add(1);
		tree.add(22);
		tree.add(11);
		//Removing root
		tree.remove(1);
		assertEquals(2, tree.size());
	}
	
	@Test
	public void testToArrayList() {
		tree = new BinarySearchTree<Integer>();
		ArrayList<Integer> toBeAdded = new ArrayList<Integer>();
		toBeAdded.add(1);
		toBeAdded.add(2);
		toBeAdded.add(3);
		toBeAdded.add(4);
		toBeAdded.add(5);
		tree.addAll(toBeAdded);
		assertEquals(toBeAdded, tree.toArrayList());
	}
	
	@Test
	public void testToArrayList1() {
		tree = new BinarySearchTree<Integer>();
		assertEquals(new ArrayList<Integer>(), tree.toArrayList());
	}

}
