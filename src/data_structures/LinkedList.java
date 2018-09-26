/**
 * 
 */
package data_structures;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author fitchae
 * Worked with Steven Bower, Sam Duemler, and (Adetayo)Alex Oladele
 * October 3rd, 2016
 */

/**
 * The ListNode<value_type> is a helper class for your 
 * LinkedList<value_type> class.  As its not intended for use
 * outside the LinkeList class, we are keeping it simple -- the
 * two properties will be access directly, instead of going through
 * inspectors and mutators.
 * 
 * DO NOT MODIFY THIST CLASS.
 * 
 * @param <value_type>  The type of object to be stored in the list.
 */
class ListNode<value_type> {
	public value_type value;
	public ListNode<value_type> next;
		
	public ListNode(value_type v) {
		value = v;
		next = null;
	}
	
	public ListNode(value_type v, ListNode<value_type> n) {
		value = v;
		next = n;
	}
	
}


/*
 * We will implement this as a single linked list.
 */
public class LinkedList<value_type> extends Sequence<value_type> {
	
	/**
	 * head will be the first node of the list -- or null if the list is empty
	 */
	private ListNode<value_type> head;  
	private ListNode<value_type> tail; 
	
	/**
	 * List constructor: must call the superclass constructor.
	 */
	public LinkedList() {
		super();
		head  = null;
		tail = null;
		
	}

	/* (non-Javadoc)
	 * @see data_structures.Sequence#get(int)
	 */
	@Override
	public value_type get(int i) {
		ListNode<value_type> temp = null;
		if (i < 0 || i >= size())
			throw new IndexOutOfBoundsException();
		if(head!=null){
			temp = head;
		}
		
		for(int m=0;m<i;m++){
			if(temp.next==null){
				return null;
			}
			temp = temp.next;
			numOps++;
		}
	return temp.value;
		
	}

	/* (non-Javadoc)
	 * @see data_structures.Sequence#set(int, java.lang.String)
	 */
	@Override
	public value_type set(int i, value_type value) {
		
		if (i < 0 || i >= size())
			throw new IndexOutOfBoundsException();
		
		ListNode<value_type> temp = head;
		value_type val = null;
		
		for(int m =0;m<i;m++){
			temp = temp.next;
			numOps++;
		}	
		val = temp.value;
		numOps++;
		temp.value = value;
		numOps++;

		
	return val;
		
	}

	/* (non-Javadoc)
	 * @see data_structures.Sequence#add(int, java.lang.String)
	 */
	@Override
	public void add(int i, value_type value) {
		
		if (i < 0 || i > size())
			throw new IndexOutOfBoundsException();
		
		ListNode<value_type> newNode = new ListNode<value_type>(value);
		ListNode<value_type> temp = head;
		
		if(head == null){
			head = newNode;
			n=1;
		}
		else if(i==0){
			newNode.next = head;
			numOps++;
			head = newNode;
			n++;
		}else {
			for(int m =0;m<i-1;m++){
				temp = temp.next;
				numOps++;
			}
			newNode.next = temp.next;
			numOps++;
			temp.next = newNode;
			numOps++;
			n++;
		}
	
		
	}

	/* (non-Javadoc)
	 * @see data_structures.Sequence#remove(int)
	 */
	@Override
	public value_type remove(int i) {
		if (i < 0 || i >= size())
			throw new IndexOutOfBoundsException();
		
		ListNode<value_type> temp = head;
		value_type val = null;
		if(head==null){
			return null;
		}
		else if(i==0){
			val = head.value;
			numOps++;
			head = head.next;
			numOps++;
			n--;
		}
		else{
			for(int m=0;m<i-1;m++){
				if(temp.next==null){
					return null;
				}
				temp = temp.next;
				numOps++;
			}
			val = temp.next.value;
			numOps++;
			temp.next = temp.next.next;
			numOps++;
	
			
			n--;
		}
		
			
		
	
			
		return val;
	
	}

	/* (non-Javadoc)
	 * @see data_structures.Sequence#clear()
	 */
	@Override
	public void clear() {
		
		head = null;
		tail = null;
		n=0;
	}
	
	/**
	 * Search for a query element_type in the array.
	 * @param query element_type being seqrched for
	 * @return boolean
	 */
	public boolean in(value_type query) {
		for (int i=0; i < n; i++)
			if (query.equals(get(i)))
				return true;
		
		return false;
	}
	/**
	 * Add an element to the back of the element_type.
	 * @param value element_type being added
	 */
	@Override
	public void push_back(value_type value) {
		ListNode<value_type> temp = new ListNode<value_type>(value);
		
		if(n==0){
			head = temp;
			tail = temp;
			n++;
		}else{
			tail.next = temp;
			numOps++;
			tail = temp;
			n++;
		}
		
	}
	



}
