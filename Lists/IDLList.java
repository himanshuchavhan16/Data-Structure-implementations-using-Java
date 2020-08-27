import java.util.ArrayList;

public class IDLList<E> {
	
	private Node<E> head;
	private Node<E> tail;
	private int size;
	private ArrayList<Node<E>> indices;
	
	private class Node<E>{
		E data;
		Node<E> next;
		Node<E> prev;
		
		public Node (E elem) {
			this.data = elem; 
			this.prev = null;
			this.next = null;
		}
		public Node (E elem, Node<E> prev, Node<E> next) {
			this.data = elem;
			this.prev = prev;
			this.next = next;		
		}
		
	}
	
	public IDLList () {
		this.head = null ;
		this.tail = null;
		this.size = 0;
		this.indices = new ArrayList<Node<E>>();
	}
	
	public boolean add (int index, E elem) {
		Node<E> temp = new Node<E>(elem);
		if(index > this.size + 1) return false;
		else if(this.head == null && index != 0) return false;
		else if(this.head == null && index==0){
			this.head = temp;
			this.tail = temp;
			this.indices.add(temp);
		}
		else if(index == this.size + 1)	{
			this.tail = temp;
			this.indices.add(temp);
		}else {
			this.indices.add(index, temp);
		}
		this.size += 1;
		return true;
	}
	
	public boolean add (E elem) {
		Node<E> temp = new Node<E>(elem);
		if(this.head == null){
			this.tail = temp;	
		}else {
			temp.next = this.head;
		}
		this.head = temp;
		this.indices.add(0,temp);
		this.size += 1;
		return true;
	}
	
	public boolean append (E elem) {
		Node<E> temp = new Node<E>(elem);
		//System.out.println(this.head.data + " " + this.tail.data);
		if(this.head == null){
			this.head = temp;		
		}
		else{
			//System.out.println("Here");
			temp.prev = this.tail;
			this.tail.next = temp;
		}
		this.tail = temp;
//		System.out.println(temp.prev.data);
		
		this.indices.add(this.size, temp);
		this.size += 1;
		return true;
	}
	
	public E get (int index) {
		if(index >= this.size || index < 0)
			return null;
		return this.indices.get(index).data;
	}
	
	public E getHead() {
		if(this.head != null) {
			return this.head.data;
		}
		else return null;
	}
	public E getLast () {
		if(this.tail != null) {
			return this.tail.data;
		}
		else return null;
	}
	public int size() {
		return this.size;
	}
	public E remove () {
		//System.out.println(this.head.data + " " + this.tail.data);
		if(this.size == 0) {
			return null;
		}else {
			Node<E> temp = this.indices.get(0);
			if(this.size==1) {
				this.head = null;
				this.tail = null;
			}
			else {
				this.head = temp.next;
				//System.out.println(this.head);
				//System.out.println(temp.next);
				temp.next.prev = null;
			}
			temp.prev = null;
			temp.next = null;
			this.indices.remove(0);
			this.size -= 1;
			return temp.data;
		}
	}
	public E removeLast () {
		if(this.size == 0) {
			return null;
		}
		Node<E> temp = this.indices.get(this.size - 1);
		if(this.size == 1){
			this.head = null;
			this.tail = null;	
		}else {
			this.tail = temp.prev;
			this.tail.next = null;
		}
		temp.prev = null;
		this.indices.remove(this.size-1);
		this.size -= 1;
		return temp.data;
	}
	
	public E removeAt (int index) {
		if(index >= this.size || index < 0)
			return null;
		Node<E> temp = this.indices.get(index);
		if(temp.prev==null && temp.next==null)
		{
			this.head = null;
			this.tail = null;
		}
		if(temp.prev!=null && temp.next==null)
			this.tail = temp.prev;
		if(temp.next!=null && temp.prev==null)
			this.head = temp.next;
		
		this.size -= 1;
		temp.prev=null;
		temp.next=null;
		this.indices.remove(index);
		return temp.data;
	}
	
	public boolean remove (E elem) {
		for(Node<E> temp : this.indices) {
			if(temp.data == elem) {
				if(temp.prev == null && temp.next == null)
				{
					this.head = null;
					this.tail = null;
				}
				if(temp.prev!=null && temp.next==null)
					this.tail = temp.prev;
				if(temp.next !=null && temp.prev==null)
					this.head = temp.next;
				
				this.indices.remove(temp);
				this.size -= 1;
				temp.prev = null;
				temp.next = null;
				return true;
			}
		}
		return false;
	}

	
	public String toString() {
		String list = "";
		for(Node<E> node : this.indices) {
			list = list + node.data + " ";
		}
		return list;
	}

	
}
