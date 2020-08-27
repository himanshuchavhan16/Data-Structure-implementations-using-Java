/**
 * @author Himanshu Chavan
 */
import java.util.*;

public class Treap<E extends Comparable<E>> {
	
	private static class Node<E> {
		public E data; // key for the search
		public int priority; // random heap priority
		public Node <E> left;
		public Node <E> right;
		
		/**
		 * Constructor of class Node
		 * @param key key to be stored in node
		 * @param priority priority of the node
		 */
		public Node (E key , int priority) {
			this.data = key;
			this.priority = priority;
			left = null;
			right = null;
		}
		
		/**
		 * Right rotation
		 * @return node after rotation
		 */
		Node <E> rotateRight(){
			 Node<E> curr = this;
			 Node<E> node = curr.left;
		     curr.left = node.right;
		     node.right = curr;
		     return node;
		 }
		
		/**
		 * Left rotation
		 * @return node after rotation
		 */
		Node <E> rotateLeft(){
			 Node<E> curr = this;
			 Node<E> node = curr.right;
		     curr.right = node.left;
		     node.left = curr;
		     return node;
		 }
	}
	
	private Random priorityGenerator;
	private Node <E> root ;
	
	/**
	 * Default Constructor
	 */
	public Treap () {
		priorityGenerator = new Random();
		root = null;
	}
	
	/**
	 * Constructor with seed
	 * @param seed for random generator
	 */
	public Treap (long seed) {
		priorityGenerator = new Random(seed);
		root = null;
	}
	
	/**
	 * Add key
	 * @param key to be added in treap
	 * @return boolean if added in treap
	 */
	boolean add(E key){
		return add(key, priorityGenerator.nextInt());
	}
	
	/**
	 * Add key
	 * @param key to be added in treap
	 * @param priority priority of the node
	 * @return boolean if added in treap
	 */
	boolean add(E key , int priority ){
		if(root == null) {
			root = new Node(key, priority);
			return true;
		}
		
		Stack<Node<E>> stack = new Stack<>();
		Node<E> curr = root;
		
		while(curr != null) {
			stack.push(curr);
			if(curr.data.compareTo(key) == 0) {
				return false;
			}else if(key.compareTo(curr.data) < 0)  {
				curr = curr.left;
			}else {
				curr = curr.right;
			}
		}
		
		Node<E> parent = stack.peek();
		
		if(key.compareTo(parent.data) < 0) {
			parent.left = new Node(key, priority);
			stack.push(parent.left);
		}else {
			parent.right = new Node(key, priority);
			stack.push(parent.right);
		}
		
		reheap(stack);
		return true;
	}
	
	/**
	 * Modify the treap to maintain max heap 
	 * @param stack contains the path to current node
	 */
	private void reheap(Stack<Node<E>> stack) {
		while(stack.size() > 1) {
			
			Node<E> curr = stack.pop();
			Node<E> parent = stack.pop();
			Node<E> grandparent = null;
			
			if(!stack.isEmpty()) {
				grandparent = stack.peek();
			}
			
			if(parent.left == curr) {
				if(curr.priority > parent.priority) {
					if(grandparent == null) {
						root = parent.rotateRight();
						return;
					}
					else if(grandparent.left == parent) {
						parent = parent.rotateRight();
						grandparent.left = parent;
					}else {
						parent = parent.rotateRight();
						grandparent.right = parent;
					}
					stack.push(parent);
				}
			}else {
				if(curr.priority > parent.priority) {
					if(grandparent == null) {
						root = parent.rotateLeft();
						return;
					}
					else if(grandparent.left == parent) {
						parent = parent.rotateLeft();
						grandparent.left = parent;
					}else {
						parent = parent.rotateLeft();
						grandparent.right = parent;
					}
					stack.push(parent);
				}
			}
		}
	}
	
