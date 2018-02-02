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
		
		if(element == null) return;
		RecursiveDoubleLinkedListImpl<T> newActual = new RecursiveDoubleLinkedListImpl<T>(this.getData(), this.getNext(),this.getPrevious());;
		this.setNext(newActual);
		this.setData(element);
		this.setPrevious(new RecursiveDoubleLinkedListImpl<>());
	}

	@Override
	public void removeFirst() {
		
		if(this.isEmpty()) return;
		this.setData(this.getNext().getData());
		this.setNext(this.getNext().getNext());
	}

	@SuppressWarnings("unchecked")
	@Override
	public void removeLast() {
		
		if(this.isEmpty()) return;
		if(this.getNext().isEmpty()) {
			this.setData(this.getNext().getData());
			this.setNext(this.getNext().getNext());
		} else {
			((DoubleLinkedList<T>) this.getNext()).removeLast();	
		}
	}

	public RecursiveDoubleLinkedListImpl<T> getPrevious() {
		return previous;
	}

	public void setPrevious(RecursiveDoubleLinkedListImpl<T> previous) {
		this.previous = previous;
	}

}
