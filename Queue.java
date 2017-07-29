import java.util.Iterator;
import data_structures.LinkedList.IteratorHelper;
import data_structures.LinkedList.Node;
import java.util.NoSuchElementException;


public class Queue<E> extends LinkedList<E>{
	private LinkedList stack;
	Node<E> head, tail;
	int currentSize;

	public Queue(){
		currentSize = 0;
		head = tail = null;
	}

	public void enqueue(E obj){
		Node<E> newNode = new Node<E>(obj);
		if (head == null){
			head = tail = newNode;		
			currentSize++;
		}
		else {
			tail.next = newNode;
			newNode.next = null;
			tail = newNode;
			currentSize++;
		}
	}

	public E dequeue(){
		Node<E> previous = null, current = head;
		if (currentSize == 0){
			return null;
		}
		if (currentSize == 1){
			E tmp = head.data;
			head = tail = null;
			currentSize = 0;
			return tmp;
		}
		else{
			E tmp = head.data;
			head = head.next;
			currentSize--;
			return tmp;
		}
	}

	public int size(){
		return currentSize;
	}

	public boolean isEmpty(){
		return currentSize == 0;
	}

	public E peek(){
		return head.data;
	}

	public boolean contains(E obj){
		Node<E> current = head;
		if(currentSize == 0){
			return false;
		}
		if(currentSize == 1){
			return ((Comparable<E>)obj).compareTo(current.data) == 0;
		}
		while (current != null && ((Comparable<E>)obj).compareTo(current.data) != 0){
			current = current.next;
		}
		if (current == null){
			return false;
		}
		return ((Comparable<E>)obj).compareTo(current.data) == 0;
	}

	public void makeEmpty(){
		currentSize = 0;
		head = tail = null;
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
