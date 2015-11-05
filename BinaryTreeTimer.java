package assign9;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.TreeSet;

public class BinaryTreeTimer {
	
	// Sample size controls
	private static int startSize   = 10000;
	private static int endSize     = 200000;
	private static int stepSize    = 10000;
	private static int timesToLoop = 100;
	
	private static long startTime, midpointTime, stopTime;					// Timer variables
	private static DecimalFormat formatter = new DecimalFormat("0000E0");	// Time string formatter
	
	public static void main(String[] args) {
		System.out.println("Experiment 1:");
		System.out.println("--------------------BST first() with sorted order input--------------------");
		System.out.println("N\tT(N)\t|\tT(N)/1\t\tT(N)/logN\t\tT(N)/N\t\tT(N)/NlogN\t\tT(N)/N^2");
		doExperiment1Sorted();
		System.out.println("--------------------BST first() with permuted order input--------------------");
		System.out.println("N\tT(N)\t|\tT(N)/1\t\tT(N)/logN\t\tT(N)/N\t\tT(N)/NlogN\t\tT(N)/N^2");
		doExperiment1Permuted();
		
		System.out.println("\nExperiment 2:");
		System.out.println("--------------------BST last() with sorted order input--------------------");
		System.out.println("N\tT(N)\t|\tT(N)/1\t\tT(N)/logN\t\tT(N)/N\t\tT(N)/NlogN\t\tT(N)/N^2");
		doExperiment2Sorted();
		System.out.println("--------------------BST last() with permuted order input--------------------");
		System.out.println("N\tT(N)\t|\tT(N)/1\t\tT(N)/logN\t\tT(N)/N\t\tT(N)/NlogN\t\tT(N)/N^2");
		doExperiment2Permuted();
		
		System.out.println("\nExperiment 3:");
		System.out.println("--------------------BST add(mid) with sorted order input--------------------");
		System.out.println("N\tT(N)\t|\tT(N)/1\t\tT(N)/logN\t\tT(N)/N\t\tT(N)/NlogN\t\tT(N)/N^2");
		doExperiment3Sorted();
		System.out.println("--------------------BST add(mid) with permuted order input--------------------");
		System.out.println("N\tT(N)\t|\tT(N)/1\t\tT(N)/logN\t\tT(N)/N\t\tT(N)/NlogN\t\tT(N)/N^2");
		doExperiment3Permuted();
		
		System.out.println("\nExperiment 4:");
		System.out.println("--------------------TreeSet first() with permuted order input--------------------");
		System.out.println("N\tT(N)\t|\tT(N)/1\t\tT(N)/logN\t\tT(N)/N\t\tT(N)/NlogN\t\tT(N)/N^2");
		doExperiment4();

		System.out.println("\nExperiment 5:");
		System.out.println("--------------------TreeSet last() with permuted order input--------------------");
		System.out.println("N\tT(N)\t|\tT(N)/1\t\tT(N)/logN\t\tT(N)/N\t\tT(N)/NlogN\t\tT(N)/N^2");
		doExperiment5();
		
		System.out.println("\nExperiment 6:");
		System.out.println("--------------------TreeSet add(mid) with permuted order input--------------------");
		System.out.println("N\tT(N)\t|\tT(N)/1\t\tT(N)/logN\t\tT(N)/N\t\tT(N)/NlogN\t\tT(N)/N^2");
		doExperiment6();
	}
	
	public static void doExperiment1Sorted() {
		int n = 0;
		
		for(n = startSize; n <= endSize; n += stepSize) {
			ArrayList<Integer> input = generateSortedOrder(n);
			BinarySearchTree<Integer> bst = new BinarySearchTree<>();
			for(int j = startSize - stepSize; j < startSize; j++) {
				bst.add(input.get(j));
			}
			
			System.out.print(n + "\t");
			
			// First, spin computing stuff until one second has gone by.
			// This allows this thread to stabilize.
			startTime = System.nanoTime();
			while(System.nanoTime() - startTime < 1000000000) { // empty block
				;
			}
			
			//Start first() timing
			startTime = System.nanoTime();
			midpointTime = startTime;
			
			for(int j = 0; j < timesToLoop; j++) {
				long tempStart = System.nanoTime();
				bst.first();
				long tempEnd = System.nanoTime();
				midpointTime += (tempEnd - tempStart);
			}
			
			stopTime = midpointTime;

			double averageTime = ((midpointTime - startTime) - (stopTime - midpointTime)) / timesToLoop;

			System.out.println(formatter.format(averageTime) + "\t|\t"
					+ formatter.format(averageTime / (Math.log10(n) / Math.log10(2))) + "\t\t"
					+ formatter.format(averageTime / n) + "\t\t"
					+ formatter.format(averageTime / (n * ((Math.log10(n) / Math.log10(2))))) + "\t\t"
					+ formatter.format(averageTime / (n * n)) + "\t\t"
					+ formatter.format(averageTime / (n * n * n)));
		}
	}
	
