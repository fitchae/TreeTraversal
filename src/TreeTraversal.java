import java.util.ArrayList;

import data_structures.IntBST;
import data_structures.LinkedList;
import data_structures.TreeNode;
/**
 * @author fitchae
 * Worked with Steven Bower, Adrianna Johnson, and Sam Duemler
 * November 8th, 2016
 */
public class TreeTraversal {

	/**
	 * Given a TreeNode<Integer,Integer> object representing the root of a binary search tree,
	 * return an ArrayList<Integer> holding the keys in order from a post-order traversal.
	 * RESTRICTION: Use recursion for this implementation.
	 * @param r
	 * @return Array list
	 */
	public static ArrayList<Integer> postOrder(TreeNode<Integer,Integer> r) {
		ArrayList<Integer> data = new ArrayList<>();
		dataAdd(r,data);
		return data;
	}
	
	public static void dataAdd(TreeNode<Integer,Integer> c, ArrayList<Integer> d){
		if(c==null){
			return;
		}else{
			dataAdd(c.left, d);
			dataAdd(c.right,d);
			d.add(c.key);
		}
		
	}
	

	/**
	 * Given a TreeNode<Integer,Integer> object representing the root of a binary tree,
	 * return an ArrayList<Integer> holding the keys in order from a BFS traversal.
	 * RESTRICTION: Do NOT use recursion for this implementation -- use a Queue.  (Or
	 * use your Linked List class as a queue.)
	 * @param root
	 * @return
	 */
	public static ArrayList<Integer> BFS(TreeNode<Integer, Integer> root) {
		ArrayList<Integer> data = new ArrayList<>();
		LinkedList<TreeNode<Integer,Integer>> storage = new LinkedList<TreeNode<Integer,Integer>>();
		
		if(root!= null ){
			storage.push_front(root);
			data.add(root.key);
		}
		while(storage.size()!=0){
			TreeNode<Integer,Integer> a = storage.pop_back();
			if(a.left != null){
				data.add(a.left.key);
			}
			if(a.right != null){
				data.add(a.right.key);
			}
		}
		
		return data;
		
		
	}

	/**
	 * Given a TreeNode<Integer,Integer> object representing the root of a binary tree,
	 * return an ArrayList<Integer> holding the keys in order from a pre-order traversal.
	 * RESTRICTION: Do NOT use recursion for this implementation -- use a Stack.  (Or
	 * use your Linked List class as a stack.)
	 * @param root
	 * @return
	 */
	public static ArrayList<Integer> preOrder(TreeNode<Integer, Integer> root) {
		ArrayList<Integer> data = new ArrayList<>();
		LinkedList<TreeNode<Integer,Integer>> storage = new LinkedList<TreeNode<Integer,Integer>>();
		boolean s = true;
		while(s==true){

			while(root!=null){
				data.add(root.key);
				storage.push_front(root);
				root = root.left;
			}
			if(storage.size()==0){
				break;
			}
			root = storage.pop_front();
			root = root.right;
		}
		return data;
		
	}

	/**
	 * Given a TreeNode<Integer,Integer> object representing the root of a binary tree,
	 * return an ArrayList<Integer> holding the keys in order from an in-order traversal.
	 * RESTRICTION: Do NOT use recursion for this implementation -- use a Stack.  (Or
	 * use your Linked List class as a stack.)
	 * @param root
	 * @return
	 */
	public static ArrayList<Integer> inOrder(TreeNode<Integer, Integer> root) {
		ArrayList<Integer> data = new ArrayList<>();
		LinkedList<TreeNode<Integer,Integer>> storage = new LinkedList<TreeNode<Integer,Integer>>();
		boolean s = true;
		while(s==true){

			while(root!=null){
				storage.push_front(root);
				root = root.left;
			}
			if(storage.size()==0){
				break;
			}
			root = storage.pop_front();
			data.add(root.key);
			root = root.right;
		}
		return data;
	}

}
