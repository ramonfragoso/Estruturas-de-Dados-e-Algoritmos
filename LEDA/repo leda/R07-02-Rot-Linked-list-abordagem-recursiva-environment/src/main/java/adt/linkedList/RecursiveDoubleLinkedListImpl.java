package adt.linkedList;

public class RecursiveDoubleLinkedListImpl<T> extends
		RecursiveSingleLinkedListImpl<T> implements DoubleLinkedList<T> {

	protected RecursiveDoubleLinkedListImpl<T> previous;

	public RecursiveDoubleLinkedListImpl() {

	}

	public RecursiveDoubleLinkedListImpl(T data,
			RecursiveSingleLinkedListImpl<T> next,
			RecursiveDoubleLinkedListImpl<T> previous) {
		super(data, next);
		this.previous = previous;
	}

	@Override
	public void insertFirst(T element) {
		
		RecursiveDoubleLinkedListImpl<T> aux = new RecursiveDoubleLinkedListImpl<>(this.data, this.next, this.previous);
		this.setData(element);
		this.setNext(aux);
		this.setPrevious(new RecursiveDoubleLinkedListImpl<T>());
	}

	@Override
	public void removeFirst() {
		
		if (isEmpty()) return;
		this.setData(this.next.data);
		this.setNext(this.next.next);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void removeLast() {
		
		if(isEmpty()) return;
		if(this.next.isEmpty()) {
			this.data = this.next.data;
			this.next = this.next.next;
		} else 
			((DoubleLinkedList<T>) this.next).removeLast();
	}

	public RecursiveDoubleLinkedListImpl<T> getPrevious() {
		return previous;
	}

	public void setPrevious(RecursiveDoubleLinkedListImpl<T> previous) {
		this.previous = previous;
	}

}