	public static void doExperiment1Permuted() {
		int n = 0;
		
		for(n = startSize; n <= endSize; n += stepSize) {
			ArrayList<Integer> input = generatePermutedOrder(n);
			BinarySearchTree<Integer> bst = new BinarySearchTree<>();
			
			System.out.print(n + "\t");
			
			// First, spin computing stuff until one second has gone by.
			// This allows this thread to stabilize.
			startTime = System.nanoTime();
			while(System.nanoTime() - startTime < 1000000000) { // empty block
				;
			}
			
			//Start first() timing
			startTime = System.nanoTime();
			midpointTime = startTime;
			
			for(int j = 0; j < timesToLoop; j++) {
				bst.clear();
				Collections.shuffle(input);
				bst.addAll(input);
				long tempStart = System.nanoTime();
				bst.first();
				long tempEnd = System.nanoTime();
				midpointTime += (tempEnd - tempStart);
			}
			
			stopTime = midpointTime;

			double averageTime = ((midpointTime - startTime) - (stopTime - midpointTime)) / timesToLoop;

			System.out.println(formatter.format(averageTime) + "\t|\t"
					+ formatter.format(averageTime / (Math.log10(n) / Math.log10(2))) + "\t\t"
					+ formatter.format(averageTime / n) + "\t\t"
					+ formatter.format(averageTime / (n * ((Math.log10(n) / Math.log10(2))))) + "\t\t"
					+ formatter.format(averageTime / (n * n)) + "\t\t"
					+ formatter.format(averageTime / (n * n * n)));
		}
	}
		
	public static void doExperiment2Sorted() {
		int n = 0;
		
		for(n = startSize; n <= endSize; n += stepSize) {
			ArrayList<Integer> input = generateSortedOrder(n);
			BinarySearchTree<Integer> bst = new BinarySearchTree<>();
			for(int j = startSize - stepSize; j < startSize; j++) {
				bst.add(input.get(j));
			}
			
			System.out.print(n + "\t");
			
			// First, spin computing stuff until one second has gone by.
			// This allows this thread to stabilize.
			startTime = System.nanoTime();
			while(System.nanoTime() - startTime < 1000000000) { // empty block
				;
			}
			
			//Start first() timing
			startTime = System.nanoTime();
			midpointTime = startTime;
			
			for(int j = 0; j < timesToLoop; j++) {
				long tempStart = System.nanoTime();
				bst.last();
				long tempEnd = System.nanoTime();
				midpointTime += (tempEnd - tempStart);
			}
			
			stopTime = midpointTime;

			double averageTime = ((midpointTime - startTime) - (stopTime - midpointTime)) / timesToLoop;

			System.out.println(formatter.format(averageTime) + "\t|\t"
					+ formatter.format(averageTime / (Math.log10(n) / Math.log10(2))) + "\t\t"
					+ formatter.format(averageTime / n) + "\t\t"
					+ formatter.format(averageTime / (n * ((Math.log10(n) / Math.log10(2))))) + "\t\t"
					+ formatter.format(averageTime / (n * n)) + "\t\t"
					+ formatter.format(averageTime / (n * n * n)));	
		}
	}
	
	public static void doExperiment2Permuted() {
		int n = 0;
		
		for(n = startSize; n <= endSize; n += stepSize) {
			ArrayList<Integer> input = generatePermutedOrder(n);
			BinarySearchTree<Integer> bst = new BinarySearchTree<>();
			
			System.out.print(n + "\t");
			
			// First, spin computing stuff until one second has gone by.
			// This allows this thread to stabilize.
			startTime = System.nanoTime();
			while(System.nanoTime() - startTime < 1000000000) { // empty block
				;
			}
			
			//Start first() timing
			startTime = System.nanoTime();
			midpointTime = startTime;
			
			for(int j = 0; j < timesToLoop; j++) {
				bst.clear();
				Collections.shuffle(input);
				bst.addAll(input);
				long tempStart = System.nanoTime();
				bst.last();
				long tempEnd = System.nanoTime();
				midpointTime += (tempEnd - tempStart);
			}
			
			stopTime = midpointTime;

			double averageTime = ((midpointTime - startTime) - (stopTime - midpointTime)) / timesToLoop;

			System.out.println(formatter.format(averageTime) + "\t|\t"
					+ formatter.format(averageTime / (Math.log10(n) / Math.log10(2))) + "\t\t"
					+ formatter.format(averageTime / n) + "\t\t"
					+ formatter.format(averageTime / (n * ((Math.log10(n) / Math.log10(2))))) + "\t\t"
					+ formatter.format(averageTime / (n * n)) + "\t\t"
					+ formatter.format(averageTime / (n * n * n)));	
		}
	}
	
