package adt.stack;

import adt.linkedList.DoubleLinkedList;
import adt.linkedList.RecursiveDoubleLinkedListImpl;
import adt.linkedList.RecursiveSingleLinkedListImpl;

public class StackRecursiveDoubleLinkedListImpl<T> implements Stack<T> {

	protected DoubleLinkedList<T> top;
	protected int size;

	public StackRecursiveDoubleLinkedListImpl(int size) {
		this.size = size;
		this.top = new RecursiveDoubleLinkedListImpl<T>();
	}

	@Override
	public void push(T element) throws StackOverflowException {
		
		if(this.isFull()) throw new StackOverflowException();
		this.top.insertFirst(element);
	}

	@Override
	public T pop() throws StackUnderflowException {
		
		if(this.isEmpty()) throw new StackUnderflowException();
		T elemento = this.top();
		this.top.removeFirst();
		return elemento;		
	}

	@SuppressWarnings("unchecked")
	@Override
	public T top() {
		
		return ((RecursiveSingleLinkedListImpl<T>) this.top).getData();
	}

	@Override
	public boolean isEmpty() {
		return(this.top.isEmpty());
	}

	@Override
	public boolean isFull() {
		return (this.top.size() == this.size);
	}

}
