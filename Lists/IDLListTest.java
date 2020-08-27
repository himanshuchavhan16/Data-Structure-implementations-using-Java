class IDLListTest{
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		IDLList<Integer> list = new IDLList<Integer>();
		list.add(2);
		System.out.println("Updated list after adding: " + list.toString());
		
		list.add(0,1);
		System.out.println("Updated list after adding at index: " + list.toString());
		
		list.add(3);
		System.out.println("Updated list after adding: " + list.toString());
		
		list.add(4);
		System.out.println("Updated list after adding: " + list.toString());
		
		list.append(5);
		System.out.println("Updated list after appending: " +list.toString());
		
		System.out.println("The object at that index: " + list.get(2));
		
		System.out.println("The object at head: " + list.getHead());
		
		System.out.println("The object at tail: " + list.getLast());
		
		//removing head
		System.out.println(list.remove());
		System.out.println("The list after removing the element at head: " + list.toString());
		
		//removing tail
		System.out.println(list.removeLast());
		System.out.println("The list after removing the last element: " +list.toString());
		
		//removing element at specific index
		System.out.println(list.removeAt(1));		
		System.out.println("The list after removing the element at index: " +list.toString());
		
		//Returns true if element removed else returns false.
		System.out.println(list.remove(3));
		System.out.println("The list after removing the element at its first occurance: " + list.toString());
				
		System.out.println("The Size of the list is " + list.size());
		
	}
}

