package adt.linkedList;


public class SingleLinkedListImpl<T> implements LinkedList<T> {

	protected SingleLinkedListNode<T> head;

	public SingleLinkedListImpl() {
		this.head = new SingleLinkedListNode<T>();
	}

	@Override
	public boolean isEmpty() {
		return(this.getHead().isNIL());
	}

	@Override
	public int size() {
		if(this.isEmpty()) return 0;
		
		int tamanho = 0;
		SingleLinkedListNode<T> aux = head;
		while(!aux.isNIL()) {
			tamanho += 1;
			aux = aux.next;
		}
		return tamanho;
	}

	@Override
	public T search(T element) {
		if(this.isEmpty()) return null;
		
		SingleLinkedListNode<T> aux = this.head;
		while(!aux.isNIL() && !aux.data.equals(element)) {
			aux = aux.next;
		}
		return (T) aux.data;
	}

	@Override
	public void insert(T element) {
		
		if(element == null) return;
		
		SingleLinkedListNode<T> aux = this.head;
		if(this.head.isNIL()) {
			SingleLinkedListNode<T> newHead = new SingleLinkedListNode<T>(element, this.head);
			this.head = newHead;
		} else {
			while(!aux.next.isNIL()) {
				aux = aux.next;
			}
			SingleLinkedListNode<T> newNode = new SingleLinkedListNode<T>(element, aux.next);
			aux.next = newNode;
		}
	}

	@Override
	public void remove(T element) {

		if(this.isEmpty()) return;
		if(this.head.data.equals(element)) {
			this.setHead(head.next);
			return;
		}
		SingleLinkedListNode<T> aux = this.head;
		while(!aux.next.isNIL() && !aux.next.data.equals(element)) {
			aux = aux.next;
		}
		aux.next = aux.next.next;
	}

	@Override
	public T[] toArray() {
		
		@SuppressWarnings("unchecked")
		T[] list = (T[]) new Object[this.size()];
		
		SingleLinkedListNode<T> aux = this.head;
		int i = 0;
		while(!aux.isNIL()) {
			list[i] = aux.data;
			aux = aux.next;
			i++;
		}
		
		return list;
		
	}

	public SingleLinkedListNode<T> getHead() {
		return head;
	}

	public void setHead(SingleLinkedListNode<T> head) {
		this.head = head;
	}
}
