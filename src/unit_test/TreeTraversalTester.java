package unit_test;

import static org.junit.Assert.*;

import org.junit.Test;

import data_structures.BST;

public class TreeTraversalTester {
	private void create_helper(BST<Integer,Integer> D, int a, int b) {
		if (a == b-1) {
			D.insert(new Integer(2*a), new Integer(2*a+1));
		}
		else if (a < b-1) {
			int m = (a+b)/2;
			D.insert(new Integer(2*m), new Integer(2*m+1));
			create_helper(D, a, m);
			create_helper(D, m+1, b);
		}
	}
	
	private BST<Integer,Integer> create_BST(int n) {
		BST<Integer,Integer> D = new BST<Integer,Integer>();
		create_helper(D, 0, n);
		return D;
	}
	
	@Test
	public void test1(){
		BST<Integer,Integer> T = create_BST(64);
	}

}