	public static void doExperiment3Sorted() {
		int n = 0;
		
		for(n = startSize; n <= endSize; n += stepSize) {
			ArrayList<Integer> input = generateSortedOrder(n);
			BinarySearchTree<Integer> bst = new BinarySearchTree<>();
			for(int j = startSize - stepSize; j < startSize; j++) {
				bst.add(input.get(j));
			}
			
			System.out.print(n + "\t");
			
			// First, spin computing stuff until one second has gone by.
			// This allows this thread to stabilize.
			startTime = System.nanoTime();
			while(System.nanoTime() - startTime < 1000000000) { // empty block
				;
			}
			
			//Start first() timing
			startTime = System.nanoTime();
			midpointTime = startTime;
			
			for(int j = 0; j < timesToLoop; j++) {
				long tempStart = System.nanoTime();
				bst.add(n/2);
				long tempEnd = System.nanoTime();
				midpointTime += (tempEnd - tempStart);
			}
			
			stopTime = midpointTime;

			double averageTime = ((midpointTime - startTime) - (stopTime - midpointTime)) / timesToLoop;

			System.out.println(formatter.format(averageTime) + "\t|\t"
					+ formatter.format(averageTime / (Math.log10(n) / Math.log10(2))) + "\t\t"
					+ formatter.format(averageTime / n) + "\t\t"
					+ formatter.format(averageTime / (n * ((Math.log10(n) / Math.log10(2))))) + "\t\t"
					+ formatter.format(averageTime / (n * n)) + "\t\t"
					+ formatter.format(averageTime / (n * n * n)));	
		}
	}
	
	public static void doExperiment3Permuted() {
		int n = 0;
		
		for(n = startSize; n <= endSize; n += stepSize) {
			ArrayList<Integer> input = generatePermutedOrder(n);
			BinarySearchTree<Integer> bst = new BinarySearchTree<>();
			
			System.out.print(n + "\t");
			
			// First, spin computing stuff until one second has gone by.
			// This allows this thread to stabilize.
			startTime = System.nanoTime();
			while(System.nanoTime() - startTime < 1000000000) { // empty block
				;
			}
			
			//Start first() timing
			startTime = System.nanoTime();
			midpointTime = startTime;
			
			for(int j = 0; j < timesToLoop; j++) {
				bst.clear();
				Collections.shuffle(input);
				bst.addAll(input);
				long tempStart = System.nanoTime();
				bst.add(n/2);
				long tempEnd = System.nanoTime();
				midpointTime += (tempEnd - tempStart);
			}
			
			stopTime = midpointTime;

			double averageTime = ((midpointTime - startTime) - (stopTime - midpointTime)) / timesToLoop;

			System.out.println(formatter.format(averageTime) + "\t|\t"
					+ formatter.format(averageTime / (Math.log10(n) / Math.log10(2))) + "\t\t"
					+ formatter.format(averageTime / n) + "\t\t"
					+ formatter.format(averageTime / (n * ((Math.log10(n) / Math.log10(2))))) + "\t\t"
					+ formatter.format(averageTime / (n * n)) + "\t\t"
					+ formatter.format(averageTime / (n * n * n)));	
		}
	}
	
	public static void doExperiment4() {
		int n = 0;
		
		for(n = startSize; n <= endSize; n += stepSize) {
			ArrayList<Integer> input = generatePermutedOrder(n);
			TreeSet<Integer> ts = new TreeSet<>();
			
			System.out.print(n + "\t");
			
			// First, spin computing stuff until one second has gone by.
			// This allows this thread to stabilize.
			startTime = System.nanoTime();
			while(System.nanoTime() - startTime < 1000000000) { // empty block
				;
			}
			
			//Start first() timing
			startTime = System.nanoTime();
			midpointTime = startTime;
			
			for(int j = 0; j < timesToLoop; j++) {
				ts.clear();
				Collections.shuffle(input);
				ts.addAll(input);
				long tempStart = System.nanoTime();
				ts.first();
				long tempEnd = System.nanoTime();
				midpointTime += (tempEnd - tempStart);
			}
			
			stopTime = midpointTime;

			double averageTime = ((midpointTime - startTime) - (stopTime - midpointTime)) / timesToLoop;

			System.out.println(formatter.format(averageTime) + "\t|\t"
					+ formatter.format(averageTime / (Math.log10(n) / Math.log10(2))) + "\t\t"
					+ formatter.format(averageTime / n) + "\t\t"
					+ formatter.format(averageTime / (n * ((Math.log10(n) / Math.log10(2))))) + "\t\t"
					+ formatter.format(averageTime / (n * n)) + "\t\t"
					+ formatter.format(averageTime / (n * n * n)));
		}
	}
	
