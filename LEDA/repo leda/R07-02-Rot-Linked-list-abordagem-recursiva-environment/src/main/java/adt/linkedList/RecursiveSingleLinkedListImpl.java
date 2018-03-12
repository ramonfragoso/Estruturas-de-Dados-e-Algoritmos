package adt.linkedList;

public class RecursiveSingleLinkedListImpl<T> implements LinkedList<T> {

	protected T data;
	protected RecursiveSingleLinkedListImpl<T> next;

	public RecursiveSingleLinkedListImpl() {

	}

	public RecursiveSingleLinkedListImpl(T data,
			RecursiveSingleLinkedListImpl<T> next) {
		this.data = data;
		this.next = next;
	}

	@Override
	public boolean isEmpty() {
		return this.data == null;
	}

	@Override
	public int size() {
		
		return size(this, 0);
	}
	
	private int size(RecursiveSingleLinkedListImpl<T> x, int count) {
		
		if(this.isEmpty()) return 0;
		if(x.data == null) return count;
		return size(x.next, count++);
	}

	@Override
	public T search(T element) {
		
		return this.search(this, element);
	}
	
	private T search(RecursiveSingleLinkedListImpl<T> a, T element) {
		
		if(this.isEmpty()) return null;
		if(this.data.equals(element)) return data;
		return search(this.next, element);
	}

	@Override
	public void insert(T element) {
		
		if(this.isEmpty()) {
			this.setData(element);
			this.setNext(new RecursiveSingleLinkedListImpl<T>());
		} else {
			this.next.insert(element);
		}	
	}

	@Override
	public void remove(T element) {
		
		if(this.isEmpty()) return;
		if(this.data.equals(element)) {
			this.setData(this.next.getData());
			this.setNext(this.next.next);
		} else {
			this.next.remove(element);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public T[] toArray() {
		
		return (T[]) this.toArray(new Object[this.size()], 0, this.size());
	}
	
	private Object[] toArray(Object[] array, int index, int size) {
		
		if(size == index) return array;
		array[index++] = this.data;
		return this.getNext().toArray(array, index, size);
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public RecursiveSingleLinkedListImpl<T> getNext() {
		return next;
	}

	public void setNext(RecursiveSingleLinkedListImpl<T> next) {
		this.next = next;
	}

}
