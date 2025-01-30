/**
 * Represents a list of Nodes. 
 */
public class LinkedList {
	
	private Node first; // pointer to the first element of this list
	private Node last;  // pointer to the last element of this list
	private int size;   // number of elements in this list
	
	/**
	 * Constructs a new list.
	 */ 
	public LinkedList () {
		first = null;
		last = first;
		size = 0;
	}
	
	/**
	 * Gets the first node of the list
	 * @return The first node of the list.
	 */		
	public Node getFirst() {
		return this.first;
	}

	/**
	 * Gets the last node of the list
	 * @return The last node of the list.
	 */		
	public Node getLast() {
		return this.last;
	}
	
	/**
	 * Gets the current size of the list
	 * @return The size of the list.
	 */		
	public int getSize() {
		return this.size;
	}
	
	/**
	 * Gets the node located at the given index in this list. 
	 * 
	 * @param index
	 *        the index of the node to retrieve, between 0 and size
	 * @throws IllegalArgumentException
	 *         if index is negative or greater than the list's size
	 * @return the node at the given index
	 */		
	public Node getNode(int index) {
		if (index < 0 || index > size) {
			throw new IllegalArgumentException(
					"index must be between 0 and size");
		}
		//// Replace the following statement with your code
		Node current = this.first;
		for(int i = 0; i < index; i++){
			current = current.next;
		}

		return current;
	}
	
	/**
	 * Creates a new Node object that points to the given memory block, 
	 * and inserts the node at the given index in this list.
	 * <p>
	 * If the given index is 0, the new node becomes the first node in this list.
	 * <p>
	 * If the given index equals the list's size, the new node becomes the last 
	 * node in this list.
     * <p>
	 * The method implementation is optimized, as follows: if the given 
	 * index is either 0 or the list's size, the addition time is O(1). 
	 * 
	 * @param block
	 *        the memory block to be inserted into the list
	 * @param index
	 *        the index before which the memory block should be inserted
	 * @throws IllegalArgumentException
	 *         if index is negative or greater than the list's size
	 */
	public void add(int index, MemoryBlock block) {
		if (index < 0 || index > size) {
			throw new IllegalArgumentException(
					"index must be between 0 and size");
		}

		Node node = new Node(block);//node to insert

		if(index == 0){ //adds first
			addFirst(block);
			return;

		} else if(index == size){ //adds last
			addLast(block);
			return;

		} else{
			Node current = this.first;
			Node prev = null;
			for(int i = 0; i < index; i++){
				prev = current;
				current = current.next;
			}
			prev.next = node;
			node.next = current;
			this.size++;
		}
	}

	/**
	 * Creates a new node that points to the given memory block, and adds it
	 * to the end of this list (the node will become the list's last element).
	 * 
	 * @param block
	 *        the given memory block
	 */
	public void addLast(MemoryBlock block) {
		Node node = new Node(block);
		if(first == null){
			first = node;
			last = node;
		} else{
			last.next = node;
			last = node;
		}
		this.size++;
	}
	
	/**
	 * Creates a new node that points to the given memory block, and adds it 
	 * to the beginning of this list (the node will become the list's first element).
	 * 
	 * @param block
	 *        the given memory block
	 */
	public void addFirst(MemoryBlock block) {
		Node node = new Node(block);
		node.next = this.first;
		this.first = node;
		this.size++;
		if (this.size == 1) {
			this.last = this.first;
		}
	}

	/**
	 * Gets the memory block located at the given index in this list.
	 * 
	 * @param index
	 *        the index of the retrieved memory block
	 * @return the memory block at the given index
	 * @throws IllegalArgumentException
	 *         if index is negative or greater than or equal to size
	 */
	public MemoryBlock getBlock(int index) {
		if (index < 0 || index > size || size == 0) {
			throw new IllegalArgumentException(
					"index must be between 0 and size");
		}

		Node node = getNode(index);
		return node.block;
	}	

	/**
	 * Gets the index of the node pointing to the given memory block.
	 * 
	 * @param block
	 *        the given memory block
	 * @return the index of the block, or -1 if the block is not in this list
	 */
	public int indexOf(MemoryBlock block) {
		
		Node current = first;
		for(int i = 0; i < size; i++){
			if(block.equals(current.block)){
				return i;
			}
			current = current.next;
		}
		return -1;
	}

	/**
	 * Removes the given node from this list.	
	 * 
	 * @param node
	 *        the node that will be removed from this list
	 */
	public void remove(Node node) {
		if(node.block == null){
			throw new IllegalArgumentException(
				"ERROR NullPointerException!");
		}

		if(first == null){ //empty list
			return;
		}

		if(first.equals(node)){ //remove at first place
			first = first.next;
			this.size--;
			return;
		}

		Node current = this.first;
		Node prev = null;

		while (current != null) {
			if (current.equals(node)) {
				prev.next = current.next;
				size--;
				return;
			}

			prev = current;
			current = current.next;
		}

	}

	/**
	 * Removes from this list the node which is located at the given index.
	 * 
	 * @param index the location of the node that has to be removed.
	 * @throws IllegalArgumentException
	 *         if index is negative or greater than or equal to size
	 */
	public void remove(int index) {
		if (index < 0 || index > size) {
			throw new IllegalArgumentException(
					"index must be between 0 and size");
		}

		if(index == 0){
			first = first.next;
			this.size--;
			return;
		}

		Node current = first;
		for(int i = 0; i < size; i++){
			if(i == index){
				current = getNode(index);
				remove(current);
				this.size--;
				break;
			}
		}

	}

	/**
	 * Removes from this list the node pointing to the given memory block.
	 * 
	 * @param block the memory block that should be removed from the list
	 * @throws IllegalArgumentException
	 *         if the given memory block is not in this list
	 */
	public void remove(MemoryBlock block) {
		int index = indexOf(block);
		remove(index);// throws the exception here and decrease the size
	}	

	/**
	 * Returns an iterator over this list, starting with the first element.
	 */
	public ListIterator iterator(){
		return new ListIterator(first);
	}
	
	/**
	 * A textual representation of this list, for debugging.
	 */
	public String toString() {
		String s = "";
		Node current = first;
		while(current != null){
			s = s + current.block + " ";
			current = current.next;
		}

		return s;
	}

	public static void main(String[]args){
		LinkedList q = new LinkedList();
		MemoryBlock m1 = new MemoryBlock(1, 10);
		MemoryBlock m2 = new MemoryBlock(2, 13);
		MemoryBlock m3 = new MemoryBlock(6, 6);
		//Node node = null;
		MemoryBlock m = null;
		//q.addFirst(m);
		q.add(0, m1);
		//q.add(1, m2);
		q.remove(0);
		//q.addLast(m2);
		//q.addLast(m3);
		//q.remove(2); //remove by index
		//q.remove(m3); //remove by memoryBlock
		//q.remove(q.getNode(1)); //remove by node
		System.out.println(q.toString());
	}
}