	public static void doExperiment5() {
		int n = 0;
		
		for(n = startSize; n <= endSize; n += stepSize) {
			ArrayList<Integer> input = generatePermutedOrder(n);
			TreeSet<Integer> ts = new TreeSet<>();
			
			System.out.print(n + "\t");
			
			// First, spin computing stuff until one second has gone by.
			// This allows this thread to stabilize.
			startTime = System.nanoTime();
			while(System.nanoTime() - startTime < 1000000000) { // empty block
				;
			}
			
			//Start first() timing
			startTime = System.nanoTime();
			midpointTime = startTime;
			
			for(int j = 0; j < timesToLoop; j++) {
				ts.clear();
				Collections.shuffle(input);
				ts.addAll(input);
				long tempStart = System.nanoTime();
				ts.last();
				long tempEnd = System.nanoTime();
				midpointTime += (tempEnd - tempStart);
			}
			
			stopTime = midpointTime;

			double averageTime = ((midpointTime - startTime) - (stopTime - midpointTime)) / timesToLoop;

			System.out.println(formatter.format(averageTime) + "\t|\t"
					+ formatter.format(averageTime / (Math.log10(n) / Math.log10(2))) + "\t\t"
					+ formatter.format(averageTime / n) + "\t\t"
					+ formatter.format(averageTime / (n * ((Math.log10(n) / Math.log10(2))))) + "\t\t"
					+ formatter.format(averageTime / (n * n)) + "\t\t"
					+ formatter.format(averageTime / (n * n * n)));
		}
	}
	
	public static void doExperiment6() {
		int n = 0;
		
		for(n = startSize; n <= endSize; n += stepSize) {
			ArrayList<Integer> input = generatePermutedOrder(n);
			TreeSet<Integer> ts = new TreeSet<>();
			
			System.out.print(n + "\t");
			
			// First, spin computing stuff until one second has gone by.
			// This allows this thread to stabilize.
			startTime = System.nanoTime();
			while(System.nanoTime() - startTime < 1000000000) { // empty block
				;
			}
			
			//Start first() timing
			startTime = System.nanoTime();
			midpointTime = startTime;
			
			for(int j = 0; j < timesToLoop; j++) {
				ts.clear();
				Collections.shuffle(input);
				ts.addAll(input);
				long tempStart = System.nanoTime();
				ts.add(n/2);
				long tempEnd = System.nanoTime();
				midpointTime += (tempEnd - tempStart);
			}
			
			stopTime = midpointTime;

			double averageTime = ((midpointTime - startTime) - (stopTime - midpointTime)) / timesToLoop;

			System.out.println(formatter.format(averageTime) + "\t|\t"
					+ formatter.format(averageTime / (Math.log10(n) / Math.log10(2))) + "\t\t"
					+ formatter.format(averageTime / n) + "\t\t"
					+ formatter.format(averageTime / (n * ((Math.log10(n) / Math.log10(2))))) + "\t\t"
					+ formatter.format(averageTime / (n * n)) + "\t\t"
					+ formatter.format(averageTime / (n * n * n)));
		}
	}
	
	/**
	 * This method generates an ArrayList of integers from 1 to size in ascending order.
	 * @param size The size of the ArrayList to be generated.
	 * @return The generated list, as an ArrayList
	 */
	public static ArrayList<Integer> generateSortedOrder(int size) {
		ArrayList<Integer> arr = new ArrayList<Integer>();
		for(int j = 1; j <= size; j++) {
			arr.add(j);
		}
		return arr;
	}
	
	/**
	 * This method generates an ArrayList of integers from 1 to size in permuted (i.e. random) order.
	 * @param size The size of the ArrayList to be generated.
	 * @return The generated list, as an ArrayList
	 */
	public static ArrayList<Integer> generatePermutedOrder(int size) {
		ArrayList<Integer> arr = new ArrayList<Integer>();
		for(int j = 1; j <= size; j++) {
			arr.add(j);
		}
		scramble(arr);
		return arr;
	}
	
	/**
	 * This method scrambles a given ArrayList of integers.
	 * @param arr The ArrayList to be scrambled.
	 */
	private static void scramble(ArrayList<Integer> arr) {
		Random r = new Random();
		for(int j = 0; j < arr.size(); j++) {
			Integer temp = arr.get(j);
			arr.remove(j);
			arr.add(r.nextInt(arr.size()+1), temp);
		}
	}
	
	/**
	 * This method generates an ArrayList of integers from 1 to size in descending order.
	 * @param size The size of the ArrayList to be generated.
	 * @return The generated list, as an ArrayList
	 */
	public static ArrayList<Integer> generateReverseSortedOrder(int size) {
		ArrayList<Integer> arr = new ArrayList<Integer>();
		for(int j = size; j >= 1; j--) {
			arr.add(j);
		}
		return arr;
	}
}
