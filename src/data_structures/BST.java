/**
 * 
 */
package data_structures;
import java.lang.IndexOutOfBoundsException;

/**
 * @author fitchae, Alicia Fitch
 *
 */
public class BST<K extends Comparable<K>, V> extends Dictionary<K, V> {
	protected TreeNode<K,V> root; 
	
	/**
	 * 
	 */
	public BST() {
		super();
		root = null;
	}
	
	/**
	 * Compare two keys and increment numOps.
	 * @param k1   First key
	 * @param k2   Second key
	 * @return     -1: k1 smaller; 0: elements equal; 1: k2 smaller
	 */
	private int compareKeys(K k1, K k2) {
		numOps++;
		return k1.compareTo(k2);
	}

	/**
	 * Get the tree's root node.
	 * @return
	 */
	public TreeNode<K,V> getRoot() {
		return root;
	}

	
	/* (non-Javadoc)
	 * @see data_structures.Dictionary#insert(java.lang.Object, java.lang.Object)
	 */
	@Override
	public void insert(K key, V value) {
		TreeNode <K,V> cur = root;
		TreeNode <K,V> newCur = new TreeNode<K,V>(key,value);
		
		if(root == null){
			root = new TreeNode<K,V>(key,value);
			n++;
		}else{
			if(find(key) != null){
				throw new IndexOutOfBoundsException();
			}
			while(cur !=null){
				if(compareKeys(key,cur.key)<0){
					if(cur.left == null){
						cur.left = newCur;
						n++;
						return;
					} else{
						cur = cur.left;
						
					}
				}else{
					if(cur.right == null){
						cur.right = newCur;
						n++;
						return;
					}else{
						cur = cur.right;
					}
					
				}
			}
			
		}		
		
	}

	
	
	/* (non-Javadoc)
	 * @see data_structures.Dictionary#remove(java.lang.Object)
	 */
	
	@Override
	public void remove(K key) {
		TreeNode <K,V> cur = root;
		TreeNode <K,V> parent = null;
		if(root == null){
			return;
		}
		while(cur !=null){
			if(compareKeys(key,cur.key)==0){
				break;
			}else if(compareKeys(key,cur.key)<0){
				parent = cur;
				cur = cur.left;
			}else{
				parent = cur;
				cur = cur.right;
			}
		}
		if(cur.left==null && cur.right==null){
			//parent.left/.right = null
			if(compareKeys(parent.left.key,key)==0){
				parent.left=null;
			}else if(compareKeys(parent.right.key,key)==0){
				parent.right=null;
			}
			cur.value= null;
			n--;
		}else if(cur.left ==null&& cur.right!= null){
			if(compareKeys(parent.left.key,key)==0){
				parent.left=cur.right;
			}else if(compareKeys(parent.right.key,key)==0){
				parent.right=cur.right;
			}
			n--;
		}else if(cur.left !=null&& cur.right== null){
			if(compareKeys(parent.left.key,key)==0){
				parent.left=cur.left;
			}else if(compareKeys(parent.right.key,key)==0){
				parent.right=cur.left;
			}
			n--;
		} else {
			TreeNode<K,V> cur2 = cur;
			parent = cur;
			cur = cur.right;
			
			while(cur.left!=null ){
					parent = cur;
					cur = cur.left;
				
			}
			
			cur2.value = cur.value;
			cur2.key = cur.key;
			if(parent.right==cur){
				parent.right=null;
			}else{
				parent.left = null;
			}
			
			n--;
			
			
		}
	}
	
	
	
	/* (non-Javadoc)
	 * @see data_structures.Dictionary#find(java.lang.Object)
	 */
	@Override
	public V find(K key) {
		TreeNode <K,V> cur = root;

		while(cur !=null){
			if(key.equals(cur.key)){
				return cur.value;
			}else if(compareKeys(key,cur.key)<0){
				cur = cur.left;
			}else{
				cur = cur.right;
			}
		}
		return null;
	}
	
	
	
	
	/**
	 * Return the smallest value in the tree.  (Return null if empty)
	 * @return key
	 */
	public K min() {
		TreeNode <K,V> cur = root;
		if(root == null){
			return null;
		}
		while(cur.left != null){
			cur = cur.left;
		}
		return cur.key;
	
	}
	
	/**
	 * Return the smallest value in the tree.  (Return null if empty)
	 * @return key
	 */
	public K max() {
		TreeNode <K,V> cur = root;
		if(root == null){
			return null;
		}
		while(cur.right != null){
			cur = cur.right;
		}
		return cur.key;
	}
	
	
	
	/**
	 * Return the height of the tree.  
	 * Definition:
	 *    The *depth* of a node is number of edges from the root to that node.
	 *    The *height* of a tree is equal to the depth of the node with the
	 *        greatest depth of all the nodes.
	 * @return int
	 */
	private int height(TreeNode<K,V> cur){
		if (cur == null){ 
			return -1;
		}
		return  1 + Math.max(height(cur.left), height(cur.right));
		
	}
	public int height() {
		return height(root);
	}
	
	
	boolean isBSTHelper(TreeNode<K,V> root, K min_value, K max_value) {
		if (root == null)
			return true;

		if ((min_value != null && root.key.compareTo(min_value) <= 0) || (max_value != null && root.key.compareTo(max_value) >= 0))
			return false;
		
		return isBSTHelper(root.left, min_value, root.key) && isBSTHelper(root.right, root.key, max_value);
	}
	
	/**
	 * Check that the tree is a BST.
	 * @param root    Root of tree being checked.
	 * @return
	 */
	boolean isBST(TreeNode<K,V> root) {
		return isBSTHelper(root, null, null);
	}
	
	
	/* (non-Javadoc)
	 * @see data_structures.Dictionary#check_structure()
	 */
	@Override
	public boolean check_structure() {
		return isBST(root);
	}

	
	void print_structure_helper(TreeNode<K,V> root, int indent) {			
		for (int i=0; i < indent; i++)
			System.out.print("\t");
		if (root == null) {
			System.out.println("LEAF");
			return;
		}
		System.out.println(root.key + ": " + root.value);
		print_structure_helper(root.left, indent+1);
		print_structure_helper(root.right, indent+1);
	}
	
	/* (non-Javadoc)
	 * @see data_structures.Dictionary#print_structure()
	 */
	@Override
	public void print_structure() {
		print_structure_helper(root, 0);
	}

	/* (non-Javadoc)
	 * @see data_structures.Container#clear()
	 */
	@Override
	public void clear() {
		n = 0;
		root = null;
	}

}