	/**
	 * Delete key in the treap
	 * @param key to be deleted
	 * @return boolean if node deleted
	 */
	boolean delete(E key) {
		
		Node<E> curr = root;
		Node<E> prev = null;
		
		while(curr != null) {
			if(key.compareTo(curr.data) < 0) {
				prev = curr;
				curr = curr.left;
			}else if(key.compareTo(curr.data) > 0) {
				prev = curr;
				curr = curr.right;
			}else {
				while(true) {
					Node<E> maxPriorityChild = null;
				
					if(curr.left == null && curr.right == null) {
						if(prev.left == curr) {
							prev.left = null;
						}else {
							prev.right = null;
						}
						return true;
					}
					else if(curr.left != null && curr.right != null) {
						maxPriorityChild = curr.left.priority > curr.right.priority ? curr.left : curr.right;
					}
					else if(curr.left == null) {
						maxPriorityChild = curr.right;
					}else {
						maxPriorityChild = curr.left;
					}
					
					if(maxPriorityChild != null) {
						if(curr.left == maxPriorityChild) {
							if(prev == null) {
								curr = curr.rotateRight();
								root = curr;
							}
							else if(prev.left == curr) {
								curr = curr.rotateRight();
								prev.left = curr;
							}else {
								curr = curr.rotateRight();
								prev.right = curr;
							}
							prev = curr;
							curr = curr.right;
						}else {
							if(prev == null) {
								curr = curr.rotateLeft();
								root = curr;
							}
							else if(prev.left == curr) {
								curr = curr.rotateLeft();
								prev.left = curr;
							}else {
								curr = curr.rotateLeft();
								prev.right = curr;
							}
							prev = curr;
							curr = curr.left;
						}
					}
				}
			}
		}
		return false;
	}
	
	/**
	 * find key in treap
	 * @param root tree
	 * @param key to be found in treap
	 * @return boolean if key exists
	 */
	private boolean find(Node <E> root , E key) {
		Node<E> curr = root;
		
		while(curr != null) {
			if(curr.data.compareTo(key) == 0) {
				return true;
			}else if(key.compareTo(curr.data) < 0)  {
				curr = curr.left;
			}else {
				curr = curr.right;
			}
		}
		return false;
	}
	
	/**
	 * find key in treap
	 * @param key to be found in treap
	 * @return boolean if key exists
	 */
	public boolean find(E key) {
		 return find(root, key);
	}
	
	/**
	 * String representation of the treap
	 */
	public String toString () {
		StringBuilder sb = new StringBuilder();
		toString(root, sb, 0);
		return sb.toString();
	}
	
	/**
	 * Helper for toString()
	 * @param node current node
	 * @param sb stringbuilder
	 * @param depth depth of the current node
	 */
	private void toString(Node<E> node, StringBuilder sb, int depth) {
		for(int i = 0; i < depth; i++) {
			sb.append("\t");
		}
		
		if(node == null) {
			sb.append("null\n");
			return;
		}
		
		sb.append("(key="+node.data + " priority=" + node.priority + ")\n");
		
		toString(node.left, sb, depth+1);
		toString(node.right, sb, depth+1);
		
	}
	
	public static void main(String[] args) {
		
		Treap <Integer>testTree = new Treap<Integer>();
		testTree.add(4 ,19);
		testTree.add(2 ,31);
		testTree.add(6 ,70);
		testTree.add(1 ,84);
		testTree.add(3 ,12);
		testTree.add(5 ,83);
		testTree.add(7 ,26);
		
//		Treap<Character> testTree = new Treap < Character >();
//		testTree.add(4 ,19);
//		testTree.add('p', 99);
//		testTree.add('g', 80);
//		testTree.add('a', 60);
//		testTree.add('p', 99);
//		testTree.add('r', 40);
//		testTree.add('j', 65);
//		testTree.add('u', 75);
//		testTree.add('z', 47);
//		testTree.add('w', 32);
//		testTree.add('v', 21);
//		testTree.add('x', 25);
//		testTree.add (2 ,31);
//		System.out.println(testTree);
//		System.out.println("\n");
//		testTree.add (6 ,70);
//		System.out.println(testTree);
//		System.out.println("\n");
//		testTree.add (1 ,84);
//		System.out.println(testTree);
//		System.out.println("\n");
//		testTree.add (3 ,12);
//		System.out.println(testTree);
//		System.out.println("\n");
//		testTree.add (5 ,83);
//		System.out.println(testTree);
//		System.out.println("\n");
//		testTree.add (7 ,26);
		System.out.println(testTree);
		System.out.println("\n");
		
//		testTree.remove()
//		testTree.delete('z');
//		System.out.println(testTree);
//		System.out.println("\n");
		
//		testTree.delete('p');
//		System.out.println(testTree);
//		System.out.println("\n");
	}
}
