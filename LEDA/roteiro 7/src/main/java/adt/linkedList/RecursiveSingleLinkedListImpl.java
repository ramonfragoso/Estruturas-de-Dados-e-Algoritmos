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
		return(this.getData() == null);
	}

	@Override
	public int size() {
		return size(this, 0);
	}

	private int size(RecursiveSingleLinkedListImpl<T> x, int count) {
		
		if(this.isEmpty()) return 0;
		if(x.getData() == null) return count;
		return size(x.getNext(), count+1);
	}
	
	@Override
	public T search(T element) {
		
		if(this.getData() == null || this.getData().equals(element)) return this.getData();
		return this.getNext().search(element);
	}

	@Override
	public void insert(T element) {
		
		if(element == null) return;
		if(this.getData() == null) {
			this.setData(element);
			this.setNext(new RecursiveDoubleLinkedListImpl<T>());
		} else {
			this.getNext().insert(element);
		}
	}

	@Override
	public void remove(T element) {
		if(element == null) return;
		if (this.isEmpty()) return;
		if(this.getData().equals(element)) {
			this.setData(this.getNext().getData());
			this.setNext(this.getNext().getNext());
		} else {
			this.getNext().remove(element);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public T[] toArray() {
		
		return (T[]) this.toArray(new Object[this.size()], 0, this.size());
	}
	
	private Object[] toArray(Object[] array, int index, int size) {
		
		if(size == index) return array;
		array[index] = this.getData();
		return this.getNext().toArray(array, index + 1, size);
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
