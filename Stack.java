import java.util.Iterator;
import java.util.NoSuchElementException;

import LinkedList.IteratorHelper;
import LinkedList.Node;

public class Stack<E> extends LinkedList<E> {
	private LinkedList stack;

	Node<E> head;
	public static int currentSize;

	public Stack(){
		currentSize = 0;
		head = null;
	}

	public void push(E obj){
		Node<E> newNode = new Node<E>(obj);
		if (currentSize == 0){
			head = newNode;
			currentSize++;
		}
		else{
			newNode.next = head;
			head = newNode;
			currentSize++;
		}
	}

	public E pop(){
		if(currentSize == 0){
			return null;
		}		
		E tmp = head.data;
		head = head.next;
		currentSize--;
		return tmp;
	}

	public int size(){
		return currentSize;
	}

	public boolean isEmpty(){
		if(currentSize == 0)
			return true;
		return false;
	}

	public boolean isFull(){
		return false;
	}

	public E peek(){
		if(currentSize == 0)
			throw new RuntimeException("Error, can't peek empty stack");
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
		head = null;
		currentSize = 0;
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
