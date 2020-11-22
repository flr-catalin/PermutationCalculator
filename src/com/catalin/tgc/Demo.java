package com.catalin.tgc;

import static com.catalin.tgc.PermutationUtils.compose;
import static com.catalin.tgc.PermutationUtils.cycles;
import static com.catalin.tgc.PermutationUtils.inverse;
import static com.catalin.tgc.PermutationUtils.print;
import static com.catalin.tgc.PermutationUtils.printCycles;
import static com.catalin.tgc.PermutationUtils.type;

/**
 * Demo program for PermutationUtils.
 * 
 * @author Catalin Florea
 */
public class Demo {
	
	public static void main(String[] args) {
		
		/* Tema 3 - 1) */
		int[] p1 = { 8, 1, 4, 9, 6, 3, 7, 5, 2 };
		int[] p2 = { 10, 9, 3, 5, 4, 8, 7, 6, 1, 2 };
		
		System.out.print("p1: ");
		print(p1);
		System.out.print("p2: ");
		print(p2);
		System.out.println();
		
		/* a) */
		System.out.print("Inversa lui p1: ");
		print(inverse(p1));
		System.out.print("p1 o p2: ");
		print(compose(p1, p2));
		System.out.print("p1 o p1 o p1: ");
		print(compose(p1, p1, p1));
		
//		/* b) */
		System.out.print("Structuri ciclice p1: ");
		printCycles(cycles(p1));
		System.out.print("Structuri ciclice p2: ");
		printCycles(cycles(p2));
		
//		/* c) */
		System.out.print("Tipul lui p1: ");
		print(type(p1), '[', ']');
		
	}
	
}
