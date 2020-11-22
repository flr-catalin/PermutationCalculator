package com.catalin.tgc;

import java.util.ArrayList;
import java.util.List;

/**
 * Utilitary class for permutation related calculations.
 * 
 * @author Catalin Florea
 */
public class PermutationUtils {
	
	/**
	 * Calculates the inverse of a permutation.
	 * 
	 * @param permutation the permutation
	 * @return the inverse of the permutation
	 */
	public static int[] inverse(int[] permutation) {
		int[] inverse = permutation.clone();
		
		for (int index = 0; index < permutation.length; index++) {
			inverse[permutation[index] - 1] = index + 1;
		}
		
		return inverse;
	}

	/**
	 * Composes a series of any number of permutations.
	 * 
	 * @param permutations the permutations to compose
	 * @return the composed permutation
	 */
	public static int[] compose(int[]... permutations) {
		if (permutations.length < 2) {
			throw new IllegalArgumentException("The should be at least two permutations.");
		}
		
		int[] composed = permutations[0];
		
		for (int i = 1; i < permutations.length; i++) {
			composed = compose(composed, permutations[i]);
		}
		
		return composed;
	}

	/**
	 * Calculates the cycles present in a permutation.
	 * 
	 * @param permutation the permutation in which we saerch for cycles
	 * @return the list of cycles present in the permutation
	 */
	public static List<int[]> cycles(int[] permutation) {
		List<int[]> cycles = new ArrayList<>();
		
		Integer cycleStart = 1;
		while (cycleStart < permutation.length) {
			while (isVisited(cycles, cycleStart)) {
				if (cycleStart == permutation.length) {
					return cycles;
				}
				
				cycleStart++;
			}
			
			List<Integer> currentCycle = new ArrayList<>();
			Integer currentElement = cycleStart;
			currentCycle.add(cycleStart);

			while (permutation[currentElement - 1] != cycleStart) {
				currentCycle.add(permutation[currentElement - 1]);
				currentElement = permutation[currentElement - 1];
			}
			
			int[] cycle = currentCycle.stream().mapToInt(i->i).toArray();
			cycles.add(cycle);
			
			cycleStart++;
		}
		
		return cycles;
	}
	
	/**
	 * Calculates the type of a permutation based on the
	 * list of cycles the permutation has.
	 * 
	 * @param permutation the permutation for which we calculate the type
	 * @return the type of the permutation
	 */
	public static int[] type(int[] permutation) {
		List<int[]> cycles = cycles(permutation);
		int[] type = permutation;
		
		for (int index = 0; index < type.length; index++) {
			type[index] = 0;
		}
		
		for (int[] cycle : cycles) {
			type[cycle.length - 1]++;
		}
		
		return type;
	}
	
	/**
	 * Prints a single permutation.
	 * 
	 * @param permutation the permutation to print
	 */
	public static void print(int[] permutation) {
		System.out.println(wrapPrintOutput(permutation, '(', ')'));
	}
	
	/**
	 * Prints a single permutation with specific start and end wrappers.
	 * 
	 * @param permutation the permutation to print
	 * @param startWrapper the start wrapper (e.g. '(')
	 * @param endWrapper the end wrapper (e.g. ')')
	 */
	public static void print(int[] permutation, char startWrapper, char endWrapper) {
		System.out.println(wrapPrintOutput(permutation, startWrapper, endWrapper));
	}
	
	/**
	 * Prints the cycles of a permutation.
	 * 
	 * @param permutationList the list of cycles
	 */
	public static void printCycles(List<int[]> permutationList) {
		System.out.println(getPrintCyclesOutput(permutationList));
	}
	
	/**
	 * Generates the print output for printing the cycles of a permutation.
	 * 
	 * @param permutationList the cycles of a permutation
	 * @return the formatted print output
	 */
	private static String getPrintCyclesOutput(List<int[]> permutationList) {
		StringBuilder builder = new StringBuilder();
		
		for (int[] permutation : permutationList) {
			builder.append(wrapPrintOutput(permutation, '(', ')'));
		}
		
		return builder.toString();
	}
	
	/**
	 * Generates the print output for printing a permutation.
	 * 
	 * @param permutation the permutation to print
	 * @return the formatted print output
	 */
	private static String getPrintOutput(int[] permutation) {
		StringBuilder builder = new StringBuilder();
		
		for (int index = 0; index < permutation.length; index++) {
			builder.append(permutation[index]);
			builder.append(", ");
		}
		
		builder.setLength(builder.length() - 2);
		
		return builder.toString();
	}
	
	/**
	 * Wraps the permutation with specific start end end wrappers.
	 * 
	 * @param permutation the permutation to wrap
	 * @param startWrapper the start wrapper (e.g. '(')
	 * @param endWrapper the end wrapper (e.g. ')')
	 * @return the wrapped print output
	 */
	private static String wrapPrintOutput(int[] permutation, char startWrapper, char endWrapper) {
		StringBuilder builder = new StringBuilder();
		builder.append(startWrapper);
		builder.append(getPrintOutput(permutation));
		builder.append(endWrapper);
		
		return builder.toString();
	}
	
	/**
	 * Internal compose function that only composes two permutations at once.
	 * 
	 * @param permutation1 the first permutation
	 * @param permutation2 the second permutation
	 * @return the composed permutation
	 */
	private static int[] compose(int[] permutation1, int[] permutation2) {
		if (permutation1.length != permutation2.length) {
			throw new IllegalArgumentException("The length of the permutations must be the same.");
		}
		
		int[] composed = permutation1.clone();
		
		for (int index = 0; index < permutation2.length; index++) {
			composed[index] = permutation1[permutation2[index] - 1];
		}
		
		return composed;
	}
	
	/**
	 * Checks if an element is present in the list of cycles.
	 * 
	 * @param cycles the list of cycles
	 * @param element the element we check is present
	 * @return whether the element is present
	 */
	private static boolean isVisited(List<int[]> cycles, int element) {
		for (int[] cycle : cycles) {
			for (int i = 0 ; i < cycle.length; i++) {
				if (element == cycle[i]) {
					return true;
				}
			}
		}
		
		return false;
	}
	
}
