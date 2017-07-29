
import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedList<E> implements ListI<E>{ 
	int currentSize;

	class Node<E> {  
		E data;
		Node<E> next;

		public Node(E data) {
			this.data = data;
			next = null;
		}
	} 

	private Node<E> head, tail;

	public LinkedList() {
		head = tail = null;	
	}

	public void addFirst(E obj){ 
		Node<E> newNode = new Node<E>(obj);
		if(head == null){
			head = tail = newNode;
			currentSize++;
		}
		else {
			newNode.next = head;
			head = newNode;
			currentSize++;
		}
	}

	public void addLast(E obj){
		Node<E> newNode = new Node<E>(obj);
		if (head == null){ 
			head = tail = newNode;
			currentSize++;
		}
		else {
			tail.next = newNode;
			tail = newNode;
			currentSize++;
		}
	}

	public E removeFirst(){ 
		if(head == null)
			return null;
		if(head == tail){
			E tmp = head.data;
			head = tail = null;
			currentSize = 0;
			return tmp;
		}
		E tmp = head.data;
		head = head.next;
		--currentSize;
		return tmp;
	}

	public E removeLast(){ 
		Node<E> previous = null, current = head;
		if(head == null){
			return null;
		}
		if(head.next == null){
			E tmp = head.data;
			head = null;
			return tmp;
		}
		while(current != tail){
			previous = current;
			current = current.next;
		}
		tail = previous;
		previous.next = null;
		--currentSize;
		return current.data;
	}

	public E peekFirst(){ 
		if(head != null){
			return head.data;
		}
		return null;
	}

	public E peekLast(){
		if(tail != null){
			return tail.data;
		}
		return null;
	}

	public void makeEmpty(){ 
		head = tail = null;
		currentSize = 0;
	}

	public boolean isEmpty(){
		return head == null;
	}

	public boolean isFull(){
		return false;
	}

	public int size(){
		return currentSize;
	}

	public boolean contains(E obj){ 
		Node<E> current = head;
		Node<E> Check = new Node<E>(obj);

		while (current != null && ((Comparable<E>)obj).compareTo(Check.data) != 0){
			current = current.next;
			Check = current;
		}
		if( ((Comparable<E>)obj).compareTo(Check.data) == 0) {
			return true;
		}
		return false;
	}

	public Iterator<E> iterator(){ 
		return new IteratorHelper();
	}

	class IteratorHelper implements Iterator<E>{
		Node<E> index;
		public IteratorHelper(){
			index = head;
		}
		public boolean hasNext(){
			return (index != null);
		}
		public E next(){
			if(!hasNext()){
				throw new NoSuchElementException();
			}
			E val = index.data;
			index = index.next;
			return val;
		}
	}
}
